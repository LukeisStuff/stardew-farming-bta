package luke.stardew.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.TextFieldElement;
import net.minecraft.client.gui.TooltipElement;
import net.minecraft.client.gui.popup.InteractivePopupComponent;
import net.minecraft.client.gui.popup.ListComponent;
import net.minecraft.client.render.Scissor;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.Shaders;
import net.minecraft.core.lang.I18n;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ListComponent.class)
public abstract class ListComponentTestMixin extends InteractivePopupComponent<Integer> {
    @Shadow
    private int lastX;

    @Shadow
    private int lastY;

    @Shadow
    protected abstract boolean canScroll();

    @Shadow
    protected abstract boolean mouseInRegion(int x, int y, int mouseX, int mouseY);

    @Shadow
    protected abstract void drawBackground(int x, int y);

    @Shadow
    protected abstract void drawScrollbar(int x, int y, int mouseY);

    @Shadow
    protected abstract void scroll(float amount);

    @Shadow
    @Final
    private @NotNull ButtonElement @NotNull [] buttons;

    @Shadow
    protected abstract int getScrollPixels();

    @Shadow
    @Final
    private @NotNull Minecraft minecraft;

    @Shadow
    @Final
    private @NotNull String @Nullable [] buttonTooltips;

    @Shadow
    @Final
    private @NotNull TooltipElement tooltip;

    @Shadow
    private float scrollAmount;

    public ListComponentTestMixin(int width) {
        super(width);
    }

    @Unique private TextFieldElement searchBar = null;

    // These hold the buttons after the filter is applied. You should use these.
    // I'd recommend renaming the original buttons array to buttonsUnfiltered and this one just to buttons.
    @Unique private ButtonElement[] buttonsFiltered = null;
    @Unique private int[] buttonsFilteredIndex = null;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addSearchBar(CallbackInfo ci) {
        this.searchBar = new TextFieldElement(this.getParent(), this.minecraft.font, 0, 0, this.getWidth(), 20, "", I18n.getInstance().translateKey("gui.inventory.creative.label.search"));
        this.searchBar.setTextChangeListener(textFieldElement -> updateButtonFilter());

        updateButtonFilter();
    }

    @Unique
    private int getSearchBarOffset() {
        return this.searchBar.height + 5;
    }

    @WrapMethod(method = "getScrollableHeight")
    public int getScrollableHeight(Operation<Integer> original) {
        return 20 * (this.buttonsFiltered != null ? this.buttonsFiltered.length : 0);
    }

    // It's probably not amazing to be allocating arrays like this.
    // I think it should be fine since it's only when the text box changes.
    // Otherwise, might be worth to cache the filter buffer and IDK to do with the string lists as java doesn't have slices.

    @Unique
    private boolean filterString(String filter, String input) {
        var filteredList = filter.toLowerCase().split("\\s");
        var inputList = input.toLowerCase().split("\\s");

        for (String filterFormated : filteredList) {
            for (String inputFormated: inputList) {
                if (inputFormated.contains(filterFormated)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Unique
    public void updateButtonFilter() {
        var temporaryFilterButtonBuffer = new ButtonElement[this.buttons.length];
        var temporaryFilterIndexBuffer = new int[this.buttons.length];

        int count = 0;

        for (int i = 0; i < this.buttons.length; i++) {
            var btn = this.buttons[i];

            if (filterString(this.searchBar.getText(), btn.displayString)) {
                temporaryFilterButtonBuffer[count] = btn;
                temporaryFilterIndexBuffer[count] = i;
                count += 1;
            }
        }

        if (buttonsFiltered == null || buttonsFiltered.length != count) {
            this.scrollAmount = 0;
            buttonsFiltered = new ButtonElement[count];
            buttonsFilteredIndex = new int[count];
        }

        System.arraycopy(temporaryFilterButtonBuffer, 0, buttonsFiltered, 0, count);
        System.arraycopy(temporaryFilterIndexBuffer, 0, buttonsFilteredIndex, 0, count);

    }

    @Inject(method = "onClick", at = @At("HEAD"))
    public void onClick(int x, int y, int button, CallbackInfo ci) {
        this.searchBar.mouseClicked(x, y, button);
    }

    @Definition(id = "buttons", field = "Lnet/minecraft/client/gui/popup/ListComponent;buttons:[Lnet/minecraft/client/gui/ButtonElement;")
    @Expression("this.buttons")
    @ModifyExpressionValue(method = "onClick", at = @At("MIXINEXTRAS:EXPRESSION"))
    ButtonElement[] useFilteredList(ButtonElement[] original) {
        return buttonsFiltered;
    }

    @Override
    public void onKeyDown(int keyCode, char c) {
        super.onKeyDown(keyCode, c);
        this.searchBar.textboxKeyTyped(c, keyCode);
    }

    @Override
    public void tick() {
        super.tick();
        searchBar.updateCursorCounter();
    }

    @Override
    public void render(int x, int y, int mouseX, int mouseY) {
        this.lastX = x;
        this.lastY = y;

        this.searchBar.xPosition = x;
        this.searchBar.yPosition = y;
        this.searchBar.drawTextBox();
        this.searchBar.updateCursor(Minecraft.getMinecraft(), mouseX, mouseY);

        if (this.canScroll() && this.mouseInRegion(x, y, mouseX, mouseY)) {
            float wheel = (float) Mouse.getDWheel();
            if (wheel != 0.0F) {
                this.scroll(wheel / -12.0F);
            }
        }

        GLRenderer.pushFrame();
        GLRenderer.setShader(Shaders.COLOR);

        this.drawBackground(x, y + getSearchBarOffset());
        this.drawScrollbar(x, y + getSearchBarOffset(), mouseY);

        GLRenderer.popFrame();

        Scissor.enable(x + 1, y + getSearchBarOffset() + 1, this.getWidth() - 2, this.getHeight() - 2 - getSearchBarOffset());

        for (int i = 0; i < this.buttonsFiltered.length; ++i) {
            if (this.canScroll()) {
                this.buttonsFiltered[i].width = this.getWidth() - 2 - 6;
            } else {
                this.buttonsFiltered[i].width = this.getWidth() - 2;
            }

            ButtonElement button = this.buttonsFiltered[i];
            button.xPosition = x + 1;
            button.yPosition = (y + getSearchBarOffset() + 1) + i * 20 - this.getScrollPixels();

            if (this.mouseInRegion(x, y, mouseX, mouseY)) {
                button.drawButton(this.minecraft, mouseX, mouseY);
            } else {
                button.drawButton(this.minecraft, -1, -1);
            }
        }

        Scissor.disable();
        if (this.buttonTooltips != null && this.mouseInRegion(x, y, mouseX, mouseY)) {

            for(int i = 0; i < this.buttonsFiltered.length; ++i) {
                ButtonElement button = this.buttonsFiltered[i];
                if (button.isHovered(mouseX, mouseY)) {
                    this.tooltip.render(this.buttonTooltips[buttonsFilteredIndex[i]], mouseX, mouseY, 0, 0);
                    break;
                }
            }

        }
    }

    ///  This is here to factor the offset from the searchBar into the vexter position.
    ///  MIXINEXTRAS:EXPRESSION is bassically bytecode regex.
    ///
    ///  tessellator.addVertex((double)x, (double)(y + this.height), (double)0.0F);
    ///  to:
    ///  tessellator.addVertex((double)x, (double)(y + this.height - this.searchBar.getHeight() - SCROLLBAR_PADDING), (double)0.0F);

    @Definition(id = "height", field = "Lnet/minecraft/client/gui/popup/ListComponent;height:I")
    @Expression("? + this.height")
    @ModifyExpressionValue(method = "drawBackground", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int factorSearchBarBackground(int original) {
        return original - this.getSearchBarOffset();
    }

    @Definition(id = "height", field = "Lnet/minecraft/client/gui/popup/ListComponent;height:I")
    @Expression("this.height - 2")
    @ModifyExpressionValue(method = "drawScrollbar", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int factorSearchBarScrollbar(int original) {
        return original - this.getSearchBarOffset();
    }

    @Definition(id = "height", field = "Lnet/minecraft/client/gui/popup/ListComponent;height:I")
    @Expression("this.height - 2")
    @ModifyExpressionValue(method = "getScrollPixels", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int factorSearchBarScrollPixels(int original) {
        return original - this.getSearchBarOffset();
    }
}

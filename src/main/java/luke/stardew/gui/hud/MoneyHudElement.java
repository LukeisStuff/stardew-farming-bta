package luke.stardew.gui.hud;

import luke.stardew.misc.money.Money;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScreenHudEditor;
import net.minecraft.client.gui.hud.HudIngame;
import net.minecraft.client.gui.hud.component.HudComponentMovable;
import net.minecraft.client.gui.hud.component.layout.Layout;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;

public class MoneyHudElement extends HudComponentMovable {
    public static int SHOW_MONEY_STAMP = 0;

    private static final IconCoordinate ICON_TEXTURE = TextureRegistry.getTexture("stardew:gui/hud/coin");

    public MoneyHudElement(String key, int xSize, int ySize, Layout layout) {
        super(key, xSize, ySize, layout);
    }

    @Override
    public boolean isVisible() {
        if (!GameSettings.IMMERSIVE_MODE.drawHotbar()) return false;

        if (mc.currentScreen instanceof ScreenHudEditor) return true;
        if (System.currentTimeMillis()/1000 - SHOW_MONEY_STAMP < 10) return true;

        return false;
    }

    @Override
    public int getBaseYSize() {
        return Math.min((int) mc.font.heightOfConstrainedChars("9999", 9999), ICON_TEXTURE.height);
    }

    @Override
    public int getBaseXSize() {
        final int balance;

        if (mc.currentWorld != null) balance = Money.getMoney(mc.currentWorld).getBalanceFor(mc.thePlayer);
        else balance = 9999;

        return ICON_TEXTURE.width + mc.font.stringWidth(String.valueOf(balance));
    }

    private void renderElement(Gui hud, int x, int y, float partialTick) {
        GLRenderer.pushFrame();
        GLRenderer.setColor4f(1F, 1F, 1F, 1F);

        var balance = String.valueOf(mc.currentWorld != null ? Money.getMoney(mc.currentWorld).getBalanceFor(mc.thePlayer) : 0);
        var strHeight = mc.font.heightOfConstrainedChars(balance, 9999);

        hud.drawRect(x - 1, y - 3, x + this.getBaseXSize() + 3, y + this.getBaseYSize() + 3, 0x48000000);

        hud.drawGuiIcon(
            x,
            (int) (y + (strHeight /2) - ((double) ICON_TEXTURE.height/2)),
            ICON_TEXTURE.width,
            ICON_TEXTURE.height,
            ICON_TEXTURE
        );

        hud.drawStringShadow(mc.font, balance, x + ICON_TEXTURE.width + 2, y + 1, 0xFFFFFFFF);
        GLRenderer.popFrame();
    }

    @Override
    public void render(HudIngame hud, int xSizeScreen, int ySizeScreen, float partialTick) {
        int x = this.getLayout().getComponentX(this, xSizeScreen);
        int y = this.getLayout().getComponentY(this, ySizeScreen);
        renderElement(hud, x, y, partialTick);
    }

    @Override
    public void renderPreview(Gui gui, Layout layout, int xSizeScreen, int ySizeScreen) {
        int x = this.getLayout().getComponentX(this, xSizeScreen);
        int y = this.getLayout().getComponentY(this, ySizeScreen);
        renderElement(gui, x, y, 0);
    }

}

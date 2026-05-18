package luke.stardew.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.achievements.StardewAchievements;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemToolHoe;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Player.class)
public abstract class StardewAchievementMixin {

    @Shadow
    @Final
    @NotNull
    public ContainerInventory inventory;

    @Shadow
    public abstract void triggerAchievement(Stat statbase);

    @Shadow
    public abstract int getStat(@Nullable Stat stat);

    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        original.call();

        if (!(this.getStat(StardewAchievements.STARDEW) > 0)) {
            for (ItemStack itemStack : this.inventory.mainInventory) {
                if (itemStack != null && itemStack.getItem() instanceof ItemToolHoe) {
                    this.triggerAchievement(StardewAchievements.STARDEW);
                }
            }
        }
    }
}

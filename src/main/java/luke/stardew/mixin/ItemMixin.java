package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Item.class, remap = false)
public abstract class ItemMixin {

    @Inject(method = "onUseOnBlock", at = @At(value = "HEAD"))
    public void addStickFunctionality(ItemStack selfStack, World world, Player player, TilePosc blockPos, Side side, double xHit, double yHit, CallbackInfoReturnable<Boolean> cir) {
        if (player.getCurrentEquippedItem().itemID == Items.STICK.id && world.getBlockType(blockPos) == Blocks.FARMLAND_DIRT && world.getBlockId(blockPos.x(), blockPos.y() + 1, blockPos.z()) == 0 && side == Side.TOP) {
            player.getCurrentEquippedItem().consumeItem(player);
            world.setBlockWithNotify(blockPos.x(), blockPos.y() + 1, blockPos.z(), StardewBlocks.PLANT_STAKE.id());
            player.swingItem();
            world.playBlockSoundEffect(player, blockPos.x() + 0.5F, blockPos.y() + 0.5F, blockPos.z() + 0.5F, Blocks.DIRT, EnumBlockSoundEffectType.PLACE);
        }
    }
}


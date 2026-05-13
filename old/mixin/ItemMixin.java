package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = Item.class, remap = false)
public abstract class ItemMixin {

    @Inject(method = "onUseItemOnBlock", at = @At(value = "HEAD"))
    public void addStickFunctionality(ItemStack itemstack, Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir) {
        if (player.getCurrentEquippedItem().itemID == Items.STICK.id && world.getBlockId(blockX, blockY, blockZ) == Blocks.FARMLAND_DIRT.id() && world.getBlockId(blockX, blockY + 1, blockZ) == 0 && side == Side.TOP) {
            player.getCurrentEquippedItem().consumeItem(player);
            world.setBlockWithNotify(blockX, blockY + 1, blockZ, StardewBlocks.PLANT_STAKE.id());
            player.swingItem();
            world.playBlockSoundEffect(player, blockX + 0.5F, blockY + 0.5F, blockZ + 0.5F, Blocks.DIRT, EnumBlockSoundEffectType.PLACE);
        }
    }

    @Inject(method = "onUseByActivator", at = @At(value = "HEAD"))
    public void addSugarToBeehive(ItemStack itemStack, TileEntityActivator activator, World world, Random random, int activatorX, int activatorY, int activatorZ, double offX, double offY, double offZ, Direction direction, CallbackInfo ci) {
        if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
            return;
        }

        int targetX = activatorX + direction.offsetX();
        int targetY = activatorY + direction.offsetY();
        int targetZ = activatorZ + direction.offsetZ();
        int targetBlockId = world.getBlockId(targetX, targetY, targetZ);

        if (targetBlockId != StardewBlocks.BEEHIVE.id()) {
            return;
        }

        if (itemStack == null || !itemStack.getItem().equals(Items.DUST_SUGAR)) {
            return;
        }

        int meta = world.getBlockMetadata(targetX, targetY, targetZ);
        world.setBlockAndMetadataWithNotify(targetX, targetY, targetZ, StardewBlocks.BEEHIVE_IDLE.id(), meta);
        world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, targetX + 0.5, targetY + 0.5, targetZ + 0.5, "random.pop", 0.2F, 0.5F);
        --itemStack.stackSize;
    }
}


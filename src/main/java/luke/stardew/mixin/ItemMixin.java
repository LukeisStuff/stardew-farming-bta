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
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = Item.class, remap = false)
public abstract class ItemMixin {

    @Inject(method = "onUseOnBlock", at = @At(value = "HEAD"))
    public void addStickFunctionality(ItemStack selfStack, World world, Player player, TilePosc blockPos, Side side, double xHit, double yHit, CallbackInfoReturnable<Boolean> cir) {
        if (
            player.getCurrentEquippedItem().itemID == Items.STICK.id
            && world.getBlockType(blockPos) == Blocks.FARMLAND_DIRT
            && world.getBlockType(blockPos.up(new TilePos())) == Blocks.AIR
            && side == Side.TOP
        ) {
            player.getCurrentEquippedItem().consumeItem(player);
            world.setBlockTypeNotify(blockPos.up(new TilePos()), StardewBlocks.PLANT_STAKE);
            player.swingItem();
            world.playBlockSoundEffect(player, blockPos.x() + 0.5F, blockPos.y() + 0.5F, blockPos.z() + 0.5F, Blocks.DIRT, EnumBlockSoundEffectType.PLACE);
        }
    }

    @Inject(method = "onUseByActivator(Lnet/minecraft/core/item/ItemStack;Lnet/minecraft/core/world/World;Lnet/minecraft/core/block/entity/TileEntityActivator;Ljava/util/Random;Lnet/minecraft/core/world/pos/TilePosc;Lnet/minecraft/core/util/helper/Direction;DDD)V", at = @At(value = "HEAD"))
    public void addSugarToBeehive(ItemStack selfStack, World world, TileEntityActivator activator, Random random, TilePosc blockPos, Direction direction, double offX, double offY, double offZ, CallbackInfo ci) {
        if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
            return;
        }

        if (world.getBlockType(blockPos) != StardewBlocks.BEEHIVE) {
            return;
        }

        if (selfStack == null || !selfStack.getItem().equals(Items.DUST_SUGAR)) {
            return;
        }

        int meta = world.getBlockData(blockPos);
        world.setBlockTypeDataNotify(blockPos, StardewBlocks.BEEHIVE_IDLE, meta);

        world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, blockPos.x() + 0.5, blockPos.y() + 0.5, blockPos.z() + 0.5, "random.pop", 0.2F, 0.5F);
        --selfStack.stackSize;
    }
}


package luke.stardew.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = MobPig.class, remap = false)
public abstract class PigDigTruffleMixin extends MobAnimal {
    @Unique
    public int timeUntilNextTruffle = this.random.nextInt(6000) + 6000;

    protected PigDigTruffleMixin(World world) {
        super(world);
    }

    @WrapMethod(method = "onLivingUpdate")
    public void onLivingUpdate(Operation<Void> original) {
        original.call();

        var block = new TilePos(this.x, this.y, this.z);
        var tilePosBelow = block.down(new TilePos());
        var blockBelow = this.world.getBlockType(tilePosBelow);

        if (
            !(
                (
                    blockBelow != Blocks.GRASS
                    && blockBelow != Blocks.GRASS_RETRO
                    && blockBelow != Blocks.DIRT
                    && blockBelow != Blocks.MUD
                    && blockBelow != Blocks.FARMLAND_DIRT
                )
                || this.world.isClientSide
            )
            && --this.timeUntilNextTruffle <= 0
        ) {
            this.world.playBlockSoundEffect(null, (int) this.x, (int) this.y - 1, (int) this.z, blockBelow, EnumBlockSoundEffectType.MINE);
            this.dropItem(StardewBlocks.MUSHROOM_TRUFFLE.id(), world.rand.nextInt(2) + 1);
            this.isMovementBlocked();
            this.world.setBlockTypeNotify(tilePosBelow, Blocks.DIRT);
            this.timeUntilNextTruffle = this.random.nextInt(6000) + 6000;
        }
    }
}

package luke.stardew.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.WeightedRandomBag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureLabyrinth;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureLabyrinth.class, remap = false)
public abstract class WorldFeatureLabyrinthMixin {
    @Shadow
    public WeightedRandomBag<WeightedRandomLootObject> chestLoot;
    @Unique
    boolean isHot = false;

    @Inject(method = "place", at = @At("HEAD"))
    public void generate(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        Biome biome = world.getBlockBiome(new TilePos(x, y, z));
        if (biome == Biomes.OVERWORLD_DESERT || biome == Biomes.OVERWORLD_OUTBACK || biome == Biomes.OVERWORLD_OUTBACK_GRASSY) {
            this.isHot = true;
        }
    }

    @Inject(method = "pickCheckLootItem", at = @At("HEAD"), cancellable = true)
    public void pickCheckLootItem(Random random, CallbackInfoReturnable<ItemStack> cir) {
        if (random.nextInt(16) == 0 && random.nextInt(10) == 0) {
            cir.setReturnValue(new ItemStack(StardewBlocks.SAPLING_APPLE, random.nextInt(1) + 1));
        }
    }

    @Definition(id = "chestLoot", field = "Lnet/minecraft/core/world/generate/feature/WorldFeatureLabyrinth;chestLoot:Lnet/minecraft/core/WeightedRandomBag;")
    @Definition(id = "WeightedRandomBag", type = WeightedRandomBag.class)
    @Expression("this.chestLoot = new WeightedRandomBag()")
    @Inject(method = "place", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
    public void addTreasure(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (isHot) {
            this.chestLoot.addEntry(new WeightedRandomLootObject(new ItemStack(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN), 1), 50.0F);
        }
    }

}

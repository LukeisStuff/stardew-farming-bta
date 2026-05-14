package luke.stardew.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import luke.stardew.entities.EntityItemFireResistant;
import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.joml.Vector3d;
import org.joml.primitives.AABBdc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = EntityFishingBobber.class, remap = false)
public abstract class EntityBobberMixin extends Entity implements IEntityBobberMixin {
    @Shadow
    public Player owner;
    @Shadow
    private int ticksInAir;
    @Shadow
    private int ticksCatchable;
    @Shadow
    public Entity hookedEntity;

    protected EntityBobberMixin(World world) {
        super(world);
        this.ticksInAir = 0;
        this.ticksCatchable = 0;
        this.hookedEntity = null;
        this.setSize(0.25F, 0.25F);
        this.ignoreFrustumCheck = true;
    }

    @Unique
    private boolean hasBait = false;

    @Override
    public boolean stardew_farming_bta$hasBait() {
        return hasBait;
    }

    @Override
    public void stardew_farming_bta$setBait(boolean bool) {
        hasBait = bool;
    }

    /**
     * @author DundiGundi and 99% khep yo
     * @reason modifying if statement that removes hookedEntity when fishing rod is not held to detect for the tiered fishing rod too
     */
    @Definition(id = "getItem", method = "Lnet/minecraft/core/item/ItemStack;getItem()Lnet/minecraft/core/item/Item;")
    @Definition(id = "TOOL_FISHINGROD", field = "Lnet/minecraft/core/item/Items;TOOL_FISHINGROD:Lnet/minecraft/core/item/Item;")
    @Expression("?.getItem() != TOOL_FISHINGROD")
    @ModifyExpressionValue(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"))
    boolean isHoldingFishingRod(boolean original, @Local(name = "heldPlayerItem") ItemStack heldPlayerItem) {
        return original && !(heldPlayerItem.getItem() instanceof ItemToolFishingRodTiered);
    }

    /**
     * @author Kheprep
     * @reason decrease catchTime based on material
     */
    @Definition(id = "catchRate", local = @Local(type = int.class, name = "catchRate"))
    @Definition(id = "nextInt", method = "Ljava/util/Random;nextInt(I)I")
    @Expression("?.nextInt(catchRate)")
    @ModifyExpressionValue(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"))
    int modifyCatchRate(int catchRate) {
        int materialRate = 0;
        int baitRate = 0;

        if (owner.getCurrentEquippedItem().itemID == StardewItems.TOOL_FISHINGROD_DIAMOND.id) {
            materialRate = 200;

        } else if (
               owner.getCurrentEquippedItem().itemID == StardewItems.TOOL_FISHINGROD_IRON.id
            || owner.getCurrentEquippedItem().itemID == StardewItems.TOOL_FISHINGROD_STEEL.id
        ) {
            materialRate = 100;
        }

        if (this.stardew_farming_bta$hasBait()) {
            baitRate = 50;
        }

        return catchRate - materialRate - baitRate;
    }

    /**
     * @author Kheprep
     * @reason allow nether fishing
     */
    @Definition(id = "isAABBInMaterial", method = "Lnet/minecraft/core/world/World;isAABBInMaterial(Lorg/joml/primitives/AABBdc;Lnet/minecraft/core/block/material/Material;)Z")
    @Expression("?.isAABBInMaterial(?, ?)")
    @ModifyExpressionValue(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"))
    boolean isInliquid(boolean original, @Local(name = "aabb") AABBdc aabb) {
        return this.world.getIsAnyLiquid(aabb);
    }



    /**
     * @author DundiGundi
     * @reason modifying catchable fishes based on type of fishing rod, position of hookedEntity, season or weather
     */
    @Definition(id = "EntityItem", type = EntityItem.class)
    @Expression("new EntityItem(?, ?, ?, ?, ?)")
    @ModifyExpressionValue(method = "yoink", at = @At("MIXINEXTRAS:EXPRESSION"))
    EntityItem filletOFish(EntityItem original) {
        ItemStack fish = new ItemStack(getCatchableFish());
        if (this.isInLava()) {
            return new EntityItemFireResistant(this.world, this.x, this.y, this.z, fish);
        }

        return new EntityItem(this.world, this.x, this.y, this.z, fish);
    }

    @Unique
    public Item getCatchableFish() {
        assert world != null;
        Season season = world.getSeasonManager().getCurrentSeason();
        Weather weather = world.getWeatherManager().getCurrentWeather();
        Item[] treasuresLowValue = {Items.OLIVINE, Items.QUARTZ, Items.DUST_REDSTONE, Items.COAL};
        Item[] treasuresMiddleValue = {Items.ORE_RAW_IRON, Items.ORE_RAW_GOLD};
        Item[] treasuresHighValue = {Items.DIAMOND, Items.INGOT_STEEL_CRUDE};

        if (isInLava()) {
            if (random.nextInt(2) == 0) return StardewItems.FISH_PIG;
            return StardewItems.FISH_EEL_LAVA;
        } else if (this.owner.getCurrentEquippedItem().itemID != StardewItems.TOOL_FISHINGROD_GOLD.id) {
            if (weather == Weathers.OVERWORLD_STORM && world.rand.nextInt(15) == 0) {
                return StardewItems.FISH_SWORD;

            } else if (weather == Weathers.OVERWORLD_FOG && !world.isDaytime() && world.rand.nextInt(15) == 0) {
                return StardewItems.FISH_GHOST;

            } else if (!world.canBlockSeeTheSky(MathHelper.floor(this.x), MathHelper.floor(this.y) + 1, MathHelper.floor(this.z)) && MathHelper.floor(this.y) + 1 <= 32 && world.rand.nextInt(15) == 0) {
                return StardewItems.FISH_STONE;

            } else if (season == Seasons.OVERWORLD_SPRING) {
                return StardewItems.FOOD_BASS_RAW;

            } else if (season == Seasons.OVERWORLD_SUMMER) {
                return StardewItems.FOOD_SNAPPER_RAW;

            } else if (season == Seasons.OVERWORLD_FALL) {
                return Items.FOOD_FISH_RAW;

            } else if (season == Seasons.OVERWORLD_WINTER) {
                return StardewItems.FOOD_SALMON_RAW;
            }
        } else {
            if (world.rand.nextInt(2) == 0) {
                if (world.rand.nextInt(2) == 0) {
                    return treasuresMiddleValue[world.rand.nextInt(treasuresMiddleValue.length)];
                } else {
                    return treasuresHighValue[world.rand.nextInt(treasuresHighValue.length)];
                }
            } else {
                return treasuresLowValue[random.nextInt(treasuresLowValue.length)];
            }
        }
        return Items.BONE;
    }
}

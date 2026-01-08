package luke.stardew.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import luke.stardew.entities.EntityItemFireResistant;
import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityFishingBobber.class, remap = false)
public abstract class EntityBobberMixin extends Entity implements IEntityBobberMixin {

    @Shadow
    public Player owner;
    @Shadow
    private int ticksCatchable;
    @Shadow
    public Entity hookedEntity;

    @Shadow
    public abstract boolean isInGround();

    @Shadow
    private @Nullable TilePos tilePos;

    protected EntityBobberMixin(World world) {
        super(world);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void defineExtraData(CallbackInfo ci) {
        this.entityData.define(3, (byte) 0, Byte.class);
    }

    @Override
    public boolean stardew_farming_bta$hasBait() {
        return this.entityData.getByte(3) != 0;
    }

    @Override
    public void stardew_farming_bta$setBait(boolean value) {
        this.entityData.set(3, (byte) (value ? 1 : 0));
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/ItemStack;getItem()Lnet/minecraft/core/item/Item;"))
    private Item allowTieredRods(ItemStack stack, Operation<Item> original) {
        Item item = original.call(stack);
        if (item instanceof ItemToolFishingRodTiered) {
            return Items.TOOL_FISHINGROD;
        }
        return item;
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;isAABBInMaterial(Lorg/joml/primitives/AABBdc;Lnet/minecraft/core/block/material/Material;)Z"))
    private boolean allowWaterOrLava(boolean original) {
        if (original) return true;
        return this.world.isAABBInMaterial(this.bb, Materials.LAVA);
    }

    /**
     * @author DundiGundi
     * @reason modifying catchable fishes based on type of fishing rod, position of hookedEntity, season or weather
     */
    @Overwrite
    public int yoink() {
        int damage = 0;

        if (this.isInGround()) {
            double dx = this.x - this.owner.x;
            double dy = this.y - this.owner.y;
            double dz = this.z - this.owner.z;

            double distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
            if (distance > 0.0) {
                dx /= distance;
                dy /= distance;
                dz /= distance;
            }

            double clamp = 0.6;
            dx = MathHelper.clamp(dx, -clamp, clamp);
            dy = MathHelper.clamp(dy, -clamp, clamp);
            dz = MathHelper.clamp(dz, -clamp, clamp);

            double scale = 2.0;
            this.owner.xd += dx * scale;
            this.owner.yd += dy * scale;
            this.owner.zd += dz * scale;

            damage = 5;
        }

        if (this.hookedEntity != null) {
            double dx = this.owner.x - this.x;
            double dy = this.owner.y - this.y;
            double dz = this.owner.z - this.z;

            double distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
            double scale = 0.1;

            this.hookedEntity.xd += dx * scale;
            this.hookedEntity.yd += dy * scale + MathHelper.sqrt(distance) * 0.08;
            this.hookedEntity.zd += dz * scale;

            damage = 3;
        } else if (this.ticksCatchable > 0 && !this.world.isClientSide) {
            ItemStack catchItem = new ItemStack(getCatchableFish());

            EntityItem item = isInLava()
                ? new EntityItemFireResistant(this.world, this.x, this.y, this.z, catchItem)
                : new EntityItem(this.world, this.x, this.y, this.z, catchItem);

            double dx = this.owner.x - this.x;
            double dy = this.owner.y - this.y;
            double dz = this.owner.z - this.z;

            double distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
            double scale = 0.1;

            item.xd = dx * scale;
            item.yd = dy * scale + MathHelper.sqrt(distance) * 0.08;
            item.zd = dz * scale;

            this.world.entityJoinedWorld(item);
            this.owner.addStat(StatList.fishCaughtStat, 1);

            damage = 1;
        }

        if (this.isInGround()) {
            damage = 2;
        }

        this.remove();
        this.owner.bobberEntity = null;
        return damage;
    }

    @Unique
    public Item getCatchableFish() {
        Season season = world.getSeasonManager().getCurrentSeason();
        Weather weather = world.getWeatherManager().getCurrentWeather();
        Item[] treasuresLowValue = {Items.OLIVINE, Items.QUARTZ, Items.DUST_REDSTONE, Items.COAL};
        Item[] treasuresMiddleValue = {Items.ORE_RAW_IRON, Items.ORE_RAW_GOLD};
        Item[] treasuresHighValue = {Items.DIAMOND, Items.INGOT_STEEL_CRUDE};
        ItemStack held = this.owner.getCurrentEquippedItem();
        if (isInLava()) {
            return StardewItems.FISH_EEL_LAVA;
        } else if (held == null || held.itemID != StardewItems.TOOL_FISHINGROD_GOLD.id) {
            if (weather == Weathers.OVERWORLD_STORM && world.rand.nextInt(15) == 0) {
                return StardewItems.FISH_SWORD;

            } else if (weather == Weathers.OVERWORLD_FOG && !world.isDaytime() && world.rand.nextInt(15) == 0) {
                return StardewItems.FISH_GHOST;

            } else if (this.tilePos != null
                && !world.canBlockSeeSky(this.tilePos)
                && MathHelper.floor(this.y) + 1 <= 32
                && world.rand.nextInt(15) == 0) {
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

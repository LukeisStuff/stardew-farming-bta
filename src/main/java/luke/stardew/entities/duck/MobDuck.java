package luke.stardew.entities.duck;

import com.mojang.nbt.tags.CompoundTag;
import luke.stardew.items.StardewItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.BlockLogicFluid;
import net.minecraft.core.entity.AgedMob;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.MobAge;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.item.tool.ItemToolShears;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import static luke.stardew.StardewMod.MOD_ID;

public class MobDuck extends MobAnimal implements AgedMob {
    public float flap = 0.0F;
    public float flapSpeed = 0.0F;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public int eggTimer;
    public int featherTimer;
    private final @NonNull MobAge age;

    public MobDuck(World world) {
        super(world);
        this.setTextureIdentifier(MOD_ID, "duck");
        this.setSize(0.6f, 1.0f);
        this.eggTimer = this.random.nextInt(3000) + 3000;
        this.featherTimer = this.random.nextInt(3000) + 3000;
        this.mobDrops.add(new WeightedRandomLootObject(Items.FEATHER_CHICKEN.getDefaultStack(), 0, 1));
        this.age = MobAge.newRandom(this, 168, 14, 140);
    }

    public @NonNull MobAge getMobAge() {
        return this.age;
    }

    @Override
    public int getMaxHealth() {
        return 5;
    }

    @Override
    public void updateAI() {
        boolean inWater = this.wasInWater;
        this.wasInWater = false;
        super.updateAI(); //Stop jumping in water
        this.wasInWater = inWater;

        if (this.isInWater()) {
            double yh = this.bb.minY - MathHelper.floor(this.bb.minY);

            int x = MathHelper.floor(this.x);
            int y = MathHelper.floor(this.bb.minY + 0.125);
            int y2 = MathHelper.floor(this.bb.minY + 0.6);
            int z = MathHelper.floor(this.z);
            boolean air = this.world.getBlockLogic(x, y2, z, BlockLogicFluid.class) == null;
            if (this.world != null && this.world.getBlockLogic(x, y, z, BlockLogicFluid.class) != null && air) {
                if (yh < 0.125) {
                    this.yd = 0;
                }
            } else {
                this.yd = 0.1;
            }
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3);
        if (this.flapSpeed < 0.0F) {
            this.flapSpeed = 0.0F;
        }

        if (this.flapSpeed > 1.0F) {
            this.flapSpeed = 1.0F;
        }

        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping = (float) ((double) this.flapping * 0.9);
        if (!this.onGround && this.yd < (double) 0.0F) {
            this.yd *= 0.6;
        }

        this.flap += this.flapping * 2.0F;
        if (!this.world.isClientSide && --this.eggTimer <= 0) {
            this.world.playSoundAtEntity(null, this, "mob.chickenplop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(StardewItems.EGG_DUCK.id, 1);
            this.eggTimer = this.random.nextInt(3000) + 3000;
        }

        if (!this.world.isClientSide && --this.featherTimer <= 0) {
            this.dropItem(Items.FEATHER_CHICKEN.id, 1);
            this.featherTimer = this.random.nextInt(6000) + 6000;
        }

        this.getMobAge().tick(this.world);
    }

    @Override
    public boolean interact(@NotNull Player player) {
        if (super.interact(player)) {
            return true;
        } else {
            ItemStack heldItem = player.getHeldItem();
            if (heldItem != null && heldItem.getItem() instanceof ItemToolShears && this.getHealth() > 0 && this.hurtTime <= 0 && !this.world.isClientSide) {
                int count = 1 + this.random.nextInt(2);

                for (int j = 0; j < count; ++j) {
                    EntityItem feather = this.dropItem(new ItemStack(Items.FEATHER_CHICKEN, 1), 1.0F);
                    feather.yd += this.random.nextFloat() * 0.05F;
                    feather.xd += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
                    feather.zd += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
                }

                heldItem.damageItem(1, player);
                if (heldItem.stackSize <= 0) {
                    player.destroyCurrentEquippedItem();
                }

                this.hurt(player, 1, DamageType.COMBAT);
                return true;
            } else {
                return false;
            }
        }
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.age.readTag(tag);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.age.writeTag(tag);
    }

    @Override
    public void causeFallDamage(float distance) {
        // No fall damage
    }

    @Override
    public int getAmbientSoundInterval() {
        return 60;
    }

    @Override
    public float getSoundVolume() {
        return 0.5f;
    }

    @Override
    public String getLivingSound() {
        return MOD_ID + ":mob.duck.idle";
    }

    @Override
    public String getHurtSound() {
        return MOD_ID + ":mob.duck.hurt";
    }

    @Override
    public String getDeathSound() {
        return MOD_ID + ":mob.duck.death";
    }

    @Override
    public boolean isFavouriteItem(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem().hasTag(ItemTags.CHICKENS_FAVOURITE_ITEM);
    }

}

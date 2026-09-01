package luke.stardew.entities.duck;

import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.entity.projectile.Projectile;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

public class ProjectileEggDuck extends Projectile {
    public ProjectileEggDuck(World world) {
        super(world);
        this.modelItem = StardewItems.EGG_DUCK;
    }

    public ProjectileEggDuck(World world, Player player) {
        super(world, player);
        this.modelItem = StardewItems.EGG_DUCK;
    }

    public ProjectileEggDuck(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
        this.modelItem = StardewItems.EGG_DUCK;
    }

    @Override
    public void onHit(@NotNull HitResult hitResult) {
        if (!this.world.isClientSide && this.random.nextInt(8) == 0) {
            int byte0 = 1;
            if (this.random.nextInt(32) == 0) {
                byte0 = 2;
            }
            for (int k = 0; k < byte0; ++k) {
                MobDuck entityduck = new MobDuck(this.world);
                entityduck.moveTo(this.x, this.y, this.z, this.yRot, 0.0f);
                this.world.entityJoinedWorld(entityduck);
            }
        }
        super.onHit(hitResult);
    }
}

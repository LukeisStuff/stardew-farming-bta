package luke.stardew.entities;

import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.projectile.Projectile;
import net.minecraft.core.world.World;

public class ProjectileTomato extends Projectile {
    public ProjectileTomato(World world) {
        super(world);
        this.modelItem = StardewItems.TOMATO;
    }

    public ProjectileTomato(World world, Mob owner) {
        super(world, owner);
        this.modelItem = StardewItems.TOMATO;
    }

    public ProjectileTomato(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.modelItem = StardewItems.TOMATO;
    }
}

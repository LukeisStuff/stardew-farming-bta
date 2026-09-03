package luke.stardew.entities.duck;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.entity.EntityTracker;
import net.minecraft.core.net.entity.EntityTrackerEntry;
import net.minecraft.core.net.entity.ITrackedEntry;
import net.minecraft.core.net.entity.IVehicleEntry;
import net.minecraft.core.net.packet.PacketAddEntity;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NetEntryEggDuck implements IVehicleEntry<ProjectileEggDuck>, ITrackedEntry<ProjectileEggDuck> {
    public @NotNull Class<ProjectileEggDuck> getAppliedClass() {
        return ProjectileEggDuck.class;
    }

    public int getTrackingDistance() {
        return 64;
    }

    @Override
    public int getMovementPacketDelay() {
        return 10;
    }

    public boolean sendMotionUpdates() {
        return true;
    }

    public void onEntityTracked(EntityTracker tracker, EntityTrackerEntry trackerEntry, ProjectileEggDuck trackedObject) {
        /*no need*/
    }

    public Entity getEntity(World world, double x, double y, double z, int metadata, boolean hasVelocity, double xd, double yd, double zd, Entity owner, @Nullable CompoundTag tag) {
        return new ProjectileEggDuck(world, x, y, z);
    }

    public PacketAddEntity getSpawnPacket(EntityTrackerEntry tracker, ProjectileEggDuck trackedObject) {
        return new PacketAddEntity(trackedObject);
    }
}

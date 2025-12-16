package luke.stardew;

import net.minecraft.core.net.packet.PacketAddParticle;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.net.PlayerList;
import turniplabs.halplibe.helper.EnvironmentHelper;

import java.util.Random;

public class StardewParticleMaker {
    private static final Random random = new Random();

    public static void spawnParticle(World world, String particleKey, double x, double y, double z, double motionX, double motionY, double motionZ, int data, double maxDistance) {
        if (EnvironmentHelper.isClientWorld()) return;

        if (EnvironmentHelper.isServerEnvironment()) {
            PlayerList playerList = MinecraftServer.getInstance().playerList;

            playerList.sendPacketToAllPlayersInDimension(
                new PacketAddParticle(particleKey, x, y, z, motionX, motionY, motionZ, data, maxDistance),
                world.dimension.id
            );

            return;
        }

        world.spawnParticle(particleKey, x, y, z, motionX, motionY, motionZ, data, maxDistance);
    }

    public static void spawnParticle(World world, String particleKey, double x, double y, double z, double motionX, double motionY, double motionZ, int data) {
        spawnParticle(world, particleKey, x, y, z, motionX, motionY, motionZ, data, 16D);
    }

    public static void spawnBlockBreakParticles(World world, String particleKey, int blockX, int blockY, int blockZ) {
        for (int i = 0; i < 16; ++i) {
            Direction face = Direction.values()[random.nextInt(6)];

            double faceX = blockX + 0.5 + (random.nextDouble() * 0.6 - 0.3);
            double faceY = blockY + 0.5 + (random.nextDouble() * 0.6 - 0.3);
            double faceZ = blockZ + 0.5 + (random.nextDouble() * 0.6 - 0.3);

            double offX = face.getOffsetX() * (random.nextDouble() * 0.3);
            double offY = face.getOffsetY() * (random.nextDouble() * 0.3);
            double offZ = face.getOffsetZ() * (random.nextDouble() * 0.3);

            double spawnX = faceX + offX;
            double spawnY = faceY + offY;
            double spawnZ = faceZ + offZ;

            double vx = offX * 0.3 + (random.nextDouble() - 0.5) * 0.2;
            double vy = offY * 0.3 + (random.nextDouble() - 0.5) * 0.2;
            double vz = offZ * 0.3 + (random.nextDouble() - 0.5) * 0.2;

            spawnParticle(world, particleKey, spawnX, spawnY, spawnZ, vx, vy, vz, 0);
        }
    }
}

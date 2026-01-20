package luke.stardew;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import teamport.aether.AetherMod;

import java.lang.reflect.Field;

public class StardewTags {

    public static Tag<Block<?>> NEARBY_CROP = Tag.of("nearby_crop");

    static {
        for (Field field : StardewTags.class.getDeclaredFields()) {
            if (field.getType().equals(Tag.class)) {
                try {
                    @SuppressWarnings("unchecked")
                    Tag<Block<?>> tag = (Tag<Block<?>>) field.get(null);
                    BlockTags.TAG_LIST.add(tag);
                } catch (Exception e) {
                    AetherMod.LOGGER.error("Failed to add tag '{}'!", field.getName(), e);
                }
            }
        }
    }
}

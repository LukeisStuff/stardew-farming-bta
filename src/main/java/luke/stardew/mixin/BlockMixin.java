package luke.stardew.mixin;

import luke.stardew.helper.NamespaceObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.collection.NamespaceID;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class BlockMixin implements NamespaceObject {
    @Shadow @Final private @NotNull NamespaceID namespaceID;

    @Override
    public NamespaceID id() {
        return this.namespaceID;
    }

    @Override
    public String cleanValue() {
        return this.namespaceID.value().replaceFirst("block/", "");
    }
}

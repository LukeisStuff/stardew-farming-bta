package luke.stardew.helper;

import net.minecraft.core.util.collection.NamespaceID;

public interface NamespaceObject {
    NamespaceID id();

    String cleanValue();
}

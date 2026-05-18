package luke.stardew.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemTool;
import net.minecraft.core.item.tool.ItemToolHoe;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ItemToolHoe.class, remap = false)
public abstract class ItemToolHoeMixin extends ItemTool {

    protected ItemToolHoeMixin(String name, String namespaceID, int id, int damageDealt, ToolMaterial toolMaterial, Tag<Block<?>> tagEffectiveAgainst) {
        super(name, namespaceID, id, damageDealt, toolMaterial, tagEffectiveAgainst);
    }

    @Definition(id = "setBlockTypeDataNotify", method = "Lnet/minecraft/core/world/World;setBlockTypeDataNotify(Lnet/minecraft/core/world/pos/TilePosc;Lnet/minecraft/core/block/Block;I)Z")
    @Definition(id = "FARMLAND_DIRT", field = "Lnet/minecraft/core/block/Blocks;FARMLAND_DIRT:Lnet/minecraft/core/block/Block;")
    @Expression("?.setBlockTypeDataNotify(?, FARMLAND_DIRT, ?)")
    @WrapOperation(method = "till", at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean onUseOnBlock(World instance, @NotNull TilePosc tilePos, @NotNull Block<?> block, int data, Operation<Boolean> original) {
        if (instance.rand.nextInt(10) == 0) {
            instance.dropItem(tilePos.up(new TilePos()), new ItemStack(StardewItems.WORM, instance.rand.nextInt(2) + 2) );
        }

        return original.call(instance, tilePos, block, data);
    }
}

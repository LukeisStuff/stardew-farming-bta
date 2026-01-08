package luke.stardew.blocks.beehive;

import com.google.gson.*;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.item.ItemStack;

import java.lang.reflect.Type;

public class RecipeBeehiveJsonAdapter implements RecipeJsonAdapter<RecipeEntryBeehive> {
    public RecipeEntryBeehive deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        RecipeSymbol a = context.deserialize(obj.get("a").getAsJsonObject(), RecipeSymbol.class);
        ItemStack output = context.deserialize(obj.get("output").getAsJsonObject(), ItemStack.class);
        if (obj.has("b")) {
            RecipeSymbol b = context.deserialize(obj.get("b").getAsJsonObject(), RecipeSymbol.class);
            return new RecipeEntryBeehive(new RecipeSymbol[]{a, b}, output);
        } else {
            return new RecipeEntryBeehive(new RecipeSymbol[]{a}, output);
        }
    }

    public JsonElement serialize(RecipeEntryBeehive src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", src.toString());
        obj.addProperty("type", Registries.RECIPE_TYPES.getKey(src.getClass()));
        RecipeSymbol[] input = src.getInput();
        obj.add("a", context.serialize(input[0]));
        obj.add("output", context.serialize(src.getOutput()));
        if (input.length == 2) {
            obj.add("b", context.serialize(input[1]));
        }

        return obj;
    }
}

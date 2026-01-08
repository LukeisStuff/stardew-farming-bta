package luke.stardew.blocks.beehive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.guidebook.GuidebookPage;
import net.minecraft.client.gui.guidebook.GuidebookSection;
import net.minecraft.client.gui.guidebook.SearchableGuidebookSection;
import net.minecraft.client.gui.guidebook.smelting.RecipePageBlastSmelting;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.SearchQuery;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.Pair;
import net.minecraft.core.util.helper.MathHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Environment(EnvType.CLIENT)
public class GuidebookSectionBeehive extends SearchableGuidebookSection {
    private final List<GuidebookPage> pages = new ArrayList<>();
    private Pair<String, List<GuidebookPage>> filteredPages = null;

    public GuidebookSectionBeehive(String translationKey, ItemStack tabIcon, int bgColor, int fgColor) {
        super(translationKey, tabIcon, bgColor, fgColor);
        this.reloadRecipes();
    }

    public void reloadRecipes() {
        this.pages.clear();
        List<RecipeEntryBlastFurnace> allRecipes = new ArrayList<>(Registries.RECIPES.getAllBlastFurnaceRecipes());
        allRecipes.removeIf(Objects::isNull);
        int totalRecipes = allRecipes.size();
        int totalPages = MathHelper.ceilInt(totalRecipes, 6);

        for (int i = 0; i < totalPages; ++i) {
            int j = i * 6;
            ArrayList<RecipeEntryBlastFurnace> recipes = new ArrayList<>(allRecipes.subList(Math.min(j, totalRecipes), Math.min(j + 6, totalRecipes)));
            this.pages.add(new RecipePageBlastSmelting(this, recipes));
        }

    }

    public List<GuidebookPage> searchPages(SearchQuery query) {
        if (this.filteredPages != null && Objects.equals(this.filteredPages.getLeft(), query.rawQuery)) {
            return this.filteredPages.getRight();
        } else {
            ArrayList<RecipeEntryBlastFurnace> filteredRecipes = new ArrayList<>();
            List<RecipeEntryBlastFurnace> allRecipes = new ArrayList<>(Registries.RECIPES.getAllBlastFurnaceRecipes());
            allRecipes.removeIf(Objects::isNull);

            for (RecipeEntryBlastFurnace recipe : allRecipes) {
                if (recipe.matchesQueryIgnoreExceptions(query)) {
                    filteredRecipes.add(recipe);
                }
            }

            ArrayList<GuidebookPage> filteredPages = new ArrayList<>();
            int filteredRecipeSize = filteredRecipes.size();
            int filteredPageCount = MathHelper.ceilInt(filteredRecipeSize, 3);

            for (int i = 0; i < filteredPageCount; ++i) {
                int j = i * 6;
                ArrayList<RecipeEntryBlastFurnace> recipes = new ArrayList<>(filteredRecipes.subList(Math.min(j, filteredRecipeSize), Math.min(j + 6, filteredRecipeSize)));
                if (!recipes.isEmpty()) {
                    filteredPages.add(new RecipePageBlastSmelting(this, recipes));
                }
            }

            this.filteredPages = Pair.of(query.rawQuery, filteredPages);
            return filteredPages;
        }
    }

    public List<GuidebookPage> getPages() {
        return this.pages;
    }

    public List<GuidebookSection.Index> getIndices() {
        return Collections.emptyList();
    }
}

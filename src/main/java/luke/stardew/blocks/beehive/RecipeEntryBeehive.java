package luke.stardew.blocks.beehive;

import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.*;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeEntryBeehive extends RecipeEntryBase<RecipeSymbol[], ItemStack, Void> implements HasJsonAdapter {
    public RecipeEntryBeehive(RecipeSymbol[] input, ItemStack output) {
        super(input, output, null);
    }

    public RecipeEntryBeehive() {
    }

    @Override
    public Void getData() {
        return null;
    }

    @Override
    public boolean containsData(Void data) {
        return false;
    }

    public boolean matches(ItemStack left, ItemStack right) {
        RecipeSymbol[] input = this.getInput();
        if (input.length == 1) {
            return input[0].matches(left) && right == null || input[0].matches(right) && left == null;
        } else if (input.length != 2) {
            return false;
        } else {
            return input[0].matches(left) && input[1].matches(right) || input[0].matches(right) && input[1].matches(left);
        }
    }

    public boolean matchesQueryIgnoreExceptions(SearchQuery query) {
        try {
            return this.matchesQuery(query);
        } catch (IllegalArgumentException | NullPointerException var3) {
            return false;
        }
    }

    public boolean matchesQuery(SearchQuery query) {
        switch (query.mode) {
            case ALL:
                if ((this.matchesRecipe(query) || this.matchesUsage(query)) && this.matchesScope(query)) {
                    return true;
                }
                break;
            case RECIPE:
                if (this.matchesRecipe(query) && this.matchesScope(query)) {
                    return true;
                }
                break;
            case USAGE:
                if (this.matchesUsage(query) && this.matchesScope(query)) {
                    return true;
                }
        }

        return false;
    }

    private boolean matchesRecipe(SearchQuery query) {
        if (query.query.getLeft() == SearchQuery.QueryType.NAME) {
            if (query.strict && this.getOutput().getDisplayName().equalsIgnoreCase(query.query.getRight())) {
                return true;
            }
            return !query.strict && this.getOutput().getDisplayName().toLowerCase().contains(query.query.getRight().toLowerCase());
        } else if (query.query.getLeft() == SearchQuery.QueryType.GROUP && !Objects.equals(query.query.getRight(), "")) {
            List<ItemStack> groupStacks = (new RecipeSymbol(query.query.getRight())).resolve();
            if (groupStacks == null) {
                return false;
            }

            return groupStacks.contains(this.getOutput());
        }

        return false;
    }

    private boolean matchesUsage(SearchQuery query) {
        List<ItemStack> stacks = new ArrayList<>();
        RecipeSymbol[] input = this.getInput();

        for (RecipeSymbol symbol : input) {
            stacks.addAll(symbol.resolve());
        }

        for (ItemStack stack : stacks) {
            if (stack != null) {
                if (query.query.getLeft() == SearchQuery.QueryType.NAME) {
                    if (query.strict && stack.getDisplayName().equalsIgnoreCase(query.query.getRight())) {
                        return true;
                    }

                    if (!query.strict && stack.getDisplayName().toLowerCase().contains(query.query.getRight().toLowerCase())) {
                        return true;
                    }
                } else if (query.query.getLeft() == SearchQuery.QueryType.GROUP && !Objects.equals(query.query.getRight(), "")) {
                    List<ItemStack> groupStacks = (new RecipeSymbol(query.query.getRight())).resolve();
                    if (groupStacks == null) {
                        return false;
                    }

                    return groupStacks.contains(this.getOutput());
                }
            }
        }

        return false;
    }

    private boolean matchesScope(SearchQuery query) {
        if (query.scope.getLeft() == SearchQuery.SearchScope.NONE) {
            return true;
        } else {
            if (query.scope.getLeft() == SearchQuery.SearchScope.NAMESPACE) {
                RecipeNamespace namespace = Registries.RECIPES.getItem(query.scope.getRight());
                return namespace == this.parent.getParent();
            } else if (query.scope.getLeft() == SearchQuery.SearchScope.NAMESPACE_GROUP) {
                RecipeGroup<?> group;
                try {
                    group = Registries.RECIPES.getGroupFromKey(query.scope.getRight());
                } catch (IllegalArgumentException var4) {
                    group = null;
                }

                return group == this.parent;
            }

            return false;
        }
    }

    public RecipeJsonAdapter<?> getAdapter() {
        return new RecipeBeehiveJsonAdapter();
    }
}

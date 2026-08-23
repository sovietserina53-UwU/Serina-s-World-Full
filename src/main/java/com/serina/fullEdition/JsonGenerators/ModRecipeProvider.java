package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class runner extends RecipeProvider.Runner{
        public runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider,recipeOutput);
        }

        @Override
        public String getName() {
            return "Serinas Recipes";
        }
    }

    @Override
    protected void buildRecipes() {

//sugar cane
        shaped(RecipeCategory.MISC,ModBlocks.SUGAR_CANE_BLOCK).
                pattern("BBB").
                pattern("BBB").
                pattern("BBB").
                define('B',Items.SUGAR_CANE).
                unlockedBy(getHasName(Items.SUGAR_CANE),has(Items.SUGAR_CANE)).
                save(output);
        shapeless(RecipeCategory.MISC,Items.SUGAR_CANE,9).
                requires(ModBlocks.SUGAR_CANE_BLOCK).
                unlockedBy(getHasName(ModBlocks.SUGAR_CANE_BLOCK),has(ModBlocks.SUGAR_CANE_BLOCK)).
                save(output,"serinasworldfulledition:sugar_cane_from_the_block");

//salt
        shaped(RecipeCategory.MISC,ModBlocks.SALT_BLOCK).
                pattern("BB ").
                pattern("BB ").
                pattern("   " +
                        "").
                define('B',ModItems.SALT).
                unlockedBy(getHasName(ModItems.SALT),has(ModItems.SALT)).
                save(output);
        shapeless(RecipeCategory.MISC,ModItems.SALT,4).
                requires(ModBlocks.SALT_BLOCK).
                unlockedBy(getHasName(ModBlocks.SALT_BLOCK),has(ModBlocks.SALT_BLOCK)).
                save(output,"serinasworldfulledition:salt_from_the_block");


//rubber Log and rubber wood
        shaped(RecipeCategory.MISC,ModBlocks.RUBBER_WOOD,4).
                pattern("BB ").
                pattern("BB ").
                pattern("   " +
                        "").
                define('B',ModBlocks.RUBBER_LOG).
                unlockedBy(getHasName(ModBlocks.RUBBER_LOG),has(ModBlocks.RUBBER_LOG)).
                save(output);
        shaped(RecipeCategory.MISC,ModBlocks.STRIPPED_RUBBER_WOOD,4).
                pattern("BB ").
                pattern("BB ").
                pattern("   " +
                        "").
                define('B',ModBlocks.STRIPPED_RUBBER_LOG).
                unlockedBy(getHasName(ModBlocks.RUBBER_LOG),has(ModBlocks.RUBBER_LOG)).
                save(output);
        shaped(RecipeCategory.MISC,ModBlocks.RUBBER_LOG,4).
                pattern("BB ").
                pattern("BB ").
                pattern("   " +
                        "").
                define('B',ModBlocks.RUBBER_WOOD).
                unlockedBy(getHasName(ModBlocks.RUBBER_WOOD),has(ModBlocks.RUBBER_WOOD)).
                save(output);
        shapeless(RecipeCategory.MISC,ModBlocks.RUBBER_PLANKS,4).
                requires(ModBlocks.RUBBER_LOG).
                unlockedBy(getHasName(ModBlocks.RUBBER_LOG),has(ModBlocks.RUBBER_LOG)).
                save(output);
        shapeless(RecipeCategory.MISC,ModBlocks.RUBBER_PLANKS,4).
                requires(ModBlocks.STRIPPED_RUBBER_LOG).
                unlockedBy(getHasName(ModBlocks.STRIPPED_RUBBER_LOG),has(ModBlocks.STRIPPED_RUBBER_LOG)).
                save(output);
//rubber planks
//knifes (might be temporary)
        shaped(RecipeCategory.TOOLS,ModItems.RUDIMENTARY_BLADE).
                pattern("  C").
                pattern(" B ").
                pattern("   " +
                        "").
                define('B',Items.STICK).
                define('C',ModItems.SHARP_PEBBLE).
                unlockedBy(getHasName(Items.STICK),has(Items.STICK)).
                unlockedBy(getHasName(ModItems.SHARP_PEBBLE),has(ModItems.SHARP_PEBBLE)).
                save(output);
        shaped(RecipeCategory.TOOLS,ModItems.IRON_KNIFE).
                pattern("  C").
                pattern(" B ").
                pattern("   " +
                        "").
                define('B',Items.STICK).
                define('C',Items.IRON_INGOT).
                unlockedBy(getHasName(Items.STICK),has(Items.STICK)).
                unlockedBy(getHasName(Items.IRON_INGOT),has(Items.IRON_INGOT)).
                save(output);






    }
}

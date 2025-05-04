package com.dave08.idcards.datagen;

import com.dave08.idcards.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    /*private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(),
            ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.NETHER_SAPPHIRE_ORE.get(),
            ModBlocks.END_STONE_SAPPHIRE_ORE.get());*/

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    @SuppressWarnings("deprication")
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        System.out.println("Generating recipes");
        //oreSmelting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUE_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BROWN_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.BROWN_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CYAN_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GRAY_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GREEN_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_BLUE_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_GRAY_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.LIGHT_GRAY_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIME_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.LIME_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGENTA_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.MAGENTA_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ORANGE_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.PINK_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PURPLE_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.PURPLE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RED_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.RED_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WHITE_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.YELLOW_IDCARD.get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);

        // Lets you get the id card in the first place
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WHITE_IDCARD.get())
                .pattern(" S ")
                .pattern(" R ")
                .pattern(" I ")
                .define('S', Items.INK_SAC)
                .define('R', Items.REDSTONE)
                .define('I', Items.IRON_NUGGET)
                .unlockedBy("has_special_core", has(Items.IRON_NUGGET))
                .save(pWriter, new ResourceLocation("idcards", "white_idcard_from_sri"));
    } // ResourceLocation("{mod_id}", "{desired_recipe_name}") will set the recipe name to {desired_recipe_name} for items  with multiple recipes

    /*protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  IDCards.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }*/
}
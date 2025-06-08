package com.dave08.idcards.datagen;

import com.dave08.idcards.block.ModBlocks;
import com.dave08.idcards.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    @SuppressWarnings("deprication")
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        System.out.println("Generating recipes");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.BLACK).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.BLUE).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.BROWN).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.BROWN_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.CYAN).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.GRAY).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.GREEN).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.LIGHT_BLUE).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.LIGHT_GRAY).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.LIGHT_GRAY_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.LIME).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.LIME_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.MAGENTA).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.MAGENTA_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.ORANGE).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.PINK).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.PINK_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.PURPLE).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.PURPLE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.RED).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.RED_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.WHITE).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.YELLOW).get())
                .requires(ItemTags.create(new ResourceLocation("idcards", "idcards")))
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_idcard", has(ItemTags.create(new ResourceLocation("idcards", "idcards"))))
                .save(pWriter);

        // Lets you get the ID card in the first place
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IDCARDS.get(DyeColor.WHITE).get())
                .pattern(" S ")
                .pattern(" R ")
                .pattern(" I ")
                .define('S', Items.INK_SAC)
                .define('R', Items.REDSTONE)
                .define('I', Items.IRON_NUGGET)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(pWriter, new ResourceLocation("idcards", "white_idcard_from_sri"));

        /*ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCANNING_CIRCUIT.get())
                .pattern("   ")
                .pattern(" R ")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(pWriter);/**/

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.IDCARD_READER.get())
                .pattern(" I ")
                .pattern("IRI")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(pWriter);
    } // .save(pWriter, new ResourceLocation("{mod_id}", "{desired_recipe_name}")); will set the recipe name to {desired_recipe_name} for items  with multiple recipes
}
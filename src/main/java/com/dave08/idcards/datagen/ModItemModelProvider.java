package com.dave08.idcards.datagen;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IDCards.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.IDCARDS.get(DyeColor.BLACK));
        simpleItem(ModItems.IDCARDS.get(DyeColor.BLUE));
        simpleItem(ModItems.IDCARDS.get(DyeColor.BROWN));
        simpleItem(ModItems.IDCARDS.get(DyeColor.CYAN));
        simpleItem(ModItems.IDCARDS.get(DyeColor.GRAY));
        simpleItem(ModItems.IDCARDS.get(DyeColor.GREEN));
        simpleItem(ModItems.IDCARDS.get(DyeColor.LIGHT_BLUE));
        simpleItem(ModItems.IDCARDS.get(DyeColor.LIGHT_GRAY));
        simpleItem(ModItems.IDCARDS.get(DyeColor.LIME));
        simpleItem(ModItems.IDCARDS.get(DyeColor.MAGENTA));
        simpleItem(ModItems.IDCARDS.get(DyeColor.ORANGE));
        simpleItem(ModItems.IDCARDS.get(DyeColor.PINK));
        simpleItem(ModItems.IDCARDS.get(DyeColor.PURPLE));
        simpleItem(ModItems.IDCARDS.get(DyeColor.RED));
        simpleItem(ModItems.IDCARDS.get(DyeColor.WHITE));
        simpleItem(ModItems.IDCARDS.get(DyeColor.YELLOW));

        simpleItem(ModItems.SCANNING_CIRCUIT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IDCards.MOD_ID,"item/" + item.getId().getPath()));
    }
}
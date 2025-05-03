package com.dave08.idcards.datagen;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
        simpleItem(ModItems.BLACK_IDCARD);
        simpleItem(ModItems.BLUE_IDCARD);
        simpleItem(ModItems.BROWN_IDCARD);
        simpleItem(ModItems.CYAN_IDCARD);
        simpleItem(ModItems.GRAY_IDCARD);
        simpleItem(ModItems.GREEN_IDCARD);
        simpleItem(ModItems.LIGHT_BLUE_IDCARD);
        simpleItem(ModItems.LIGHT_GRAY_IDCARD);
        simpleItem(ModItems.LIME_IDCARD);
        simpleItem(ModItems.MAGENTA_IDCARD);
        simpleItem(ModItems.ORANGE_IDCARD);
        simpleItem(ModItems.PINK_IDCARD);
        simpleItem(ModItems.PURPLE_IDCARD);
        simpleItem(ModItems.RED_IDCARD);
        simpleItem(ModItems.WHITE_IDCARD);
        simpleItem(ModItems.YELLOW_IDCARD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IDCards.MOD_ID,"item/" + item.getId().getPath()));
    }
}
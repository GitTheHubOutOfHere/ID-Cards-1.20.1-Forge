package com.dave08.idcards.client.screen;

import com.dave08.idcards.block.entity.menu.ModMenus;
import com.dave08.idcards.block.entity.menu.IDCardReaderMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = "idcards", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModScreens {
    public static void register(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.ID_CARD_READER_MENU.get(), IDCardReaderScreen::new);
        });
    }
}
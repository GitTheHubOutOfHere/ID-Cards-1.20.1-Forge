package com.dave08.idcards.block.entity.menu;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.entity.menu.IDCardReaderMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, IDCards.MOD_ID);

    public static final RegistryObject<MenuType<IDCardReaderMenu>> ID_CARD_READER_MENU =
            MENUS.register("id_card_reader_menu", () ->
                    IForgeMenuType.create(IDCardReaderMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
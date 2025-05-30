package com.dave08.idcards.item;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.item.custom.idcardItem;
import com.dave08.idcards.item.custom.idcardItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IDCards.MOD_ID);

    // The ID CARD STACK! *fake scream*
    public static final Map<DyeColor, RegistryObject<Item>> IDCARDS = new EnumMap<>(DyeColor.class);

    static {
        for (DyeColor color : DyeColor.values()) {
            RegistryObject<Item> idcard = ITEMS.register(color.getName() + "_idcard",
                    () -> new idcardItem(color, new Item.Properties().stacksTo(1)));
            IDCARDS.put(color, idcard);
        }
    }

    public static final RegistryObject<Item> SCANNING_CIRCUIT = ITEMS.register("scanning_circuit",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

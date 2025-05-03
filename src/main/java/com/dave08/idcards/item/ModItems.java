package com.dave08.idcards.item;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.item.custom.coloredidcards.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IDCards.MOD_ID);

    // The ID CARD STACK! *fake scream*
    public static final RegistryObject<Item> BLACK_IDCARD = ITEMS.register("black_idcard",
            () -> new blackIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLUE_IDCARD = ITEMS.register("blue_idcard",
            () -> new blueIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BROWN_IDCARD = ITEMS.register("brown_idcard",
            () -> new brownIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CYAN_IDCARD = ITEMS.register("cyan_idcard",
            () -> new cyanIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GRAY_IDCARD = ITEMS.register("gray_idcard",
            () -> new grayIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GREEN_IDCARD = ITEMS.register("green_idcard",
            () -> new greenIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LIGHT_BLUE_IDCARD = ITEMS.register("light_blue_idcard",
            () -> new lightBlueIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LIGHT_GRAY_IDCARD = ITEMS.register("light_gray_idcard",
            () -> new lightGrayIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LIME_IDCARD = ITEMS.register("lime_idcard",
            () -> new limeIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MAGENTA_IDCARD = ITEMS.register("magenta_idcard",
            () -> new magentaIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ORANGE_IDCARD = ITEMS.register("orange_idcard",
            () -> new orangeIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PINK_IDCARD = ITEMS.register("pink_idcard",
            () -> new pinkIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PURPLE_IDCARD = ITEMS.register("purple_idcard",
            () -> new purpleIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> RED_IDCARD = ITEMS.register("red_idcard",
            () -> new redIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHITE_IDCARD = ITEMS.register("white_idcard",
            () -> new whiteIdcardItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> YELLOW_IDCARD = ITEMS.register("yellow_idcard",
            () -> new yellowIdcardItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

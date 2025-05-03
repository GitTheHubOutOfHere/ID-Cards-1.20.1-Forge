package com.dave08.idcards.item;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IDCards.MOD_ID);

    //Add a creative mod tab called "ID Cards" (en_us.json creativetab.idcards.idcards_tab.name)
    public static final RegistryObject<CreativeModeTab> IDCARD_TAB = CREATIVE_MODE_TABS.register("idcard_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RED_IDCARD.get()))
                    .title(Component.translatable("creativetab.idcards.idcards_tab.name"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // The ID CARD STACK! *still pretty fake scream*
                        output.accept(ModBlocks.IDCARD_READER.get());
                        output.accept(ModItems.BLACK_IDCARD.get());
                        output.accept(ModItems.BLUE_IDCARD.get());
                        output.accept(ModItems.BROWN_IDCARD.get());
                        output.accept(ModItems.CYAN_IDCARD.get());
                        output.accept(ModItems.GRAY_IDCARD.get());
                        output.accept(ModItems.GREEN_IDCARD.get());
                        output.accept(ModItems.LIGHT_BLUE_IDCARD.get());
                        output.accept(ModItems.LIGHT_GRAY_IDCARD.get());
                        output.accept(ModItems.LIME_IDCARD.get());
                        output.accept(ModItems.MAGENTA_IDCARD.get());
                        output.accept(ModItems.ORANGE_IDCARD.get());
                        output.accept(ModItems.PINK_IDCARD.get());
                        output.accept(ModItems.PURPLE_IDCARD.get());
                        output.accept(ModItems.RED_IDCARD.get());
                        output.accept(ModItems.WHITE_IDCARD.get());
                        output.accept(ModItems.YELLOW_IDCARD.get());
                        //output.accept(Items.DIAMOND); For adding vannilla items
                    }).build());/**/

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

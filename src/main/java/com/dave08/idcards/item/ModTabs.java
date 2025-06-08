package com.dave08.idcards.item;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.IDCARDS.get(DyeColor.RED).get()))
                    .title(Component.translatable("creativetab.idcards.idcards_tab.name"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // The ID CARD STACK! *still pretty fake scream*
                        output.accept(ModBlocks.IDCARD_READER.get());
                        //output.accept(ModItems.SCANNING_CIRCUIT.get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.BLACK).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.BLUE).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.BROWN).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.CYAN).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.GRAY).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.GREEN).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.LIGHT_BLUE).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.LIGHT_GRAY).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.LIME).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.MAGENTA).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.ORANGE).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.PINK).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.PURPLE).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.RED).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.WHITE).get());
                        output.accept(ModItems.IDCARDS.get(DyeColor.YELLOW).get());
                        //output.accept(Items.DIAMOND); For adding vannilla items
                    }).build());/**/

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

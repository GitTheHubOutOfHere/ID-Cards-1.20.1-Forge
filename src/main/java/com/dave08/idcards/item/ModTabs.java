package com.dave08.idcards.item;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IDCards.MOD_ID);

    //Add a creative mod tab called "ID Cards" (en_us.json creativetab.idcards_tab)
    public static final RegistryObject<CreativeModeTab> IDCARD_TAB = CREATIVE_MODE_TABS.register("idcard_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.IDCARD.get()))
                    .title(Component.translatable("creativetab.idcards_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.IDCARD.get());
                        output.accept(ModBlocks.IDCARD_READER.get());
                        //output.accept(Items.DIAMOND); For adding vannilla items
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

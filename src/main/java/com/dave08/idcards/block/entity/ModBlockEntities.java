package com.dave08.idcards.block.entity;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.entity.*;
import com.dave08.idcards.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IDCards.MOD_ID);

    public static final RegistryObject<BlockEntityType<IdcardReaderBlockEntity>> IDCARD_READER_BE =
            BLOCK_ENTITIES.register("idcard_reader_be", () ->
                    BlockEntityType.Builder.of(IdcardReaderBlockEntity::new,
                            ModBlocks.IDCARD_READER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

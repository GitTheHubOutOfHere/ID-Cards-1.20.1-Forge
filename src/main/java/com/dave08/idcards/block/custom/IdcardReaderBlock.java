package com.dave08.idcards.block.custom;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.entity.IdcardReaderBlockEntity;
import com.dave08.idcards.block.entity.menu.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

public class IdcardReaderBlock extends Block implements EntityBlock {

    public IdcardReaderBlock(Properties properties) {
        super(properties);
        //IDCards.LOGGER.info("1");
    }



    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            //IDCards.LOGGER.info("2");
            var be = level.getBlockEntity(pos);
            //IDCards.LOGGER.info("Held item NBT: {}", player.getItemInHand(hand).getTag().get("OwnerUUID"));
            if (!(be instanceof IdcardReaderBlockEntity blockEntity)) return InteractionResult.FAIL;

            if (player.isShiftKeyDown()) {
                MenuProvider provider = state.getMenuProvider(level, pos);
                if (provider != null && player instanceof ServerPlayer serverPlayer) {
                    //IDCards.LOGGER.info(ModMenus.ID_CARD_READER_MENU.get().toString());
                    NetworkHooks.openScreen(serverPlayer, provider, pos);
                }
            } else {
                ItemStack stack = player.getItemInHand(hand);
                //IDCards.LOGGER.info("4");
                if (!stack.isEmpty() && stack.getItem().getDescriptionId().equals("item.idcards.idcard") &&
                        stack.hasTag() && stack.getTag().contains("OwnerUUID")) {
                    //IDCards.LOGGER.info("Scanned ownerUUID: " + stack.getTag().getUUID("OwnerUUID").toString());
                    UUID uuid = stack.getTag().getUUID("OwnerUUID");
                    if (be instanceof IdcardReaderBlockEntity reader) {
                        if (reader.getStoredUUIDs().contains(uuid)) {
                            if (player instanceof ServerPlayer serverPlayer) {
                                serverPlayer.displayClientMessage(
                                        Component.literal("Access Granted"/* + reader.getStoredUUIDs() + uuid/**/), true);
                            }
                        } else {
                            if (player instanceof ServerPlayer serverPlayer) {
                                serverPlayer.displayClientMessage(
                                        Component.literal("Access Denied"/* + reader.getStoredUUIDs() + uuid/**/), true);
                            }
                        }
                        //IDCards.LOGGER.info("Stored UUIDs:", reader.getStoredUUIDs(), uuid);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        var be = level.getBlockEntity(pos);
        return (be instanceof MenuProvider provider) ? provider : null;
    }

    @Nullable
    @Override
    public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IdcardReaderBlockEntity(pos, state);
    }
}

package com.dave08.idcards.block.custom;

import com.dave08.idcards.ServerConfig;
import com.dave08.idcards.block.entity.IdcardReaderBlockEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

@SuppressWarnings("deprication")
public class IdcardReaderBlock extends Block implements EntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public IdcardReaderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(POWERED, false);
    }

    // Function to trigger a 1-tick redstone signal
    public void emitRedstonePulse(BlockState state, Level level, BlockPos pos) {
        if (!state.getValue(POWERED)) {
            BlockEntity be = level.getBlockEntity(pos);
            BlockState newState = state.setValue(POWERED, true);
            level.setBlock(pos, newState, Block.UPDATE_ALL);
            level.updateNeighborsAt(pos, this);
            for (Direction dir : Direction.values()) {
                level.updateNeighborsAt(pos.relative(dir), this);
            }
            /*if (level.getBlockEntity(pos) instanceof IdcardReaderBlockEntity reader) {
                level.scheduleTick(pos, this, reader.getPulseLength()); } else { */level.scheduleTick(pos, this, 2); //}
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof IdcardReaderBlockEntity) {
                Containers.dropContents(pLevel, pPos, ((IdcardReaderBlockEntity) blockentity).getContainer());
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @SuppressWarnings("deprication")
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED)) {
            BlockState newState = state.setValue(POWERED, false);
            level.setBlock(pos, newState, Block.UPDATE_ALL);
            level.updateNeighborsAt(pos, this);
            for (Direction dir : Direction.values()) {
                level.updateNeighborsAt(pos.relative(dir), this);
            }
        }
    }

    @SuppressWarnings("deprication")
    @Override
    public boolean isSignalSource(BlockState state) { return true;}

    @SuppressWarnings("deprication")
    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @SuppressWarnings("deprication")
    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return this.getSignal(state, level, pos, dir);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.getBlockEntity(pos) instanceof IdcardReaderBlockEntity reader) {
            reader.setOwner(placer.getUUID());
            if (stack.hasCustomHoverName()) { reader.setCustomName(stack.getHoverName().getString()); }
        }
    }

    @Override
    @SuppressWarnings("deprication")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            var be = level.getBlockEntity(pos);
            if (!(be instanceof IdcardReaderBlockEntity blockEntity)) return InteractionResult.FAIL;

            if (hit.getDirection() == ServerConfig.getTriggerFace()) {
                //Deny access to just anyone
                if (be instanceof IdcardReaderBlockEntity reader) {
                    if (!player.getUUID().equals(reader.getOwner()) && (!ServerConfig.allowTrustListToEdit || !reader.getStoredUUIDs().contains(player.getUUID()))) {
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.displayClientMessage(
                                Component.translatable("block.idcards.idcard_reader.no_edit_trust_list"),
                                true);
                        }
                        return InteractionResult.SUCCESS;
                    }
                }

                MenuProvider provider = state.getMenuProvider(level, pos);
                if (provider != null && player instanceof ServerPlayer serverPlayer) {
                    NetworkHooks.openScreen(serverPlayer, provider, pos);
                    if (ServerConfig.angerPiglinsOnOpenTrustList) { PiglinAi.angerNearbyPiglins(player, true); }
                }
            } else {
                ItemStack stack = player.getItemInHand(hand);
                if (!stack.isEmpty() && stack.is(ItemTags.create(new ResourceLocation("idcards", "idcards"))) &&
                        stack.hasTag() && stack.getTag().contains("OwnerUUID")) {
                    UUID uuid = stack.getTag().getUUID("OwnerUUID");
                    if (be instanceof IdcardReaderBlockEntity reader) {
                        if (uuid.equals(reader.getOwner()) || reader.getStoredUUIDs().contains(uuid)) {
                            if (player instanceof ServerPlayer serverPlayer) {
                                serverPlayer.displayClientMessage(
                                        Component.literal("Access Granted"), true);
                            }
                            emitRedstonePulse(state, level, pos);
                        } else {
                            if (player instanceof ServerPlayer serverPlayer) {
                                serverPlayer.displayClientMessage(
                                        Component.literal("Access Denied"), true);
                            }
                        }
                    }
                } else {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.displayClientMessage(
                                Component.translatable("block.idcards.idcard_reader.not_valid_id_card"), true);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    @SuppressWarnings("deprication")
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        var be = level.getBlockEntity(pos);
        return (be instanceof MenuProvider provider) ? provider : null;
    }

    @Nullable
    @Override
    public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IdcardReaderBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }
}
package com.dave08.idcards;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import com.dave08.idcards.block.entity.IdcardReaderBlockEntity;

import java.util.function.Supplier;

public class idcardReaderPacket {
    private final BlockPos pos;
    private final int pulseLength;

    public idcardReaderPacket(BlockPos pos, int pulseLength) {
        this.pos = pos;
        this.pulseLength = pulseLength;
    }

    public static void encode(idcardReaderPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeInt(msg.pulseLength);
    }

    public static idcardReaderPacket decode(FriendlyByteBuf buf) {
        return new idcardReaderPacket(buf.readBlockPos(), buf.readInt());
    }

    public static void handle(idcardReaderPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ServerLevel level = player.serverLevel();
            BlockEntity be = level.getBlockEntity(msg.pos);
            if (be instanceof IdcardReaderBlockEntity reader) {
                reader.setPulseLength(msg.pulseLength);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


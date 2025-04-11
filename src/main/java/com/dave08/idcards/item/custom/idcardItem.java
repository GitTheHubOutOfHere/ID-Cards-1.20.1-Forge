package com.dave08.idcards.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class idcardItem extends Item
{
    public idcardItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("OwnerUUID", Tag.TAG_INT_ARRAY)) {
            UUID storedUuid = tag.getUUID("OwnerUUID");
            if (storedUuid.equals(pPlayer.getUUID())) {
                // Player is the owner — clear the tag
                stack.setTag(null); // or stack.removeTagKey("OwnerUUID"); + remove other keys individually
                if (!pLevel.isClientSide) {
                    pPlayer.displayClientMessage(Component.translatable("item.idcards.idcard.actionBarMessageUnlinked")
                            .withStyle(ChatFormatting.RED), true);
                }
                return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide); // REQUIRED - Will not clear owner without
            }
        }

        // Else: set ownership
        tag.putUUID("OwnerUUID", pPlayer.getUUID());
        tag.putString("OwnerName", pPlayer.getName().getString());
        stack.setTag(tag);

        if (!pLevel.isClientSide())
        {
            pPlayer.displayClientMessage(Component.translatable("item.idcards.idcard.actionBarMessageLinked",
                    pPlayer.getName().getString()).withStyle(ChatFormatting.GREEN), true);
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.idcards.idcard.tooltip.base"));

        if (stack.hasTag() && stack.getTag().contains("OwnerName", Tag.TAG_STRING)) {
            Component ownerName = Component.literal(stack.getTag().getString("OwnerName")).withStyle(ChatFormatting.GREEN);
            tooltip.add(Component.translatable("item.idcards.idcard.tooltip.owner", ownerName));
        }
    }
}

package com.dave08.idcards.item.custom;

import com.dave08.idcards.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class idcardItem extends Item
{
    public idcardItem(Properties pProperties) { super(pProperties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        //BlockHitResult blockHit = getPlayerPOVHitResult(pLevel, pPlayer)
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("OwnerUUID", Tag.TAG_INT_ARRAY)) {
            UUID storedUuid = tag.getUUID("OwnerUUID");

            // Disallow non-owners to clear ownership
            if (!storedUuid.equals(pPlayer.getUUID()))
            {
                if (!pLevel.isClientSide) {
                    pPlayer.displayClientMessage(Component.translatable("item.idcards.idcard.actionBarMessage.NotOwner")
                            .withStyle(ChatFormatting.RED), true);
                }
                return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
            }

            // Player must crouch to clear data
            if (!pPlayer.isCrouching())
            {
                if (!pLevel.isClientSide) {
                    pPlayer.displayClientMessage(Component.translatable("item.idcards.idcard.actionBarMessage.AlreadyLinked")
                            .withStyle(ChatFormatting.WHITE), true);
                }
                return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
            }

            // Player is the owner — clear the data
            stack.setTag(null); // or stack.removeTagKey("OwnerUUID"); + remove other keys individually
            if (!pLevel.isClientSide) {
                pPlayer.displayClientMessage(Component.translatable("item.idcards.idcard.actionBarMessage.Unlinked")
                        .withStyle(ChatFormatting.RED), true);
            }
            return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
        }

        // Else: set ownership
        tag.putUUID("OwnerUUID", pPlayer.getUUID());
        tag.putString("OwnerName", pPlayer.getName().getString());
        stack.setTag(tag);

        if (!pLevel.isClientSide())
        {
            pPlayer.displayClientMessage(Component.translatable("item.idcards.idcard.actionBarMessage.Linked",
                    pPlayer.getName().getString()).withStyle(ChatFormatting.GREEN), true);
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.idcards.idcard.tooltip.base1"));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("[Ctrl] for controls"));

        // Show controls when control key is pressed
        if (Screen.hasControlDown())
        {
            tooltip.add(Component.translatable("item.idcards.idcard.tooltip.base2"));
            tooltip.add(Component.translatable("item.idcards.idcard.tooltip.base3"));
        }

        if (stack.hasTag() && stack.getTag().contains("OwnerName", Tag.TAG_STRING)) {
            Component ownerName = Component.literal(stack.getTag().getString("OwnerName")).withStyle(ChatFormatting.GREEN);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.idcards.idcard.tooltip.owner", ownerName));
        }
    }
}

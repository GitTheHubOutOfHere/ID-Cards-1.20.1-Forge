package com.dave08.idcards.block.entity.menu;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.entity.IdcardReaderBlockEntity;
import com.dave08.idcards.block.entity.menu.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class IDCardReaderMenu extends AbstractContainerMenu {
    private final Container container;
    private final IdcardReaderBlockEntity blockEntity;
    private final ContainerData data;

    public IDCardReaderMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, (IdcardReaderBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainer(54));
    }

    public IDCardReaderMenu(int id, Inventory inv, IdcardReaderBlockEntity be, Container container) {
        super(ModMenus.ID_CARD_READER_MENU.get(), id);
        this.container = container;
        this.blockEntity = be;
        this.data = blockEntity.getDataAccess();
        addDataSlots(data);

        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 9; ++j) {
                int index = j + i * 9;
                this.addSlot(new Slot(container, index, 8 + j * 18, 18 + i * 18) {
                    private boolean initialized = false;

                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return stack.is(ItemTags.create(new ResourceLocation("idcards", "idcards"))) &&
                                stack.hasTag() &&
                                stack.getTag().contains("OwnerUUID");
                    }

                    @Override
                    public void setChanged() {
                        super.setChanged();
                        if (initialized) {
                            be.updateUUIDList(container);
                        } else {
                            initialized = true; // Skip the first call
                        }
                    }
                });
            }
        }

        for (int row = 0; row < 3; ++row)
            for (int col = 0; col < 9; ++col)
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 140 + row * 18));

        for (int i = 0; i < 9; ++i)
            this.addSlot(new Slot(inv, i, 8 + i * 18, 198));
    }

    public IdcardReaderBlockEntity getBlockEntity()
    {
        return blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i)
    {
        return player.getInventory().getItem(1);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public void setPulseLength(Integer newPulseLength) { blockEntity.setPulseLength(newPulseLength); }
    
    public int getPulseLength() {
        IDCards.LOGGER.info("Getting value (2): " + data.get(0));
        return data.get(0);
    }
}
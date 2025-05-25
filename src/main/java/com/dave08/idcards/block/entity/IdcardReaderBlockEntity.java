package com.dave08.idcards.block.entity;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.block.entity.ModBlockEntities;
import com.dave08.idcards.block.entity.menu.IDCardReaderMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IdcardReaderBlockEntity extends BlockEntity implements MenuProvider {
    private final Container container = new SimpleContainer(54);
    private boolean isPowered = false;
    private String customName;
    private int pulseLength;

    public Container getContainer() {
        return container;
    }

    private final List<UUID> storedUUIDs = new ArrayList<>();

    private UUID owner = new UUID(0L, 0L);

    public IdcardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IDCARD_READER_BE.get(), pos, state);
    }

    public void setCustomName(String name) { customName = name; }

    public void setPulseLength(Integer newPulseLength) {
        pulseLength = newPulseLength;
        setChanged();
        IDCards.LOGGER.info("Called setPulseLength() on: " + (level != null && level.isClientSide ? "CLIENT" : "SERVER")); }
    public int getPulseLength() { return pulseLength; }

    @Override
    public Component getDisplayName() {
        return customName == null ? Component.translatable("block.idcards.idcard_reader") : Component.literal(customName + " trust list");
    }

    public boolean getPowered() { return isPowered; }

    public void setPowered(Boolean powered) {
        this.isPowered = powered;
        setChanged();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new IDCardReaderMenu(id, inv, this, container);
    }

    public void updateUUIDList(Container container) {
        storedUUIDs.clear();
        //IDCards.LOGGER.info("Cleared uuids");
        for (int i = 0; i < container.getContainerSize(); i++) {
            var stack = container.getItem(i);
            if (stack != null && stack.hasTag()) {
                CompoundTag tag = stack.getTag();
                if (tag.contains("OwnerUUID")) {
                    try {
                        storedUUIDs.add(UUID.fromString(tag.getUUID("OwnerUUID").toString()));
                        //IDCards.LOGGER.info("Added ", UUID.fromString(tag.getUUID("OwnerUUID").toString()));
                    } catch (IllegalArgumentException e) {/*IDCards.LOGGER.info("Failed to add: ", tag.getAllKeys(), e);*/}
                }
            }
        }
        //IDCards.LOGGER.info("Stored UUIDs: ", storedUUIDs);
        setChanged();
    }

    public List<UUID> getStoredUUIDs() {
        return storedUUIDs;
    }

    public UUID getOwner() {return owner;}

    public void setOwner(UUID newOwner) {owner = newOwner;}

    public void emitRedstoneSignal() {
        Level world = this.level;
        BlockPos pos = this.getBlockPos();

        // Set a specific Redstone strength, such as 15
        int signalStrength = 15;
        world.setBlockAndUpdate(pos, this.getBlockState().setValue(BlockStateProperties.POWER, signalStrength));

        // Notify neighbors about the Redstone signal change
        world.updateNeighborsAt(pos, this.getBlockState().getBlock());
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = world.getBlockState(neighborPos);
            world.updateNeighborsAt(neighborPos, neighborState.getBlock());
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        // Save UUIDs
        ListTag uuidList = new ListTag();
        for (UUID uuid : storedUUIDs) {
            uuidList.add(StringTag.valueOf(uuid.toString()));
        }
        tag.put("StoredUUIDs", uuidList);

        // Save container items, without checking for empty slots
        ListTag itemList = new ListTag();
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack itemStack = container.getItem(i);
            CompoundTag itemTag = new CompoundTag();
            itemStack.save(itemTag); // Always save the item stack, even if it's empty
            itemList.add(itemTag);
        }
        tag.put("ContainerItems", itemList); // Store it under a custom key "ContainerItems"

        //IDCards.LOGGER.info("Stored UUIDs: {}", uuidList); // Debugging log
        tag.putUUID("Owner", owner);

        tag.putBoolean("powered", isPowered);

        //tag.putInt("pulseLength", pulseLength);
        //IDCards.LOGGER.info("Pulse length saved: " + tag.getInt("pulseLength"));

        if (customName != null) { tag.putString("customName", customName); }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        // Load UUIDs
        storedUUIDs.clear();
        ListTag uuidList = tag.getList("StoredUUIDs", Tag.TAG_STRING);
        for (Tag element : uuidList) {
            try {
                storedUUIDs.add(UUID.fromString(element.getAsString()));
            } catch (IllegalArgumentException ignored) {}
        }

        // Load container items
        ListTag itemList = tag.getList("ContainerItems", Tag.TAG_COMPOUND);
        for (int i = 0; i < itemList.size(); i++) {
            CompoundTag itemTag = itemList.getCompound(i);
            ItemStack itemStack = ItemStack.of(itemTag); // Reconstruct the item stack
            container.setItem(i, itemStack); // Set it in the container, preserving the order
        }

        //IDCards.LOGGER.info("Loaded UUIDs: {}", uuidList); // Debugging log
        owner = tag.getUUID("Owner");

        isPowered = tag.getBoolean("powered");

        //pulseLength = tag.getInt("pulseLength");
        //IDCards.LOGGER.info("Getting value (0): " + tag.getInt("pulseLength"));

        if (customName != null) { customName = tag.getString("customName"); }
    }

    /*private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            IDCards.LOGGER.info("Getting value (1): " + pulseLength);
            if (index == 0) return pulseLength;
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) pulseLength = value;
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public ContainerData getDataAccess() {
        return dataAccess;
    }/**/

}
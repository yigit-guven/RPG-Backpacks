package com.yigitguven.rpgbackpacks.menu;

import com.yigitguven.rpgbackpacks.registry.ModDataComponents;
import com.yigitguven.rpgbackpacks.registry.ModItems;
import com.yigitguven.rpgbackpacks.registry.ModMenus;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.ArrayList;
import java.util.List;

public class BackpackMenu extends AbstractContainerMenu {
    private final SimpleContainer backpackContainer;
    private final ItemStack backpackStack;
    private final Player player;
    public final int rows; // Exposed for Screen

    // Constructor for Client Side
    public BackpackMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, findBackpack(playerInventory.player, extraData));
    }

    private static ItemStack findBackpack(Player player, FriendlyByteBuf extraData) {
        int source = extraData.readInt();
        if (source == 0)
            return player.getMainHandItem();
        if (source == 1)
            return player.getOffhandItem();
        if (source == 2)
            return player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST);
        if (source == 3)
            return com.yigitguven.rpgbackpacks.compat.CuriosCompat.findBackpack(player);
        return ItemStack.EMPTY;
    }

    // Constructor for Server Side
    public BackpackMenu(int containerId, Inventory playerInventory, ItemStack backpackStack) {
        super(ModMenus.BACKPACK_MENU.get(), containerId);
        this.player = playerInventory.player;
        this.backpackStack = backpackStack;

        // Determine rows from item
        if (backpackStack.getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem backpackItem) {
            this.rows = backpackItem.rows;
        } else {
            this.rows = 3; // Fallback
        }

        // Initialize container with dynamic size
        this.backpackContainer = new SimpleContainer(this.rows * 9) {
            @Override
            public void setChanged() {
                super.setChanged();
                BackpackMenu.this.slotsChanged(this);
            }
        };

        // Load items from ItemStack component
        if (!backpackStack.isEmpty()) {
            ItemContainerContents contents = backpackStack.get(ModDataComponents.BACKPACK_CONTENTS.get());
            if (contents != null) {
                contents.copyInto(this.backpackContainer.getItems());
            }
        }

        // Backpack Slots
        int i = (this.rows - 4) * 18;
        for (int row = 0; row < this.rows; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(this.backpackContainer, col + row * 9, 8 + col * 18, 18 + row * 18) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return !(stack.getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem);
                    }
                });
            }
        }

        // Player Inventory Slots
        int inventoryY = 18 + (this.rows * 18) + 13; // 13 padding? Vanilla uses 103 for 6 rows (103 = 18 + 6*18 + x?
                                                     // 18+108=126. 103? No.
        // Vanilla Generic54:
        // Top section: 17 (header) + rows * 18.
        // Inventory starts at: 103 + (rows - 6) * 18?
        // Let's use simple logic: 18 (top padding) + rows * 18 (slots) + 14 (middle
        // padding)

        // Correct offset calculation to match generic_54.png texture usage in screen
        // Top border: 17px
        // Backpack slots: rows * 18px
        // Middle border: 14px (usually)
        // So player inventory starts at: 17 + (rows * 18) + 14
        int playerInvY = 17 + (this.rows * 18) + 14;

        for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < 9; ++c) {
                this.addSlot(new Slot(playerInventory, c + r * 9 + 9, 8 + c * 18, playerInvY + r * 18));
            }
        }

        // Player Hotbar Slots
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, playerInvY + 58));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            int backpackSize = this.rows * 9;

            if (index < backpackSize) {
                if (!this.moveItemStackTo(itemstack1, backpackSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, backpackSize, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return !this.backpackStack.isEmpty();
    }

    @Override
    public void slotsChanged(Container container) {
        if (container == this.backpackContainer && !player.level().isClientSide && !backpackStack.isEmpty()) {
            List<ItemStack> items = new ArrayList<>();
            for (int i = 0; i < container.getContainerSize(); i++) {
                items.add(container.getItem(i));
            }
            backpackStack.set(ModDataComponents.BACKPACK_CONTENTS.get(), ItemContainerContents.fromItems(items));
        }
    }
}

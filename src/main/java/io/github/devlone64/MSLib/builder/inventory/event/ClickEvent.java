package io.github.devlone64.MSLib.builder.inventory.event;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class ClickEvent {
    public boolean cancelled;

    public Player player;
    public final ItemStack item;
    public final ClickType click;
    public final InventoryView view;
    public final Inventory clickedInventory;
    public final InventoryAction action;
    public final int slot;
    public final int rawSlot;
    public final int hotbarKey;
}
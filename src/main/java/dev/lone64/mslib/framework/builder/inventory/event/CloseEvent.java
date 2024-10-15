package dev.lone64.mslib.framework.builder.inventory.event;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

@AllArgsConstructor
public class CloseEvent {
    public final Player player;
    public final InventoryView view;
    public final Inventory inventory;
}
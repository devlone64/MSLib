package io.github.devlone64.MSLib.builder.inventory.data;

import io.github.devlone64.MSLib.builder.inventory.extension.EventExt;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface InventoryProvider<T> extends EventExt {
    default void onPrevInit(Inventory inventory, Player player) { }
    default void onInit(Inventory inventory, Player player) { }
    default void onInitLater(Inventory inventory, Player player) { }

    boolean show(Player player);
    boolean update(Player player);
    boolean showAndUpdate(Player player);

    T createInventory();
}
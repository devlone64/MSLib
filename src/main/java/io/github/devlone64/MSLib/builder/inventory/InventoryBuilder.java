package io.github.devlone64.MSLib.builder.inventory;

import io.github.devlone64.MSLib.builder.inventory.impl.BukkitInventory;
import io.github.devlone64.MSLib.builder.inventory.impl.CustomInventory;
import io.github.devlone64.MSLib.util.message.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public class InventoryBuilder {

    public static void show(Player player, int rows, String name, Consumer<Inventory> consumer) {
        Inventory inventory = Bukkit.createInventory(null, rows * 9, Component.from(name));
        consumer.accept(inventory);
        player.openInventory(inventory);
    }

    public static void show(Player player, InventoryType type, String name, Consumer<Inventory> consumer) {
        Inventory inventory = Bukkit.createInventory(null, type, Component.from(name));
        consumer.accept(inventory);
        player.openInventory(inventory);
    }

    public static boolean show(Player player, BukkitInventory inventory) {
        return inventory.show(player);
    }

    public static boolean show(Player player, CustomInventory inventory) {
        return inventory.show(player);
    }

}
package io.github.devlone64.MSLib.command;

import io.github.devlone64.MSLib.command.provider.SenderProvider;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface BaseCommand extends SenderProvider {
    default boolean perform(CommandSender sender, String[] args) { return false; }
    default List<String> complete(CommandSender sender, String[] args) { return List.of(); }

    default boolean perform(Player player, String[] args) { return false; }
    default List<String> complete(Player player, String[] args) { return List.of(); }
}
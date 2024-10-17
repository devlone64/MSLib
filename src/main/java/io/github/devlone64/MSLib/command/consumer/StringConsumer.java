package io.github.devlone64.MSLib.command.consumer;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface StringConsumer {
    String accept(Player player);
}
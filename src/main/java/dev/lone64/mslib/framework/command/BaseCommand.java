package dev.lone64.mslib.framework.command;

import dev.lone64.mslib.framework.command.data.CommandType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface BaseCommand {
    CommandType getCommandType();

    default String getConsoleMessage() { return "&c해당 명령어는 콘솔에서 사용할 수 없습니다."; }

    default String getPermissionNode() { return ""; }
    default String getPermissionMessage() { return "&c당신은 해당 명령어를 사용할 권한이 없습니다."; }

    default boolean perform(CommandSender sender, String[] args) { return false; }
    default List<String> complete(CommandSender sender, String[] args) { return List.of(); }
    
    default boolean perform(Player player, String[] args) { return false; }
    default List<String> complete(Player player, String[] args) { return List.of(); }
}
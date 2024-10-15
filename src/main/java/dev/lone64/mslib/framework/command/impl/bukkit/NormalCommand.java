package dev.lone64.mslib.framework.command.impl.bukkit;

import dev.lone64.mslib.framework.command.LoadCommand;
import dev.lone64.mslib.framework.command.data.CommandType;
import dev.lone64.mslib.framework.util.message.Component;
import lombok.Getter;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
public class NormalCommand implements CommandExecutor, TabExecutor {

    private final LoadCommand mapper;

    public NormalCommand(LoadCommand mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (this.mapper.getCommand().getCommandType() == CommandType.CONSOLE)
            return mapper.getCommand().perform(sender, args);
        else if (this.mapper.getCommand().getCommandType() == CommandType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(Component.from(this.mapper.getPrefix() + this.mapper.getConsoleMessage()));
                return true;
            }
            return mapper.getCommand().perform((Player) sender, args);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (this.mapper.getCommand().getCommandType() == CommandType.CONSOLE)
            return mapper.getCommand().complete(sender, args);
        else if (this.mapper.getCommand().getCommandType() == CommandType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) return List.of();
            return mapper.getCommand().complete((Player) sender, args);
        }
        return List.of();
    }

}
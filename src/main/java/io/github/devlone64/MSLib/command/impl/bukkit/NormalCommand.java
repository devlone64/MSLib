package io.github.devlone64.MSLib.command.impl.bukkit;

import io.github.devlone64.MSLib.command.LoadCommand;
import io.github.devlone64.MSLib.command.data.SenderType;
import io.github.devlone64.MSLib.util.message.Component;
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
        if (this.mapper.getCommand().getSenderType() == SenderType.CONSOLE)
            return mapper.getCommand().perform(sender, args);
        else if (this.mapper.getCommand().getSenderType() == SenderType.PLAYER) {
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
        if (this.mapper.getCommand().getSenderType() == SenderType.CONSOLE)
            return mapper.getCommand().complete(sender, args);
        else if (this.mapper.getCommand().getSenderType() == SenderType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) return List.of();
            return mapper.getCommand().complete((Player) sender, args);
        }
        return List.of();
    }

}
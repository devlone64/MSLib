package io.github.devlone64.MSLib.command.executor;

import io.github.devlone64.MSLib.command.data.CommandData;
import io.github.devlone64.MSLib.command.enums.SenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static io.github.devlone64.MSLib.util.message.Component.from;

@Getter
@AllArgsConstructor
public class CmdExecutor implements CommandExecutor, TabExecutor {

    private final CommandData commandData;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (getCommandData().getBaseCommand().getSenderType() == SenderType.CONSOLE) {
            return getCommandData().getBaseCommand().perform(sender, args);
        } else if (getCommandData().getBaseCommand().getSenderType() == SenderType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(from("&7[%s] &r%s".formatted(
                        getCommandData().getPlugin().getName(),
                        getCommandData().getSenderMessage()
                )));
                return true;
            }
            return getCommandData().getBaseCommand().perform((Player) sender, args);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (getCommandData().getBaseCommand().getSenderType() == SenderType.CONSOLE) {
            return getCommandData().getBaseCommand().complete(sender, args);
        } else if (getCommandData().getBaseCommand().getSenderType() == SenderType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) return List.of();
            return getCommandData().getBaseCommand().complete((Player) sender, args);
        }
        return List.of();
    }

}
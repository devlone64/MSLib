package io.github.devlone64.MSLib.command.executor;

import io.github.devlone64.MSLib.command.data.CommandData;
import io.github.devlone64.MSLib.command.enums.SenderType;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.github.devlone64.MSLib.util.message.Component.from;

@Getter
public class PaperExecutor extends BukkitCommand {
    
    private final CommandData commandData;

    public PaperExecutor(CommandData commandData) {
        super(commandData.getName());
        this.commandData = commandData;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg, @NotNull String[] args) {
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

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String arg, String[] args) {
        if (getCommandData().getBaseCommand().getSenderType() == SenderType.CONSOLE) {
            return getCommandData().getBaseCommand().complete(sender, args);
        } else if (getCommandData().getBaseCommand().getSenderType() == SenderType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) return List.of();
            return getCommandData().getBaseCommand().complete((Player) sender, args);
        }
        return List.of();
    }
    
}
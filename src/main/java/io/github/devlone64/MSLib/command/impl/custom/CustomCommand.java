package io.github.devlone64.MSLib.command.impl.custom;

import io.github.devlone64.MSLib.command.LoadCommand;
import io.github.devlone64.MSLib.command.data.CommandType;
import io.github.devlone64.MSLib.util.message.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomCommand extends org.bukkit.command.defaults.BukkitCommand {

    private final JavaPlugin plugin;
    private final LoadCommand mapper;

    public CustomCommand(JavaPlugin plugin, LoadCommand mapper) {
        super(mapper.getName());

        this.plugin = plugin;
        this.mapper = mapper;

        setName(mapper.getName());
        if (mapper.getUsage() != null && !mapper.getUsage().isEmpty())
            setUsage(mapper.getUsage());
        if (mapper.getDescription() != null && !mapper.getDescription().isEmpty())
            setDescription(mapper.getDescription());
        if (mapper.getPermission() != null && !mapper.getPermission().isEmpty())
            setPermission(mapper.getPermission());
        if (mapper.getAliases() != null && !mapper.getAliases().isEmpty())
            setAliases(mapper.getAliases());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg, @NotNull String[] args) {
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

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String arg, String[] args) {
        if (this.mapper.getCommand().getCommandType() == CommandType.CONSOLE)
            return mapper.getCommand().complete(sender, args);
        else if (this.mapper.getCommand().getCommandType() == CommandType.PLAYER) {
            if (sender instanceof ConsoleCommandSender) return List.of();
            return mapper.getCommand().complete((Player) sender, args);
        }
        return List.of();
    }

}
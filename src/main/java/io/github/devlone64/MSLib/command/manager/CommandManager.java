package io.github.devlone64.MSLib.command.manager;

import io.github.devlone64.MSLib.command.BaseCommand;
import io.github.devlone64.MSLib.command.LoadCommand;
import io.github.devlone64.MSLib.command.impl.custom.CustomCommand;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CommandManager {

    private final JavaPlugin plugin;
    private final Map<Class<? extends BaseCommand>, LoadCommand> commands = new HashMap<>();

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void spigots(BaseCommand... commands) {
        for (BaseCommand command : commands) {
            LoadCommand loadCommand = new LoadCommand(plugin, command);
            this.commands.put(command.getClass(), loadCommand);
        }
    }

    public void customs(BaseCommand... commands) {
        for (BaseCommand command : commands) {
            try {
                LoadCommand loadCommand;
                java.lang.reflect.Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                bukkitCommandMap.setAccessible(true);
                org.bukkit.command.CommandMap cmap = (org.bukkit.command.CommandMap) bukkitCommandMap.get(Bukkit.getServer());
                cmap.register(plugin.getName(), new CustomCommand(plugin, loadCommand = new LoadCommand(plugin, command)));
                this.commands.put(command.getClass(), loadCommand);
            } catch (Exception ignored) { }
        }
    }

}
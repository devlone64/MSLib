package io.github.devlone64.MSLib.command.manager;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.command.BaseCommand;
import io.github.devlone64.MSLib.command.data.CommandData;
import io.github.devlone64.MSLib.command.executor.CmdExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommandManager {

    private final List<CommandData> commandDataList = new ArrayList<>();
    private final MSPlugin plugin;

    public void register(BaseCommand... commands) {
        for (var cmd : commands) register(new CommandData(getPlugin(), cmd));
    }

    public void register(List<BaseCommand> commands) {
        register(commands.toArray(new BaseCommand[0]));
    }

    private void register(CommandData commandData) {
        var commandMap = getCommandMap();
        if (commandMap != null) {
            unregister(commandData.getName());

            var command = createCommand(commandData.getName());
            if (command != null) {
                command.setExecutor(new CmdExecutor(commandData));
                command.setTabCompleter(new CmdExecutor(commandData));

                command.setName(commandData.getName());
                command.setUsage(commandData.getUsage());
                command.setDescription(commandData.getComment());
                command.setAliases(commandData.getAliases());
                command.setPermission(commandData.getPermission());
                commandMap.register(getPlugin().getName(), command);
                getCommandDataList().add(commandData);
            }
        }
    }

    public void unregister(String name) {
        try {
            var field = getField("commandMap");
            field.setAccessible(true);

            var commandMap = (CommandMap) field.get(Bukkit.getServer());
            var command = commandMap.getCommand(name);
            if (command != null) command.unregister(commandMap);
        } catch (Exception e) {
            getPlugin().getLogger().severe(e.getMessage());
        }
    }

    public PluginCommand createCommand(String name) {
        try {
            var field = getField("commandMap");
            field.setAccessible(true);

            var commandMap = (CommandMap) field.get(Bukkit.getServer());
            var cmdCons = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            cmdCons.setAccessible(true);

            var command = cmdCons.newInstance(name, getPlugin());
            commandMap.register(getPlugin().getName(), command);
            return command;
        } catch (Exception e) {
            getPlugin().getLogger().severe(e.getMessage());
            return null;
        }
    }

    private CommandMap getCommandMap() {
        try {
            var field = getField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getServer());
        } catch (Exception e) {
            getPlugin().getLogger().severe(e.getMessage());
            return null;
        }
    }

    private Field getField(String name) throws NoSuchFieldException {
        return getPlugin().getServer().getClass().getDeclaredField("commandMap");
    }

}
package io.github.devlone64.MSLib.command.manager;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.command.BaseCommand;
import io.github.devlone64.MSLib.command.data.CommandData;
import io.github.devlone64.MSLib.command.data.LoaderType;
import io.github.devlone64.MSLib.command.executor.PaperExecutor;
import io.github.devlone64.MSLib.command.executor.SpigotExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommandManager {

    private final List<CommandData> commandDataList = new ArrayList<>();
    private final MSPlugin plugin;

    public void register(BaseCommand... commands) {
        for (var cmd : commands) {
            var commandData = new CommandData(getPlugin(), cmd);
            if (commandData.getLoaderType() == LoaderType.SPIGOT) {
                addSpigot(commandData);
            } else if (commandData.getLoaderType() == LoaderType.PAPER) {
                addPaper(commandData);
            }
        }
    }

    public void register(List<BaseCommand> commands) {
        register(commands.toArray(new BaseCommand[0]));
    }

    private void addSpigot(CommandData commandData) {
        var command = getPlugin().getCommand(commandData.getName());
        if (command != null) {
            command.setExecutor(new SpigotExecutor(commandData));
            command.setTabCompleter(new SpigotExecutor(commandData));

            command.setName(commandData.getName());
            command.setUsage(commandData.getUsage());
            command.setDescription(commandData.getComment());
            command.setAliases(commandData.getAliases());
            command.setPermission(commandData.getPermissionNode());
        }
    }

    private void addPaper(CommandData commandData) {
        try {
            var field = getField("commandMap");
            field.setAccessible(true);

            var commandMap = (CommandMap) field.get(Bukkit.getServer());
            commandMap.register(getPlugin().getName(), new PaperExecutor(commandData));
            getCommandDataList().add(commandData);
        } catch (Exception e) {
            getPlugin().getLogger().severe(e.getMessage());
        }
    }

    private Field getField(String name) throws NoSuchFieldException {
        return getPlugin().getServer().getClass().getDeclaredField("commandMap");
    }

}
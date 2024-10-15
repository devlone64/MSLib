package io.github.devlone64.MSLib.command;

import io.github.devlone64.MSLib.command.annotation.CommandInfo;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Setter
@Getter
public class LoadCommand {

    private JavaPlugin plugin;
    private BaseCommand command;

    private String name;
    private String usage;
    private String prefix;
    private String description;

    private String permission;
    private String permissionMessage;

    private String consoleMessage;

    private List<String> aliases;

    public LoadCommand(JavaPlugin plugin, BaseCommand command) {
        this.plugin = plugin;
        this.command = command;
        if (command.getClass().isAnnotationPresent(CommandInfo.class)) {
            CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);

            this.name = commandInfo.name();
            this.usage = commandInfo.usage();
            this.prefix = commandInfo.prefix();
            this.description = commandInfo.description();
            this.aliases = List.of(commandInfo.aliases());

            this.permission = command.getPermissionNode();
            this.permissionMessage = command.getPermissionMessage();

            this.consoleMessage = command.getConsoleMessage();
        }
    }

}
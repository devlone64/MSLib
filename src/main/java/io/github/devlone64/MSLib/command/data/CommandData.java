package io.github.devlone64.MSLib.command.data;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.command.BaseCommand;
import io.github.devlone64.MSLib.command.annotation.CommandInfo;
import io.github.devlone64.MSLib.command.annotation.loader.PaperLoader;
import io.github.devlone64.MSLib.command.annotation.loader.SpigotLoader;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class CommandData {

    private final MSPlugin plugin;
    private final BaseCommand baseCommand;

    private final String name;
    private final String usage;
    private final String comment;
    private final List<String> aliases;

    private final String senderMessage;

    private final String permissionNode;
    private final String permissionMessage;

    private final LoaderType loaderType;

    public CommandData(MSPlugin plugin, BaseCommand baseCommand) {
        this.plugin = plugin;
        this.baseCommand = baseCommand;
        if (baseCommand.getClass().isAnnotationPresent(CommandInfo.class)) {
            CommandInfo commandInfo = baseCommand.getClass().getAnnotation(CommandInfo.class);

            this.name = commandInfo.name();
            this.usage = commandInfo.usage();
            this.comment = commandInfo.comment();
            this.aliases = Arrays.asList(commandInfo.aliases());

            this.senderMessage = baseCommand.getSenderMessage();

            this.permissionNode = baseCommand.getPermissionNode();
            this.permissionMessage = baseCommand.getPermissionMessage();

            if (baseCommand.getClass().isAnnotationPresent(PaperLoader.class)) {
                this.loaderType = LoaderType.PAPER;
            } else {
                this.loaderType = LoaderType.SPIGOT;
            }
        } else {
            throw new RuntimeException("CommandInfo annotation is not found: %s".formatted(plugin.getName()));
        }
    }

}
package io.github.devlone64.MSLib.command.data;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.command.BaseCommand;
import io.github.devlone64.MSLib.command.annotation.Command;
import io.github.devlone64.MSLib.command.enums.SenderType;
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
    private final String permission;

    private final String senderMessage;

    private final SenderType senderType;

    public CommandData(MSPlugin plugin, BaseCommand baseCommand) {
        this.plugin = plugin;
        this.baseCommand = baseCommand;
        if (baseCommand.getClass().isAnnotationPresent(Command.class)) {
            Command command = baseCommand.getClass().getAnnotation(Command.class);

            this.name = command.name();
            this.usage = command.usage();
            this.comment = command.comment();
            this.aliases = Arrays.asList(command.aliases());
            this.permission = command.permission();

            this.senderMessage = baseCommand.onConsoleRequest();

            this.senderType = command.consoleAvailable() ? SenderType.CONSOLE : SenderType.PLAYER;
        } else {
            throw new RuntimeException("CommandInfo annotation is not found: %s".formatted(plugin.getName()));
        }
    }



}
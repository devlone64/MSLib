package io.github.devlone64.MSLib;

import io.github.devlone64.MSLib.command.manager.CommandManager;

import java.util.logging.Logger;

public class MSLib {

    public static final MSPlugin INSTANCE = MSPlugin.INSTANCE;
    public static final Logger LOGGER = INSTANCE.getLogger();

    public static CommandManager getCommandManager() {
        return INSTANCE.getCommandManager();
    }

}
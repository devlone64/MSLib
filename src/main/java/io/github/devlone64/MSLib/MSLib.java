package io.github.devlone64.MSLib;

import io.github.devlone64.MSLib.builder.language.manager.PlayerLanguageManager;
import io.github.devlone64.MSLib.command.manager.CommandManager;

public class MSLib {

    public static final MSPlugin INSTANCE = MSPlugin.INSTANCE;

    public static CommandManager getCommandManager() {
        return INSTANCE.getCommandManager();
    }

    public static PlayerLanguageManager getLanguageManager() {
        return INSTANCE.getLanguageManager();
    }

}
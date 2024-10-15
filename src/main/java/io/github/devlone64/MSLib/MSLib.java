package io.github.devlone64.MSLib;

import java.io.File;
import java.util.logging.Logger;

public class MSLib {

    public static MSPlugin getInstance() {
        return MSPlugin.INSTANCE;
    }

    public static Logger getLogger() {
        return getInstance().getLogger();
    }

    public static File getPluginFolder() {
        return getInstance().getDataFolder();
    }

}
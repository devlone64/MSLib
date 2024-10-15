package dev.lone64.mslib.framework;

import java.io.File;
import java.util.logging.Logger;

public class MSLib {

    public static MSPlugin getInstance() {
        return MSPlugin.getPlugin(MSPlugin.class);
    }

    public static Logger getLogger() {
        return getInstance().getLogger();
    }

    public static File getPluginFolder() {
        return getInstance().getDataFolder();
    }

}
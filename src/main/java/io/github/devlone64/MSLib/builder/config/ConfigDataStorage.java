package io.github.devlone64.MSLib.builder.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigDataStorage {

    private static final Map<Class<? extends ConfigBuilderProvider>, ConfigBuilderProvider> configData = new HashMap<>();

    public static void setConfig(ConfigBuilderProvider config) {
        configData.put(config.getClass(), config);
    }

    public static ConfigBuilderProvider removeConfig(Class<? extends ConfigBuilderProvider> configClass) {
        return configData.remove(configClass);
    }

    public static ConfigBuilderProvider getConfig(Class<? extends ConfigBuilderProvider> configClass) {
        return configData.get(configClass);
    }

    public static boolean isConfig(Class<? extends ConfigBuilderProvider> configClass) {
        return configData.containsKey(configClass);
    }

}
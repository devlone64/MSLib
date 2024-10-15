package io.github.devlone64.MSLib.util;

import io.github.devlone64.MSLib.MSLib;
import io.github.devlone64.MSLib.util.message.Component;
import org.bukkit.Bukkit;

public class Console {

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(Component.from("&7[%s] &r%s".formatted(MSLib.getInstance().getName(), message)));
    }

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(Component.from("&7[%s] %s".formatted(MSLib.getInstance().getName(), message)));
    }

    public static void warning(String message) {
        Bukkit.getConsoleSender().sendMessage(Component.from("&e[%s] %s".formatted(MSLib.getInstance().getName(), message)));
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(Component.from("&c[%s] %s".formatted(MSLib.getInstance().getName(), message)));
    }

}
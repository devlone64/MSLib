package io.github.devlone64.MSLib.spigot;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.command.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.scheduler.BukkitTask;

public class Spigot {

    public static void register(BaseCommand... commands) {
        MSPlugin.INSTANCE.getCommandManager().register(commands);
    }

    public static void register(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, MSPlugin.INSTANCE);
        }
    }

    public static void register(Permission permission) {
        Bukkit.getPluginManager().addPermission(permission);
    }

    public static <T> void register(Class<T> aClass, T t) {
        Bukkit.getServicesManager().register(aClass, t, MSPlugin.INSTANCE, ServicePriority.Normal);
    }

    public static BukkitTask async(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(MSPlugin.INSTANCE, runnable);
    }

    public static BukkitTask sync(Runnable runnable) {
        return Bukkit.getScheduler().runTask(MSPlugin.INSTANCE, runnable);
    }

    public static BukkitTask syncLater(Runnable runnable, long ticks) {
        return Bukkit.getScheduler().runTaskLater(MSPlugin.INSTANCE, runnable, ticks);
    }

    public static BukkitTask syncTimer(Runnable runnable, long delay, long ticks) {
        return Bukkit.getScheduler().runTaskTimer(MSPlugin.INSTANCE, runnable, delay, ticks);
    }

    public static boolean isQueued(int taskId) {
        return Bukkit.getScheduler().isQueued(taskId);
    }

    public static boolean isCurrentlyRunning(int taskId) {
        return Bukkit.getScheduler().isCurrentlyRunning(taskId);
    }

}
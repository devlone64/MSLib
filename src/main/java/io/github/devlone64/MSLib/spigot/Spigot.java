package io.github.devlone64.MSLib.spigot;

import io.github.devlone64.MSLib.MSLib;
import io.github.devlone64.MSLib.command.BaseCommand;
import io.github.devlone64.MSLib.command.LoadCommand;
import io.github.devlone64.MSLib.command.data.Types;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.scheduler.BukkitTask;

public class Spigot {

    public static void register(BaseCommand... commands) {
        for (BaseCommand command : commands) {
            LoadCommand loadCommand = MSLib.INSTANCE.getCommandManager().getCommand(command.getClass());
            if (loadCommand != null) {
                if (loadCommand.getType() == Types.SPIGOT) {
                    MSLib.INSTANCE.getCommandManager().spigots(command);
                } else {
                    MSLib.INSTANCE.getCommandManager().customs(command);
                }
            }
        }
    }

    public static void register(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, MSLib.INSTANCE);
        }
    }

    public static void register(Permission permission) {
        Bukkit.getPluginManager().addPermission(permission);
    }

    public static <T> void register(Class<T> aClass, T t) {
        Bukkit.getServicesManager().register(aClass, t, MSLib.INSTANCE, ServicePriority.Normal);
    }

    public static BukkitTask async(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(MSLib.INSTANCE, runnable);
    }

    public static BukkitTask sync(Runnable runnable) {
        return Bukkit.getScheduler().runTask(MSLib.INSTANCE, runnable);
    }

    public static BukkitTask syncLater(Runnable runnable, long ticks) {
        return Bukkit.getScheduler().runTaskLater(MSLib.INSTANCE, runnable, ticks);
    }

    public static BukkitTask syncTimer(Runnable runnable, long delay, long ticks) {
        return Bukkit.getScheduler().runTaskTimer(MSLib.INSTANCE, runnable, delay, ticks);
    }

    public static boolean isQueued(int taskId) {
        return Bukkit.getScheduler().isQueued(taskId);
    }

    public static boolean isCurrentlyRunning(int taskId) {
        return Bukkit.getScheduler().isCurrentlyRunning(taskId);
    }

}
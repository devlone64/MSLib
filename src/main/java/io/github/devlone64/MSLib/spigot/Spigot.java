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

import java.util.List;

public class Spigot {

    public static void register(Permission permission) {
        Bukkit.getPluginManager().addPermission(permission);
    }

    public static void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, MSLib.getInstance());
    }

    public static <T> void register(Class<T> aClass, T t) {
        Bukkit.getServicesManager().register(aClass, t, MSLib.getInstance(), ServicePriority.Normal);
    }

    public static void register(BaseCommand... commands) {
        for (BaseCommand command : commands) {
            LoadCommand cmd = MSLib.getInstance().getCommandManager().getCommands().get(command.getClass());
            if (cmd != null) {
                if (cmd.getType() == Types.SPIGOT) {
                    MSLib.getInstance().getCommandManager().spigots(command);
                } else {
                    MSLib.getInstance().getCommandManager().customs(command);
                }
            }
        }
    }

    public static void register(List<BaseCommand> commands) {
        register(commands.toArray(new BaseCommand[0]));
    }

    public static BukkitTask async(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(MSLib.getInstance(), runnable);
    }

    public static BukkitTask sync(Runnable runnable) {
        return Bukkit.getScheduler().runTask(MSLib.getInstance(), runnable);
    }

    public static BukkitTask syncLater(Runnable runnable, long ticks) {
        return Bukkit.getScheduler().runTaskLater(MSLib.getInstance(), runnable, ticks);
    }

    public static BukkitTask syncTimer(Runnable runnable, long delay, long ticks) {
        return Bukkit.getScheduler().runTaskTimer(MSLib.getInstance(), runnable, delay, ticks);
    }

    public static boolean isQueued(int taskId) {
        return Bukkit.getScheduler().isQueued(taskId);
    }

    public static boolean isCurrentlyRunning(int taskId) {
        return Bukkit.getScheduler().isCurrentlyRunning(taskId);
    }

}
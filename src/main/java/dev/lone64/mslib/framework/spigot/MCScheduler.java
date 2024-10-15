package dev.lone64.mslib.framework.spigot;

import dev.lone64.mslib.framework.MSLib;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class MCScheduler {

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
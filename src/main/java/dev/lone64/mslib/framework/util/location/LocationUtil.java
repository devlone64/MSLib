package dev.lone64.mslib.framework.util.location;import org.bukkit.Bukkit;import org.bukkit.Location;import org.bukkit.World;public class LocationUtil {        public static Location setX(Location location, double x) {        location.setX(x);        return location;    }    public static Location setY(Location location, double y) {        location.setY(y);        return location;    }    public static Location setZ(Location location, double z) {        location.setZ(z);        return location;    }    public static Location setBlockX(Location location, int x) {        return new Location(location.getWorld(), x, location.getBlockY(), location.getBlockZ());    }    public static Location setBlockY(Location location, int y) {        return new Location(location.getWorld(), location.getBlockX(), y, location.getBlockZ());    }    public static Location setBlockZ(Location location, int z) {        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), z);    }    public static String getFormat(Location location) {        return location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();    }    public static int getHighestBlockYAt(Location location) {        if (location.getWorld() == null) return 0;        return location.getWorld().getHighestBlockYAt(location);    }    public static Location getBlockAt(Location location) {        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getYaw(), location.getPitch());    }    public static String getEntityLocation(Location location) {        if (location.getWorld() == null) return null;        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();    }    public static Location getEntityLocation(String s) {        String[] str = s.split(":");        World world = Bukkit.getWorld(str[0]);        double x = Double.parseDouble(str[1]);        double y = Double.parseDouble(str[2]);        double z = Double.parseDouble(str[3]);        float yaw = Float.parseFloat(str[4]);        float pitch = Float.parseFloat(str[5]);        return new Location(world, x, y, z, yaw, pitch);    }    public static String getBlockLocation(Location location) {        if (location.getWorld() == null) return null;        return location.getWorld().getName() + ":" + location.getBlockX() + ":" + location.getBlockY() + ":" + location.getBlockZ();    }    public static Location getBlockLocation(String s) {        String[] str = s.split(":");        World world = Bukkit.getWorld(str[0]);        int x = Integer.parseInt(str[1]);        int y = Integer.parseInt(str[2]);        int z = Integer.parseInt(str[3]);        return new Location(world, x, y, z);    }}
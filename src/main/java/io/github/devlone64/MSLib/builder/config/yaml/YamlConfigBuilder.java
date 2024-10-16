package io.github.devlone64.MSLib.builder.config.yaml;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.builder.config.ConfigBuilderProvider;
import io.github.devlone64.MSLib.util.item.ItemUtil;
import io.github.devlone64.MSLib.util.location.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class YamlConfigBuilder implements ConfigBuilderProvider {

    private File config;
    private String name;
    private boolean firstTime;
    private YamlConfiguration yml;

    public YamlConfigBuilder(MSPlugin plugin, String name) {
        this.firstTime = false;

        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                MSPlugin.INSTANCE.getLogger().severe("Cloud not create %s".formatted(dataFolder.getPath()));
                return;
            }
        }

        this.config = new File(dataFolder, name);
        if (!this.config.exists()) {
            this.firstTime = true;
            MSPlugin.INSTANCE.getLogger().info("Creating %s".formatted(this.config.getPath()));
            try {
                if (!this.config.createNewFile()) {
                    MSPlugin.INSTANCE.getLogger().severe("Cloud not create %s".formatted(this.config.getPath()));
                    return;
                }
            } catch (IOException e) {
                MSPlugin.INSTANCE.getLogger().severe(e.getMessage());
            }
        }

        yml = YamlConfiguration.loadConfiguration(config);
        yml.options().copyDefaults(true);
        this.name = name;
    }

    public YamlConfigBuilder(MSPlugin plugin, String dir, String name) {
        this.firstTime = false;

        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                MSPlugin.INSTANCE.getLogger().severe("Cloud not create %s".formatted(dataFolder.getPath()));
                return;
            }
        }

        File directory = new File(plugin.getDataFolder(), dir);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                MSPlugin.INSTANCE.getLogger().severe("Cloud not create %s".formatted(directory.getPath()));
                return;
            }
        }

        this.config = new File(dir, name);
        if (!this.config.exists()) {
            this.firstTime = true;
            MSPlugin.INSTANCE.getLogger().info("Creating %s".formatted(this.config.getPath()));
            try {
                if (!this.config.createNewFile()) {
                    MSPlugin.INSTANCE.getLogger().severe("Cloud not create %s".formatted(this.config.getPath()));
                    return;
                }
            } catch (IOException e) {
                MSPlugin.INSTANCE.getLogger().severe(e.getMessage());
            }
        }

        yml = YamlConfiguration.loadConfiguration(config);
        yml.options().copyDefaults(true);
        this.name = name;
    }

    public void saveYml() {
        try {
            this.yml.save(this.config);
        } catch (IOException e) {
            MSPlugin.INSTANCE.getLogger().severe(e.getMessage());
        }
    }

    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(this.config);
    }

    public void set(String path, Object value) {
        this.yml.set(path, value);
        this.saveYml();
    }

    public Object get(String path) {
        return yml.get(path);
    }

    public Object get(String path, Object def) {
        return yml.get(path, def);
    }

    public String getString(String path) {
        return yml.getString(path);
    }

    public String getString(String path, String def) {
        return yml.getString(path, def);
    }

    public int getInt(String path) {
        return yml.getInt(path);
    }

    public int getInt(String path, int def) {
        return yml.getInt(path, def);
    }

    public double getDouble(String path) {
        return yml.getDouble(path);
    }

    public double getDouble(String path, double def) {
        return yml.getDouble(path, def);
    }

    public float getFloat(String path) {
        return Float.parseFloat(getString(path));
    }

    public float getFloat(String path, float def) {
        return Float.parseFloat(getString(path, String.valueOf(def)));
    }

    public boolean getBoolean(String path) {
        return yml.getBoolean(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return yml.getBoolean(path, def);
    }

    public List<?> getList(String path) {
        return yml.getList(path);
    }

    public List<?> getList(String path, List<?> def) {
        return yml.getList(path, def);
    }

    public List<String> getStringList(String path) {
        return yml.getStringList(path);
    }

    public boolean contains(String path) {
        return yml.contains(path);
    }

    public boolean exists() {
        return config.exists();
    }

    public String convertLocation(Location location) {
        return LocationUtil.getEntityLocation(location);
    }

    public Location convertLocation(String string) {
        return LocationUtil.getEntityLocation(string);
    }

    public String convertBlock(Location location) {
        return LocationUtil.getBlockLocation(location);
    }

    public Location convertBlock(String string) {
        return LocationUtil.getBlockLocation(string);
    }

    public String convertItem(ItemStack item) {
        return ItemUtil.encode(item);
    }

    public ItemStack convertItem(String string) {
        return ItemUtil.decode(string);
    }

    public boolean compareArenaLoc(Location pos1, Location pos2) {
        return pos1.getBlockX() == pos2.getBlockX() && pos1.getBlockZ() == pos2.getBlockZ() && pos1.getBlockY() == pos2.getBlockY();
    }

}
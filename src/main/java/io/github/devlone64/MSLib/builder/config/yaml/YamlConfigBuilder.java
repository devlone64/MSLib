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
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class YamlConfigBuilder implements ConfigBuilderProvider {

    private File config;
    private String name;
    private boolean firstTime;
    private YamlConfiguration yml;

    public YamlConfigBuilder(MSPlugin plugin, String name) {
        this(plugin, name, false);
    }

    public YamlConfigBuilder(MSPlugin plugin, String dir, String name) {
        this(plugin, dir, name, false);
    }

    public YamlConfigBuilder(MSPlugin plugin, String name, boolean isDir) {
        this.firstTime = false;

        var dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                MSPlugin.LOGGER.severe("Cloud not create folder to '%s'.".formatted(dataFolder.getPath()));
                return;
            }
        }

        this.config = new File(dataFolder, name);
        if (!this.config.exists()) {
            this.firstTime = true;
            MSPlugin.LOGGER.info("Creating to '%s'".formatted(this.config.getPath()));
            if (!isDir) {
                try {
                    if (!this.config.createNewFile()) {
                        MSPlugin.LOGGER.severe("Cloud not create file to '%s'.".formatted(this.config.getPath()));
                        return;
                    }
                } catch (IOException e) {
                    MSPlugin.LOGGER.severe(e.getMessage());
                }
            } else {
                if (!this.config.mkdirs()) {
                    MSPlugin.LOGGER.severe("Cloud not create folder to '%s'.".formatted(this.config.getPath()));
                    return;
                }
            }
        }

        this.yml = YamlConfiguration.loadConfiguration(config);
        this.yml.options().copyDefaults(true);
        this.name = name;
    }

    public YamlConfigBuilder(MSPlugin plugin, String dir, String name, boolean isDir) {
        this.firstTime = false;

        var dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                MSPlugin.LOGGER.severe("Cloud not create folder to '%s'.".formatted(dataFolder.getPath()));
                return;
            }
        }

        var directory = new File(dataFolder, dir);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                MSPlugin.LOGGER.severe("Cloud not create folder to '%s'.".formatted(directory.getPath()));
                return;
            }
        }

        this.config = new File(directory, name);
        if (!this.config.exists()) {
            this.firstTime = true;
            MSPlugin.LOGGER.info("Creating to '%s'".formatted(this.config.getPath()));
            if (!isDir) {
                try {
                    if (!this.config.createNewFile()) {
                        MSPlugin.LOGGER.severe("Cloud not create file to '%s'.".formatted(this.config.getPath()));
                        return;
                    }
                } catch (IOException e) {
                    MSPlugin.LOGGER.severe(e.getMessage());
                }
            } else {
                if (!this.config.mkdirs()) {
                    MSPlugin.LOGGER.severe("Cloud not create folder to '%s'.".formatted(this.config.getPath()));
                    return;
                }
            }
        }

        this.yml = YamlConfiguration.loadConfiguration(config);
        this.yml.options().copyDefaults(true);
        this.name = name;
    }

    public void saveYml() {
        try {
            this.yml.save(this.config);
        } catch (IOException e) {
            MSPlugin.LOGGER.severe(e.getMessage());
        }
    }

    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(getConfig());
    }

    public void set(String path, Object value) {
        getYml().set(path, value);
        saveYml();
    }

    public Object get(String path) {
        return yml.get(path);
    }

    public Object get(String path, Object def) {
        return getYml().get(path, def);
    }

    public String getString(String path) {
        return getYml().getString(path);
    }

    public String getString(String path, String def) {
        return getYml().getString(path, def);
    }

    public int getInt(String path) {
        return getYml().getInt(path);
    }

    public int getInt(String path, int def) {
        return getYml().getInt(path, def);
    }

    public double getDouble(String path) {
        return getYml().getDouble(path);
    }

    public double getDouble(String path, double def) {
        return getYml().getDouble(path, def);
    }

    public float getFloat(String path) {
        return Float.parseFloat(getString(path));
    }

    public float getFloat(String path, float def) {
        return Float.parseFloat(getString(path, String.valueOf(def)));
    }

    public boolean getBoolean(String path) {
        return getYml().getBoolean(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return getYml().getBoolean(path, def);
    }

    public List<?> getList(String path) {
        return getYml().getList(path);
    }

    public List<?> getList(String path, List<?> def) {
        return getYml().getList(path, def);
    }

    public List<String> getStringList(String path) {
        return getYml().getStringList(path);
    }

    public List<String> getKeys() {
        return new ArrayList<>(getYml().getKeys(false));
    }

    public List<String> getKeys(String path) {
        var section = getYml().getConfigurationSection(path);
        return section != null ? new ArrayList<>(section.getKeys(false)) : new ArrayList<>();
    }

    public boolean contains(String path) {
        return getYml().contains(path);
    }

    public boolean exists() {
        return getConfig().exists();
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
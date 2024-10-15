package dev.lone64.mslib.framework;

import dev.lone64.mslib.framework.builder.input.InputBuilder;
import dev.lone64.mslib.framework.builder.input.listener.InputListener;
import dev.lone64.mslib.framework.builder.inventory.event.ClickEvent;
import dev.lone64.mslib.framework.builder.inventory.event.CloseEvent;
import dev.lone64.mslib.framework.builder.inventory.impl.BukkitInventory;
import dev.lone64.mslib.framework.builder.inventory.impl.CustomInventory;
import dev.lone64.mslib.framework.command.BaseCommand;
import dev.lone64.mslib.framework.command.LoadCommand;
import dev.lone64.mslib.framework.command.data.Types;
import dev.lone64.mslib.framework.command.impl.custom.manager.CommandManager;
import dev.lone64.mslib.framework.spigot.MCScheduler;
import dev.lone64.mslib.framework.util.Console;
import dev.lone64.mslib.framework.util.message.Component;
import dev.lone64.mslib.framework.util.version.VersionUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public class MSPlugin extends JavaPlugin implements Listener {

    public static String PREFIX;

    private final CommandManager commandManager;

    public MSPlugin() {
        PREFIX = Component.from("<GRADIENT:FF9633>%s</GRADIENT:FFD633>&r".formatted(getName()));

        this.commandManager = new CommandManager(this);
    }

    @Override
    public void onLoad() {
        onInit();
    }

    @Override
    public void onEnable() {
        if (VersionUtil.isSupportVersion()) {
            Bukkit.getPluginManager().disablePlugin(this);
            Console.error("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            Console.error("%s 플러그인은 %s 버전을 지원하지 않습니다.".formatted(getName(), VersionUtil.getVersion()));
            Console.error("올바른 버전에서 플러그인을 사용해주세요.");
            Console.error("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            return;
        }

        onStart();
    }

    @Override
    public void onDisable() {
        onStop();
    }

    public void onInit() { }
    public void onStart() { }
    public void onStop() { }

    public void registerPermission(Permission permission) {
        Bukkit.getPluginManager().addPermission(permission);
    }

    public void registerCommands(Types types, BaseCommand... commands) {
        registerCommands(types, List.of(commands));
    }

    public void registerListeners(Listener... listeners) {
        registerListeners(List.of(listeners));
    }

    public void registerCommands(Types types, List<BaseCommand> commands) {
        if (types == Types.BUKKIT) {
            getCommandManager().spigots(commands);
        } else if (types == Types.CUSTOM) {
            getCommandManager().customs(commands);
        }
    }

    public void registerListeners(List<Listener> listeners) {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled=true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (InputBuilder.is(event.getPlayer())) {
            InputBuilder.remove(event.getPlayer());
        }
    }

    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled=true)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        for (LoadCommand mapper : getCommandManager().getCommands()) {
            if (mapper.getName() != null && mapper.getName().toLowerCase().contains(event.getMessage().replace("/", ""))) {
                if (mapper.getPermission() == null || mapper.getPermission().isEmpty()) return;
                if (!player.hasPermission(mapper.getPermission())) {
                    event.setCancelled(true);
                    player.sendMessage(Component.from(mapper.getPrefix() + mapper.getPermissionMessage()));
                }
            }
        }
    }

    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled=true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        InputListener listener = InputBuilder.get(event.getPlayer());
        if (InputBuilder.is(event.getPlayer())) {
            event.setCancelled(true);
            if (listener.onInit(event.getPlayer(), event.getMessage())) {
                MCScheduler.sync(() -> InputBuilder.remove(event.getPlayer()));
            }
        }
    }

    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled=true)
    public void onPlayerMove(PlayerMoveEvent event) {
        InputListener listener = InputBuilder.get(event.getPlayer());
        if (InputBuilder.is(event.getPlayer())) {
            Location location = event.getPlayer().getLocation().clone().subtract(0, 1, 0);
            if (location.getBlock().getType() == Material.AIR && listener.onCancel(event.getPlayer())) {
                InputBuilder.remove(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (event.getInventory().getHolder() instanceof BukkitInventory inventory) {
                ClickEvent clickEvent = new ClickEvent(
                        event.isCancelled(),
                        player,
                        event.getCurrentItem(),
                        event.getClick(),
                        event.getView(),
                        event.getClickedInventory(),
                        event.getAction(),
                        event.getSlot(),
                        event.getRawSlot(),
                        event.getHotbarButton()
                );
                inventory.onClick(clickEvent);
                event.setCancelled(clickEvent.cancelled);
            }

            if (event.getInventory().getHolder() instanceof CustomInventory inventory) {
                ClickEvent clickEvent = new ClickEvent(
                        event.isCancelled(),
                        player,
                        event.getCurrentItem(),
                        event.getClick(),
                        event.getView(),
                        event.getClickedInventory(),
                        event.getAction(),
                        event.getSlot(),
                        event.getRawSlot(),
                        event.getHotbarButton()
                );
                inventory.onClick(clickEvent);
                event.setCancelled(clickEvent.cancelled);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            if (event.getInventory().getHolder() instanceof BukkitInventory inventory) {
                CloseEvent closeEvent = new CloseEvent(
                        player,
                        event.getView(),
                        event.getInventory()
                );
                inventory.onClose(closeEvent);
            }

            if (event.getInventory().getHolder() instanceof CustomInventory inventory) {
                CloseEvent closeEvent = new CloseEvent(
                        player,
                        event.getView(),
                        event.getInventory()
                );
                inventory.onClose(closeEvent);
            }
        }
    }

}
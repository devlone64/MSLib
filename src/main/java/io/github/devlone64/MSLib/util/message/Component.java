package io.github.devlone64.MSLib.util.message;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.builder.config.ConfigBuilderProvider;
import io.github.devlone64.MSLib.builder.config.custom.CustomConfigBuilder;
import io.github.devlone64.MSLib.builder.config.yaml.YamlConfigBuilder;
import io.github.devlone64.MSLib.nms.NmsVersion;
import io.github.devlone64.MSLib.util.color.ColorUtil;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Component {

    public static String from(ConfigBuilderProvider config, String path) {
        if (config instanceof CustomConfigBuilder c) {
            return from(c.getString(path).replace("{말머리}", MSPlugin.PREFIX));
        } else if (config instanceof YamlConfigBuilder c) {
            return from(c.getString(path).replace("{말머리}", MSPlugin.PREFIX));
        }
        return null;
    }

    public static List<String> fromList(ConfigBuilderProvider config, String path) {
        if (config instanceof CustomConfigBuilder c) {
            return from(c.getList(path));
        } else if (config instanceof YamlConfigBuilder c) {
            return from(c.getStringList(path));
        }
        return null;
    }

    public static String from(String message) {
        if (NmsVersion.getCurrentVersion().isGradientVersion())
            return ColorUtil.format(ChatColor.translateAlternateColorCodes('&', getHexColor(message)));
        return ChatColor.translateAlternateColorCodes('&', getHexColor(message));
    }

    public static List<String> from(List<String> messages) {
        return messages.stream().map(Component::from).toList();
    }

    public static String getHexColor(String message) {
        Pattern pattern = Pattern.compile("<#[a-fA-F0-9]{6}>");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message).replace('&', '§');
    }

    public static String from(String[] args, int start) {
        StringBuilder w = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            w.append(args[i]).append(" ");
        }
        w = new StringBuilder(w.substring(0, w.length() - 1));
        return w.toString();
    }

    public static String substring(String s, String s1, String s2) {
        s = s.substring(s.indexOf(s1) + 1);
        s = s.substring(0, s.indexOf(s2));
        return s;
    }

}
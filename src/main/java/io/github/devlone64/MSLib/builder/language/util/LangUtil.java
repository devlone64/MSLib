package io.github.devlone64.MSLib.builder.language.util;

import io.github.devlone64.MSLib.builder.language.data.Language;
import io.github.devlone64.MSLib.builder.language.storage.CachedUserList;
import io.github.devlone64.MSLib.util.message.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class LangUtil {

    public static String getMsg(Player player, String path) {
        Language language = CachedUserList.get(player.getUniqueId());
        if (language == null) return null;
        return Component.from(language.getString(path));
    }

    public static String getMsg(Player player, String path, String def) {
        Language language = CachedUserList.get(player.getUniqueId());
        if (language == null) return null;
        return Component.from(language.getString(path, def));
    }

    public static List<String> getMsgList(Player player, String path) {
        Language language = CachedUserList.get(player.getUniqueId());
        if (language == null) return null;
        return Component.from(language.getStringList(path));
    }

}
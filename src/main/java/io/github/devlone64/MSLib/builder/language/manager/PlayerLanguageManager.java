package io.github.devlone64.MSLib.builder.language.manager;

import io.github.devlone64.MSLib.builder.language.data.Language;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerLanguageManager {

    private final Map<UUID, Language> languageMap = new HashMap<>();

    public void setLanguage(Player player, Language language) {
        getLanguages().put(player.getUniqueId(), language);
    }

    public Language removeLanguage(Player player) {
        return getLanguages().remove(player.getUniqueId());
    }

    public Language getLanguage(Player player) {
        return getLanguages().get(player.getUniqueId());
    }

    public boolean isLanguage(Player player) {
        return getLanguage(player) != null;
    }

    public Map<UUID, Language> getLanguages() {
        return languageMap;
    }

}
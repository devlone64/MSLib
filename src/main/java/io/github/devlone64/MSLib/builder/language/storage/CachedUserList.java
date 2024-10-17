package io.github.devlone64.MSLib.builder.language.storage;

import io.github.devlone64.MSLib.builder.language.data.Language;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CachedUserList {
    
    @Getter private static final Map<UUID, Language> languages = new HashMap<>();

    public static Language set(UUID uuid, Language language) {
        getLanguages().put(uuid, language);
        return language;
    }

    public static Language remove(UUID uuid) {
        return getLanguages().remove(uuid);
    }

    public static Language get(UUID uuid) {
        return getLanguages().get(uuid);
    }

    public static boolean is(UUID uuid) {
        return get(uuid) != null;
    }
    
}
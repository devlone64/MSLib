package io.github.devlone64.MSLib.builder.language.storage;

import io.github.devlone64.MSLib.builder.language.data.Language;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CachedLangList {

    @Getter private static final Map<String, Language> languages = new HashMap<>();

    public static Language set(String name, Language language) {
        getLanguages().put(name, language);
        return language;
    }

    public static Language remove(String name) {
        return getLanguages().remove(name);
    }

    public static Language get(String name) {
        return getLanguages().get(name);
    }

    public static boolean is(String name) {
        return get(name) != null;
    }

}
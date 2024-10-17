package io.github.devlone64.MSLib.builder.language.storage;

import io.github.devlone64.MSLib.builder.database.impl.database.SQLiteDatabase;
import io.github.devlone64.MSLib.builder.language.data.Language;

import java.util.UUID;

public class CachedUserDB extends SQLiteDatabase {

    public CachedUserDB() {
        super("database/users.db");

        createTable("lang_data", "user_id VARCHAR(36), lang_id VARCHAR(36)");
    }

    public static void setLang(UUID uuid, Language language) {
        CachedUserDB cachedUserDB = new CachedUserDB();
        if (!cachedUserDB.is("lang_data")) return;
        var selected = "user_id, lang_id";
        var values = new Object[]{ uuid.toString(), language.getName() };
        cachedUserDB.set("lang_data", selected, values, "=", "user_id", uuid.toString());
    }

    public static void removeLang(UUID uuid) {
        CachedUserDB cachedUserDB = new CachedUserDB();
        if (!cachedUserDB.is("lang_data")) return;
        var userId = uuid.toString();
        if (!cachedUserDB.is("lang_data", "user_id", userId)) return;
        cachedUserDB.remove("lang_data", "=", "user_id", userId);
    }

    public static String getLang(UUID uuid) {
        CachedUserDB cachedUserDB = new CachedUserDB();
        if (!cachedUserDB.is("lang_data")) return null;
        var userId = uuid.toString();
        if (!cachedUserDB.is("lang_data", "user_id", userId)) return null;
        var selected = "lang_id";
        return cachedUserDB.get("lang_data", selected, "=", "user_id", userId).toString();
    }

    public static boolean isLang(UUID uuid) {
        return getLang(uuid) != null;
    }

}
package io.github.devlone64.MSLib.builder.language;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.builder.config.yaml.YamlConfigBuilder;
import io.github.devlone64.MSLib.builder.language.data.Language;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class LanguageBuilder extends YamlConfigBuilder {

    private final String folderName;
    private final Map<Class<? extends Language>, Language> languageMap = new HashMap<>();

    public LanguageBuilder(String folderName) {
        super(MSPlugin.INSTANCE, folderName, true);

        this.folderName = folderName;
    }

    public void reloadAll() {
        for (var entry : getLanguageMap().values()) {
            entry.reload();
        }
    }

    public void addLanguage(Language... languages) {
        for (var language : languages) {
            getLanguageMap().put(language.getClass(), language);
        }
    }

    public void addLanguage(List<Language> languages) {
        addLanguage(languages.toArray(new Language[0]));
    }

    public Language getLanguage(String langId) {
        for (var language : getLanguageMap().values()) {
            if (language.getId().equalsIgnoreCase(langId)) {
                return language;
            }
        }
        return null;
    }

    public boolean isLanguage(String langId) {
        return getLanguage(langId) != null;
    }

    public List<String> getLanguageKeys() {
        return getLanguageMap().values().stream().map(Language::getId).toList();
    }

    public List<Language> getLanguages() {
        return new ArrayList<>(getLanguageMap().values());
    }

}
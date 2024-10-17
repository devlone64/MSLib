package io.github.devlone64.MSLib.builder.language.data;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.builder.config.yaml.YamlConfigBuilder;
import io.github.devlone64.MSLib.builder.language.LanguageBuilder;
import lombok.Getter;

@Getter
public class Language extends YamlConfigBuilder {

    private final boolean enabled;
    private final String id;
    private final String name;
    private final String fileName;

    public Language(LanguageBuilder lang, boolean enabled, String id, String name, String fileName) {
        super(MSPlugin.INSTANCE, lang.getFolderName(), fileName);

        this.enabled = enabled;
        this.id = id;
        this.name = name;
        this.fileName = fileName;
    }

    public Language(LanguageBuilder lang, String id, String name, String fileName) {
        this(lang, true, id, name, fileName);
    }

    public Language(LanguageBuilder lang, String id, String name) {
        this(lang, true, id, name, "%s.yml".formatted(name));
    }

}
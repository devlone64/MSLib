package io.github.devlone64.MSLib.builder.language.data;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.builder.config.yaml.YamlConfigBuilder;
import io.github.devlone64.MSLib.builder.language.LanguageBuilder;
import lombok.Getter;

@Getter
public class Language extends YamlConfigBuilder {

    private final boolean enabled;
    private final String name;

    public Language(LanguageBuilder lang, String name) {
        this(lang, true, name);
    }

    public Language(LanguageBuilder lang, boolean enabled, String name) {
        super(MSPlugin.INSTANCE, lang.getFolderName(), "messages_%s.yml".formatted(name));

        this.enabled = enabled;
        this.name = name;
    }

}
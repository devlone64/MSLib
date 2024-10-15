package dev.lone64.mslib.framework.builder.database;

import dev.lone64.mslib.framework.builder.database.data.SQLDatabase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatabaseBuilder {
    private final SQLDatabase database;
}
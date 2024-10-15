package com.github.devlone64.MSLib.builder.database.data;

import com.github.devlone64.MSLib.builder.database.handler.ClassHandler;

import java.util.List;

public interface SQLDatabase {
    boolean createTable(String table, String columns);
    boolean deleteTable(String table);

    boolean set(String table, String selected, Object[] values, String logic, String column, String data);
    boolean remove(String table, String logic, String column, String data);

    Object get(String table, String selected, String logic, String column, String data);
    <T> T get(String table, String selected, String logic, String column, String data, ClassHandler<T> handler);

    List<Object> getList(String table, String selected, String logic, String column, String data);
    <T> List<T> getList(String table, String selected, String logic, String column, String data, ClassHandler<T> handler);

    boolean is(String table);
    boolean is(String table, String column, String data);
}
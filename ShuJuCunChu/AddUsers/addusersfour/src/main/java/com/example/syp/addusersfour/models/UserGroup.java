package com.example.syp.addusersfour.models;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

import com.example.syp.addusersfour.db.DbConnector;


/**
 * Created by syp on 17-6-7.
 * <p>
 * 工厂模式   自定义Cursor
 */

public class UserGroup {
    private int id;
    private String name;

    public UserGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
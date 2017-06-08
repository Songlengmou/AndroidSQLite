package com.example.syp.adduserstwo.models;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

import com.example.syp.adduserstwo.db.DbConnector;

/**
 * Created by syp on 17-6-7.
 * <p>
 * 工厂模式   自定义Cursor
 */

public class UserCursor extends SQLiteCursor {
    public UserCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
        super(driver, editTable, query);
    }

    public int getId() {
        return getInt(getColumnIndex(DbConnector.ID_COLUMN_NAME));
    }

    public String getName() {
        return getString(getColumnIndex(DbConnector.NAME_COLUMN_NAME));
    }

    public int getAge() {
        return getInt(getColumnIndex(DbConnector.AGE_COLUMN_NAME));
    }
}
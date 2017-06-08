package com.example.syp.addusersthree.models;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

import com.example.syp.addusersthree.db.DbConnector;

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
        return getInt(getColumnIndex(DbConnector.COLUMN_NAME_ID));
    }

    public String getName() {
        return getString(getColumnIndex(DbConnector.COLUMN_NAME_NAME));
    }

    public int getAge() {
        return getInt(getColumnIndex(DbConnector.COLUMN_NAME_AGE));
    }
}
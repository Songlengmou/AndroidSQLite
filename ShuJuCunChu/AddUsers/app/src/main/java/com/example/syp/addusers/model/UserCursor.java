package com.example.syp.addusers.model;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

/**
 * Created by syp on 17-6-7.
 * <p>
 * 工厂模式   自定义Cursor
 */

public class UserCursor extends SQLiteCursor {
    public UserCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
        super(driver, editTable, query);
    }

    public String getName() {
        return getString(getColumnIndex("name"));
    }

    public int getAge() {
        return getInt(getColumnIndex("age"));
    }
}

package com.example.syp.addusersfour.db;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

/**
 * Created by syp on 17-6-9.
 */

public class DbCursor extends SQLiteCursor {
    public DbCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
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

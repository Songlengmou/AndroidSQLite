package com.example.syp.addusers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

import com.example.syp.addusers.model.UserCursor;

/**
 * Created by syp on 17-6-7.
 */

public class DbConnector extends SQLiteOpenHelper {
    public static final String DB_NAME = "users.db";
    public static final int DB_VERSION = 1;//数据库版本号
    public static final String USER_TABLE_NAME = "user";
    public static final String ID_COLUMN_NAME = "_id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String AGE_COLUMN_NAME = "age";

    private SQLiteDatabase writableDatabase;

    public DbConnector(Context context) {
        super(context, DB_NAME, new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                return new UserCursor(masterQuery, editTable, query);
            }
        }, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + "(" +
                ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME_COLUMN_NAME + " TEXT NOT NULL DEFAULT NONE," +
                AGE_COLUMN_NAME + " INTEGER DEFAULT 1)");
    }

    public void insertUser(String name, int age) {
        writableDatabase = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put(NAME_COLUMN_NAME, name);
        cvs.put(AGE_COLUMN_NAME, age);
        writableDatabase.insert(USER_TABLE_NAME, "", cvs);
    }

    public void close() {
        writableDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.syp.addusersthree.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

import com.example.syp.addusersthree.models.UserCursor;

/**
 * Created by syp on 17-6-7.
 * <p>
 * 更新文档描述
 */

public class DbConnector extends SQLiteOpenHelper {
    public static final String DB_NAME = "users.db";
    public static final int DB_VERSION = 2;
    public static final String TABLE_NAME_USER = "user";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_AGE = "age";

    public static final String TABLE_NAME_GROUP = "user_group";
    public static final String COLUMN_NAME_GROUP_ID = "group_id";

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    //连接数据库
    public DbConnector(Context context) {
        super(context, DB_NAME, new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                return new UserCursor(masterQuery, editTable, query);
            }
        }, DB_VERSION);
        //获取数据库可读可写的权限
        writableDatabase = getWritableDatabase();
        readableDatabase = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_GROUP + "(" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_NAME + " TEXT NOT NULL DEFAULT \"default group\")");

        initGroupData(db);


        db.execSQL("CREATE TABLE " + TABLE_NAME_USER + "(" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_NAME + " TEXT NOT NULL DEFAULT NONE," +
                COLUMN_NAME_AGE + " INTEGER DEFAULT 1," +
                COLUMN_NAME_GROUP_ID + " INTEGER DEFAULT 1)");


//        //v1
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + "(" +
//                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                COLUMN_NAME_NAME + " TEXT NOT NULL DEFAULT NONE," +
//                COLUMN_NAME_AGE + " INTEGER DEFAULT 1)");
    }

    /**
     * 初始化用户组数据
     */
    private void initGroupData(SQLiteDatabase db) {
        ContentValues cvs = new ContentValues();
        cvs.put(COLUMN_NAME_ID, 1);
        cvs.put(COLUMN_NAME_NAME, "default group");
        db.insert(TABLE_NAME_GROUP, null, cvs);
    }

    //插入数据
    public void insertUser(String name, int age) {
        ContentValues cvs = new ContentValues();
        cvs.put(COLUMN_NAME_NAME, name);
        cvs.put(COLUMN_NAME_AGE, age);
        writableDatabase.insert(TABLE_NAME_USER, "", cvs);
    }

    //自定义查询
    public UserCursor queryUsers() {
        return (UserCursor) readableDatabase.query("user", null, null, null, null, null, null);
    }

    /**
     * 根据id删除该条数据
     *
     * @param id
     */
    public void delete(int id) {
        writableDatabase.delete(TABLE_NAME_USER, "_id=?", new String[]{String.valueOf(id)});
    }

    //修改数据
    public void updateUser(int id, String userName, int age) {
        ContentValues cvs = new ContentValues();
        cvs.put(COLUMN_NAME_NAME, userName);
        cvs.put(COLUMN_NAME_AGE, age);

        writableDatabase.update(TABLE_NAME_USER, cvs, COLUMN_NAME_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void close() {
        writableDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade from 1 to 2
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL("CREATE TABLE " + TABLE_NAME_GROUP + "(" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_NAME + " TEXT NOT NULL DEFAULT \"default group\")");
            initGroupData(db);

            //upgrade user table
            db.execSQL("ALTER TABLE " + TABLE_NAME_USER + " ADD COLUMN" + COLUMN_NAME_GROUP_ID + " INTEGER DEFAULT 1");
        }
    }
}


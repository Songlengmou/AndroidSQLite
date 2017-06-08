package com.example.syp.userlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;

import com.example.syp.userlist.databinding.ActivityMainBinding;

/**
 * Created by syp on 17-6-6.
 * <p>
 * 使用sqlite数据库基本API
 * <p>
 * <p>
 * 如果表已存在，不重复创建
 */

public class Controller {
    private final ActivityMainBinding binding;
    private UserListAdapter adapter;
    private SQLiteDatabase database;
    private ContentValues cvs;

    public Controller(ActivityMainBinding binding) {
        this.binding = binding;

        adapter = new UserListAdapter(null, binding.getRoot().getContext());
        createSqliteDatabase();

        initUI();

        readDataFromDB();
    }

    private void createSqliteDatabase() {
        database = binding.getRoot().getContext().openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL DEFAULT \"\"," +
                "age TEXT NOT NULL DEFAULT \"\")");

//-----------如果表已存在，不重复创建-------------
//    (第一次运行时打开备注的这些，第二次把它注释掉也可以呈现数据)

        //手动插入数据
//        cvs = new ContentValues();
//        cvs.put("name", "张三");
//        cvs.put("age", "18");
//        database.insert("user", "", cvs);
//
//        cvs = new ContentValues();
//        cvs.put("name", "李四");
//        cvs.put("age", "18");
//        database.insert("user", "", cvs);
//
//        cvs = new ContentValues();
//        cvs.put("name", "王五");
//        cvs.put("age", "18");
//        database.insert("user", "", cvs);
    }

    //读取(查询)数据
    private void readDataFromDB() {
        Cursor userCursor = database.query("user", null, null, null, null, null, null);
        adapter.setCursor(userCursor);
    }

    //设置布局
    private void initUI() {
        binding.userList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false));
        binding.userList.setAdapter(adapter);
    }
}

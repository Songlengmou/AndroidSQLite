package com.example.syp.addusertolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 将数据库中的数据同步到列表中2  (1在UserList项目中)
 * <p>
 * 保存并且查询数据
 */

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private RecyclerView userList;
    private UserListAdapter adapter;

    public static final int REQUEST_CODE_ADO_USER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//-----------------------------以下基本是自己写-------------------------------------------------
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //右侧加号的跳转
                startActivityForResult(new Intent(MainActivity.this, EditUserActivity.class), REQUEST_CODE_ADO_USER);
            }
        });

        userList = (RecyclerView) findViewById(R.id.userList);
        //设置列表的布局方式
        userList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new UserListAdapter(null, this);
        userList.setAdapter(adapter);

        connectDb();
        readFromDb();
    }

    //建立数据库连接
    private void connectDb() {
        database = openOrCreateDatabase("data.db", MODE_PRIVATE, new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                return new UserCursor(masterQuery, editTable, query);
            }
        });

        initDb();
    }

    private void initDb() {
        database.execSQL("CREATE TABLE IF NOT EXISTS user" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL DEFAULT NONE ," +
                "age INTEGER NOT NULL DEFAULT 1)");
    }

    //从数据库读取数据
    private void readFromDb() {
        UserCursor userCursor = (UserCursor) database.query("user", null, null, null, null, null, null);
        adapter.setCursor(userCursor);
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    //插入信息(ContentValues一般是插入数值对)
    private void insertUserToDb(String name, int age) {
        ContentValues cvs = new ContentValues();
        cvs.put("name", name);
        cvs.put("age", age);
        database.insert("user", "", cvs);

        readFromDb();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADO_USER:
                switch (resultCode) {
                    case EditUserActivity.RESULT_SAVE:
                        insertUserToDb(
                                data.getStringExtra(EditUserActivity.KEY_USER_NAME),
                                data.getIntExtra(EditUserActivity.KEY_USER_AGE, 0)
                        );
                        break;
                    case EditUserActivity.RESULT_CLOSE://(这行可写可不写)
                        Toast.makeText(MainActivity.this, "没有可保存的数据", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

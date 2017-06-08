package com.example.syp.addusers.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.syp.addusers.R;
import com.example.syp.addusers.model.UserCursor;
import com.example.syp.addusers.adapters.UserListAdapter;
import com.example.syp.addusers.db.DbConnector;

/**
 * 1.
 * <p>
 * 将数据库中的数据同步到列表中3  (1在UserList项目中)
 * <p>
 * SQLiteOpenHelper的使用
 * <p>
 * (利用的是 RecyclerView)
 */

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase readableDatabase;
    private RecyclerView userList;
    private UserListAdapter adapter;
    private DbConnector dbConnector;

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
        dbConnector = new DbConnector(this);
        readableDatabase = dbConnector.getReadableDatabase();
    }

    //从数据库读取数据
    private void readFromDb() {
        UserCursor userCursor = (UserCursor) readableDatabase.query("user", null, null, null, null, null, null);
        adapter.setCursor(userCursor);
    }

    @Override
    protected void onDestroy() {
        readableDatabase.close();
        dbConnector.close();
        super.onDestroy();
    }

    //插入信息(ContentValues一般是插入数值对)
    private void insertUserToDb(String name, int age) {
        dbConnector.insertUser(name, age);

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

package com.example.syp.addusersfour.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.syp.addusersfour.R;
import com.example.syp.addusersfour.controller.MainController;
import com.example.syp.addusersfour.controller.UserListController;
import com.example.syp.addusersfour.databinding.ActivityMainBinding;


/**
 * 4.实现Fragment事件响应处理
 * 完成用户组相关操作
 */

public class MainActivity extends AppCompatActivity {
    private MainController mainController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //实例化MainController
        mainController = new MainController(binding, this);
        setSupportActionBar(binding.toolbar);
        binding.setController(mainController);
    }

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


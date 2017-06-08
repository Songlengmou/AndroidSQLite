package com.example.syp.addusersthree.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.syp.addusersthree.R;
import com.example.syp.addusersthree.controller.MainController;
import com.example.syp.addusersthree.databinding.ActivityMainBinding;


/**
 * 3.数据存储中的：
 * 更新文档描述
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
    protected void onDestroy() {
        mainController.onDestroy();
        super.onDestroy();
    }

    //-------------------------END----------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainController.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
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


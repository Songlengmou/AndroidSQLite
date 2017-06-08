package com.example.syp.userlist;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.syp.userlist.databinding.ActivityMainBinding;

/**
 * 将数据库中的数据同步到列表中 1  (2在AddUserToList项目中)
 * <p>
 * 使用sqlite数据库基本API
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        controller = new Controller(binding);
        binding.setController(controller);
    }
}

package com.example.syp.preference;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.syp.preference.databinding.ActivityMainBinding;

/**
 * 首选项(Preference配置数据)
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new Presenter(binding);
        binding.setPresenter(presenter);
    }
}

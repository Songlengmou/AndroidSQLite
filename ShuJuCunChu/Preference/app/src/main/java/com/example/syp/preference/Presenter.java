package com.example.syp.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.CompoundButton;

import com.example.syp.preference.databinding.ActivityMainBinding;

/**
 * Created by syp on 17-6-6.
 * <p>
 * 首选项(Preference配置数据)
 * <p>
 * <p>
 * Context.MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中。可以使用Context.MODE_APPEND
 * Context.MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
 * Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件。
 * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
 * MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
 */

public class Presenter {

    private static final String PREFERENCE_NAME = "myCheckState";
    private ActivityMainBinding binding;
    public final ObservableBoolean buttonEnabled = new ObservableBoolean(false);
    private SharedPreferences preferences;

    public Presenter(ActivityMainBinding binding) {
        this.binding = binding;

        preferences = this.binding.getRoot().getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        addListeners();
        readCheckState();
    }

    private void addListeners() {
        this.binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonEnabled.set(isChecked);

                saveCheckState();
            }
        });
    }

    private void saveCheckState() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("checked", binding.checkBox.isChecked());//根据单选框判断是否保存 当前状态
        editor.apply();
    }

    //读取选项框的状态
    private void readCheckState() {
        boolean checked = preferences.getBoolean("checked", false);
        binding.checkBox.setChecked(checked);
        buttonEnabled.set(checked);
    }

    //点击按钮所显示的选项框
    public void buttonClickedHandler(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("Hello")
                .setMessage("Hello Android")
                .setPositiveButton("close", null)
                .show();
    }
}

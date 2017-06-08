package com.example.syp.internaistrage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 内部存储
 *
 * 写文件流时 切记不能用 绝对路径
 * <p>
 * available()：
 * ①available不阻塞线程,本地文件小 能够立马读取出来(一般不用这个)
 * ②如果线程阻塞就不用，例如网络通信
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            FileOutputStream data = openFileOutput("data", MODE_PRIVATE);
//            data.write("Hello World".getBytes("utf-8"));
//            data.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//-------------------------------------------------------------------
        try {
            //切记不能用绝对路径
            FileInputStream data = openFileInput("data");
            //available不阻塞线程,本地文件小 能够立马读取出来(一般不用这个)
//            byte[] bytes = new byte[data.available()];
//            data.read(bytes);
//            data.close();
//
//            System.out.println(new String(bytes, "utf-8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(data, "utf-8"));
            String line = br.readLine();
            data.close();

            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

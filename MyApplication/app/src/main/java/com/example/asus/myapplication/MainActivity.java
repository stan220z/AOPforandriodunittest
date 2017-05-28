package com.example.asus.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAOP(0);
    }

//    public boolean testAOP() {
//        Log.d("boolean: ", "testAOP");
//        return true;
//    }
    public void testAOP(int a) {
          Log.d("void :", "testAOP");
    }
    public static void TP(int i)
    {
        Log.d("TP", "ha!"+i);
    }
}
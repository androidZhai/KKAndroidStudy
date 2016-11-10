package com.kkandroidstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kkandroidstudy.R;
import com.kkandroidstudy.ndk.JNI;
import com.orhanobut.logger.Logger;

public class JNITestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnitest);

        int[] intArray = new JNI().increaseArrayEles(new int[]{1, 2, 3, 4, 5});
        for (int array : intArray) {
            Log.e("array", array + "");
        }

        Toast.makeText(this, new JNI().checkPwd("123456") + "", Toast.LENGTH_SHORT).show();

        new JNI().callbackAdd();

        new JNI().callbackHelloFromJava();

        new JNI().callbackPrintString();
    }
}

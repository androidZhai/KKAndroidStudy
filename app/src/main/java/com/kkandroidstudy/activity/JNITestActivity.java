package com.kkandroidstudy.activity;

import android.os.Build;
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

        new JNI().callbackSayHello();

        JNITestActivity.this.callBackToast();
        String packageDir = "/data/data/" + getPackageName();
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        //new JNI().uninstallListener(packageDir, sdkVersion);
        new JNI().uninstallListenerInotify(packageDir,sdkVersion);
    }

    public native void callBackToast();

    public void ToastString() {
        Toast.makeText(JNITestActivity.this, "Hello C", Toast.LENGTH_SHORT).show();
    }
}

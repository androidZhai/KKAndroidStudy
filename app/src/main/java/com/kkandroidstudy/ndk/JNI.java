package com.kkandroidstudy.ndk;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by shiyan on 2016/11/7.
 */
public class JNI {
    public static final String TAG = "JNI";

    static {
        System.loadLibrary("Hello");
    }

    /**
     * 输出字符串
     *
     * @return
     */
    public native String sayHello();

    /**
     * java传入2个数，C进行相加操作，结果返回到java
     *
     * @param x
     * @param y
     * @return
     */
    public native int add(int x, int y);

    /**
     * 从java传入字符串，C进行拼接
     *
     * @param s I am from java
     * @return I am from java add I am from C
     */
    public native String sayString(String s);

    /**
     * 让C代码每个元素都加上10
     *
     * @param intArray
     * @return
     */
    public native int[] increaseArrayEles(int[] intArray);

    /**
     * 应用:检查密码是否正确 正确返回200 错误返回400
     *
     * @param pwd
     * @return
     */
    public native int checkPwd(String pwd);

    /**
     * C调用java add方法
     */
    public native void callbackAdd();

    public native void callbackHelloFromJava();

    public native void callbackPrintString();

    public native void callbackSayHello();

    public int addJava(int x, int y) {
        Log.e(TAG, "x=" + x + "y=" + y);
        return x + y;
    }

    public void HelloFromJava() {
        Log.e(TAG, "HelloFromJava()");
    }

    public void printString(String str) {
        Log.e(TAG, "c中输入的string:" + str);
    }

    public static void sayHello(String str) {
        Log.e(TAG, "我是java中的方法，我被c调用了，参数是:" + str);
    }


}

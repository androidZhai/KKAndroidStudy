//
// Created by shiyan on 2016/11/7.
//
#include<stdio.h>
#include<stdlib.h>
#include<jni.h>
#include<string.h>
#include "com_kkandroidstudy_ndk_JNI.h"

#include <android/log.h>

#define LOG_TAG "shiyan"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

char *jstringTostr(JNIEnv *env, jstring jstr) {
    char *pStr = NULL;

    jclass jstrObj = (*env)->FindClass(env, "java/lang/String");
    jstring encode = (*env)->NewStringUTF(env, "utf-8");
    jmethodID methodId = (*env)->GetMethodID(env, jstrObj, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray byteArray = (jbyteArray) (*env)->CallObjectMethod(env, jstr, methodId, encode);
    jsize strLen = (*env)->GetArrayLength(env, byteArray);
    jbyte *jBuf = (*env)->GetByteArrayElements(env, byteArray, JNI_FALSE);

    if (jBuf > 0) {
        pStr = (char *) malloc(strLen + 1);

        if (!pStr) {
            return NULL;
        }

        memcpy(pStr, jBuf, strLen);

        pStr[strLen] = 0;
    }

    (*env)->ReleaseByteArrayElements(env, byteArray, jBuf, 0);

    return pStr;
}

jstring Java_com_kkandroidstudy_ndk_JNI_sayHello(JNIEnv *env, jobject jobj) {
    //jstring     (*NewStringUTF)(JNIEnv*, const char*);
    char *str = "I am from C!";
    return (*env)->NewStringUTF(env, str);
}

jint Java_com_kkandroidstudy_ndk_JNI_add(JNIEnv *env, jobject jobj, jint ji, jint jj) {
    int result = ji + jj;
    return result;
}

jstring Java_com_kkandroidstudy_ndk_JNI_sayString(JNIEnv *env, jobject jobj, jstring jstr) {
    char *fromJava = jstringTostr(env, jstr);
    char *fromC = "add I am from C";
    strcat(fromJava, fromC);
    return (*env)->NewStringUTF(env, fromJava);
}

/**
   给每个元素加上10
*/
jintArray Java_com_kkandroidstudy_ndk_JNI_increaseArrayEles(JNIEnv *env, jobject jobj,
                                                            jintArray jarray) {
    //获取数组长度
    //jsize       (*GetArrayLength)(JNIEnv*, jarray);
    int size = (*env)->GetArrayLength(env, jarray);
    //得到数组元素
    //jint*       (*GetIntArrayElements)(JNIEnv*, jintArray, jboolean*);
    jint *intArray = (*env)->GetIntArrayElements(env, jarray, JNI_FALSE);
    //遍历数组，给每个元素加上1
    int i;
    for (i = 0; i < size; i++) {
        *(intArray + i) += 10;
    }
    //返回结果
    return jarray;
}

/**
 * 如果密码正确，返回200，否则返回400
 */
jint Java_com_kkandroidstudy_ndk_JNI_checkPwd(JNIEnv *env, jobject jobj, jstring str) {
    char *origin = "123456";
    char *dest = jstringTostr(env, str);
    //extern int    strcmp(const char *, const char *);
    int result = strcmp(origin, dest);
    if (result == 0) {
        return 200;
    }
    return 400;
}

/*
 * Signature: (II)I
 */
void Java_com_kkandroidstudy_ndk_JNI_callbackAdd(JNIEnv *env, jobject jobj) {
    //1.获得字节码
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclazz = (*env)->FindClass(env, "com/kkandroidstudy/ndk/JNI");
    //2.得到方法
    //jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmethodIDs = (*env)->GetMethodID(env, jclazz, "addJava", "(II)I");
    //3.实例化类
    //jobject     (*AllocObject)(JNIEnv*, jclass);
    jobject jobject = (*env)->AllocObject(env, jclazz);
    //4.调用方法
    //jint        (*CallIntMethod)(JNIEnv*, jobject, jmethodID, ...);
    jint value = (*env)->CallIntMethod(env, jobject, jmethodIDs, 99, 1);
    LOGE("value===%d", value);
}

/*
 * Signature: ()V
 * public void HelloFromJava()
 */
void Java_com_kkandroidstudy_ndk_JNI_callbackHelloFromJava(JNIEnv *env, jobject jobj) {
    //1.获得字节码
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclazz = (*env)->FindClass(env, "com/kkandroidstudy/ndk/JNI");
    //2.得到方法
    //jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmethodIDs = (*env)->GetMethodID(env, jclazz, "HelloFromJava", "()V");
    //3.实例化类
    //jobject     (*AllocObject)(JNIEnv*, jclass);
    jobject jobject = (*env)->AllocObject(env, jclazz);
    //4.调用方法
    //jint        (*CallIntMethod)(JNIEnv*, jobject, jmethodID, ...);
    (*env)->CallVoidMethod(env, jobject, jmethodIDs);
}

/*
 * Signature: (Ljava/lang/String;)V
 *  public void printString(String str)
 */
void Java_com_kkandroidstudy_ndk_JNI_callbackPrintString(JNIEnv *env, jobject jobj) {
    //1.获得字节码
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclazz = (*env)->FindClass(env, "com/kkandroidstudy/ndk/JNI");
    //2.得到方法
    //jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmethodIDs = (*env)->GetMethodID(env, jclazz, "printString", "(Ljava/lang/String;)V");
    //3.实例化类
    //jobject     (*AllocObject)(JNIEnv*, jclass);
    jobject jobject = (*env)->AllocObject(env, jclazz);

    jstring str = (*env)->NewStringUTF(env, "I am shiyan");
    //4.调用方法
    //jint        (*CallIntMethod)(JNIEnv*, jobject, jmethodID, ...);
    (*env)->CallVoidMethod(env, jobject, jmethodIDs, str);
}

/*
 * Signature: (Ljava/lang/String;)V
 */
void Java_com_kkandroidstudy_ndk_JNI_callbackSayHello(JNIEnv *env, jobject jobj) {

}

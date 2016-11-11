//
// Created by shiyan on 2016/11/7.
//
#include<stdio.h>
#include<stdlib.h>
#include<jni.h>
#include<string.h>
#include "com_kkandroidstudy_ndk_JNI.h"

#include <android/log.h>
#include <unistd.h>
#include <sys/inotify.h>

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
    //1.获得字节码
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclazz = (*env)->FindClass(env, "com/kkandroidstudy/ndk/JNI");
    //2.得到方法
    //jmethodID   (*GetStaticMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmethodIDs = (*env)->GetStaticMethodID(env, jclazz, "sayHello",
                                                     "(Ljava/lang/String;)V");
    jstring str = (*env)->NewStringUTF(env, "I am static method");
    //3.调用方法
    //   void        (*CallStaticVoidMethod)(JNIEnv*, jclass, jmethodID, ...);
    (*env)->CallStaticVoidMethod(env, jclazz, jmethodIDs, str);
}

/**
 * c调用java代码
 * Signature: ()V
 */
void Java_com_kkandroidstudy_activity_JNITestActivity_callBackToast(JNIEnv *env, jobject instance) {
    //1.获得字节码
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclazz = (*env)->FindClass(env, "com/kkandroidstudy/activity/JNITestActivity");
    //2.得到方法
    // jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmethodIDs = (*env)->GetMethodID(env, jclazz, "ToastString", "()V");
    //2.调用方法
    (*env)->CallVoidMethod(env, instance, jmethodIDs);
}

/**
 * 卸载apk 打开浏览器 问卷调查
 */
void Java_com_kkandroidstudy_ndk_JNI_uninstallListener(JNIEnv *env, jobject obj, jstring packageDir,
                                                       jint sdkVersion) {
    //code>0 父进程id
    //code<0  出错
    //code==0  子进程id
    // 1，将传递过来的java的包名转为c的字符串
    char *pd = jstringTostr(env, packageDir);

    // 2，创建当前进程的克隆进程
    pid_t pid = fork();

    // 3，根据返回值的不同做不同的操作,<0,>0,=0
    if (pid < 0) {
        // 说明克隆进程失败
        LOGD("current crate process failure");
    } else if (pid > 0) {
        // 说明克隆进程成功，而且该代码运行在父进程中
        LOGD("crate process success,current parent pid = %d", pid);
    } else {
        // 说明克隆进程成功，而且代码运行在子进程中
        LOGD("crate process success,current child pid = %d", pid);

        // 4，在子进程中监视/data/data/包名这个目录
        while (JNI_TRUE) {
            FILE *file = fopen(pd, "rt");

            if (file == NULL) {
                // 应用被卸载了，通知系统打开用户反馈的网页
                LOGD("app uninstall,current sdkversion = %d", sdkVersion);
                if (sdkVersion >= 17) {
                    // Android4.2系统之后支持多用户操作，所以得指定用户
                    execlp("am", "am", "start", "--user", "0", "-a",
                           "android.intent.action.VIEW", "-d",
                           "http://www.baidu.com", (char *) NULL);
                } else {
                    // Android4.2以前的版本无需指定用户
                    execlp("am", "am", "start", "-a",
                           "android.intent.action.VIEW", "-d",
                           "http://www.baidu.com", (char *) NULL);
                }
            } else {
                // 应用没有被卸载
                LOGD("app run normal");
            }
            sleep(1);
        }
    }

}

void Java_com_kkandroidstudy_ndk_JNI_uninstallListenerInotify(JNIEnv *env, jobject obj, jstring packageDir, jint sdkVersion) {
    //将传递过来的java的包名转换成C的字符串
    char *pd = jstringTostr(env, packageDir);
    //创建当前进程的克隆进程
    pid_t pid = fork();

    if (pid < 0) {
        LOGD("current create process failure");
    } else if (pid > 0) {
        // 说明克隆进程成功，而且该代码运行在父进程中
        LOGD("create process success,current parent pid = %d", pid);
    } else {
        // 说明克隆进程成功，而且代码运行在子进程中
        LOGD("create process success,current child pid = %d", pid);
        //在子进程中监视/data/data/包名这个目录
        //初始化inotify进程
        int fd = inotify_init();
        if (fd < 0) {
            LOGD("inotify_init failed !!!");
            exit(1);
        }
        //添加inotify监听器
        int wd = inotify_add_watch(fd, pd, IN_DELETE);
        if (wd < 0) {
            LOGD("inotify_add_watch failed !!!");
            exit(1);
        }
        //分配缓存，以便读取event，缓存大小=一个struct inotify_event的大小，这样一次处理一个event
        void *p_buf = malloc(sizeof(struct inotify_event));
        if (p_buf == NULL) {
            LOGD("malloc failed !!!");
            exit(1);
        }
        //开始监听
        LOGD("start observer");
        ssize_t readBytes = read(fd, p_buf, sizeof(struct inotify_event));
        //read会阻塞进程，走到这里说明收到目录被删除的事件，注销监听器
        free(p_buf);
        inotify_rm_watch(fd, IN_DELETE);

        // 应用被卸载了，通知系统打开用户反馈的网页
        LOGD("app uninstall,current sdkversion = %d", sdkVersion);
        if (sdkVersion >= 17) {
            // Android4.2系统之后支持多用户操作，所以得指定用户
            execlp("am", "am", "start", "--user", "0", "-a",
                   "android.intent.action.VIEW", "-d", "http://www.baidu.com",
                   (char *) NULL);
        } else {
            // Android4.2以前的版本无需指定用户
            execlp("am", "am", "start", "-a", "android.intent.action.VIEW",
                   "-d", "http://www.baidu.com", (char *) NULL);
        }

    }


}
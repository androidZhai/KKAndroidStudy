package com.kkandroidstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kkandroidstudy.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxJavaTestActivity extends AppCompatActivity {
    private final String TAG = "RxJavaTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);
        test8();
    }

    /**
     * 最基本的rajava使用方法(输出Hello World)
     */
    public void test1() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World");
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.d(s);
            }
        };

        //订阅观察者
        observable.subscribe(subscriber);
    }

    /**
     * 轻量版实现Hello World
     */
    public void test2() {
        Observable<String> observable = Observable.just("Hello World");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d(s);
            }
        };
        observable.subscribe(onNextAction);
    }

    /**
     * 简化版实现Hello World
     */
    public void test3() {
        Observable.just("Hello World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d(s);
            }
        });
    }

    /**
     * map操作符基本应用
     * 从Hello Word的基础上输出Hello World Dan
     */
    public void test4() {
        Observable.just("Hello World").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + " Dan";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d(s);
            }
        });

    }

    /**
     * map操作符进阶
     * 将Hello World变成Hello World Dan 然后输出Hello World Dan的hashcode值
     */
    public void test5() {
        Observable.just("Hello World").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + " Dan";
            }
        }).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String o) {
                return o.hashCode();
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer o) {
                Logger.d(Integer.toString(o));
            }
        });
    }

    /**
     * 根据输入的字符串返回一个网站的url列表
     */
    public void test6() {
        query("shiyan boy").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> urls) {
                for (String url : urls) {
                    Logger.d(url);
                }
            }
        });

    }

    /**
     * 非for循环版本 输入的字符串返回一个网站的url列表
     */
    public void test7() {
        query("shiyan test").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> urls) {
                Observable.from(urls).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Logger.d(s);
                    }
                });
            }
        });
    }

    /**
     * 精简版 输入的字符串返回一个网站的url列表
     */
    public void test8() {
        query("shiyan test").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> urls) {
                return Observable.from(urls);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d(s);
            }
        });

    }

    /**
     * 根据输入的字符串返回一个网站的url列表
     * 模拟查询函数
     */
    public Observable<List<String>> query(String str) {
        List<String> list = new ArrayList<>();
        if (str.contains("shiyan")) {
            list = Arrays.asList("www.baidu.com", "www.sina.com", "www.360.cn");
        }
        return Observable.just(list);
    }


}

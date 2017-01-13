package com.kkandroidstudy.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kkandroidstudy.R;
import com.kkandroidstudy.iterface.RetrofitService;
import com.kkandroidstudy.network.RetrofitClient;
import com.kkandroidstudy.util.HttpsUtil;
import com.orhanobut.logger.Logger;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpsActivity extends AppCompatActivity {
    private String urlTest = "https://kyfw.12306.cn/otn/";
    private InputStream certInput;
    private Certificate serverCert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https);
    }

    public void sendByHttpUrlConnection(View view) {
        try {
            certInput = new BufferedInputStream(getAssets().open("srca.cer"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    //以X.509格式获取证书
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    Certificate cert = certificateFactory.generateCertificate(certInput);
                    Log.d("cert key", ((X509Certificate) cert).getPublicKey().toString());

                    //生成一个包含服务器端证书的keystore
                    String keyStoreType = KeyStore.getDefaultType();
                    Log.d("keystore type", keyStoreType);
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(null, null);
                    keyStore.setCertificateEntry("cert", cert);

                    //用包含服务器端证书的keystore生成一个TrustManager
                    String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                    Log.d("tmfAlgorithm", tmfAlgorithm);
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
                    trustManagerFactory.init(keyStore);

                    //生成一个使用我们的TrustManger的SSLContext
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

                    URL url = new URL(urlTest);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                    Log.e("response code:", httpsURLConnection.getResponseCode() + "");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(urlTest);
    }

    public void sendByHttpUrlConnection2(View view) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                try {

                    URL url = new URL(urlTest);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    HttpsUtil.getInstance(HttpsActivity.this).setSSLSocketFactory(httpsURLConnection, "srca.cer");
                    int responseCode = httpsURLConnection.getResponseCode();
                    Log.d("responseCode:", responseCode + "");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(urlTest);

    }

    public void sendByHttpUrlConnection3(View view) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                try {
                    URL url = new URL("https://192.168.0.190:8443");
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    HttpsUtil.getInstance(HttpsActivity.this).setSSLSocketFactory(httpsURLConnection, "shiyan_server.cer");
                    int responseCode = httpsURLConnection.getResponseCode();
                    Log.d("responseCode:", responseCode + "");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(urlTest);

    }


    public void sendByOkHttp(View view) {
        //修改baseUrl
        RetrofitClient.baseUrl = "https://kyfw.12306.cn";
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<String> cookieInfo = service.listClass();
        cookieInfo.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}

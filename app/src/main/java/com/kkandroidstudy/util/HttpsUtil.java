package com.kkandroidstudy.util;

import android.content.Context;
import android.util.Log;


import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
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
import javax.net.ssl.X509TrustManager;

/**
 * Created by shiyan on 2016/12/8.
 */

public class HttpsUtil {
    private static HttpsUtil httpsUtil;
    private static Context context;
    private Certificate serverCert;

    public static HttpsUtil getInstance(Context context) {
        HttpsUtil.context = context;
        if (httpsUtil == null) {
            httpsUtil = new HttpsUtil();
        }
        return httpsUtil;
    }

    public TrustManager[] getTrustAllCerts(String cerName) {
        try {
            InputStream certInput = new BufferedInputStream(context.getAssets().open(cerName));
            //以X.509格式获取证书
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            serverCert = certificateFactory.generateCertificate(certInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    //校验服务器端证书
                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {
                        if (x509Certificates == null) {
                            throw new IllegalArgumentException("Check Server x509Certificates is null");
                        }
                        if (x509Certificates.length <= 0) {
                            throw new IllegalArgumentException("Check Server x509Certificates is empty");
                        }
                        for (X509Certificate cert : x509Certificates) {
                            //检查服务器端证书有效期
                            cert.checkValidity();
                            //检查服务器端证书签名
                            try {
                                cert.verify(serverCert.getPublicKey());
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (NoSuchProviderException e) {
                                e.printStackTrace();
                            } catch (SignatureException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
        return trustAllCerts;
    }

    public SSLContext getSSLContext(TrustManager[] trustManagers) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);
            return sslContext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                Log.d("hostname:", hostname);
                HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
                boolean result = verifier.verify(hostname, session);
//                if (hostname.equals("192.168.0.190")) {
//                    return true;
//                }
                return result;
//                return false;
            }
        };
        return hostnameVerifier;
    }

    public void setSSLSocketFactory(HttpsURLConnection httpsURLConnection, String certName) {
        try {
            httpsURLConnection.setSSLSocketFactory(getSSLContext(getTrustAllCerts(certName)).getSocketFactory());
            //服务器证书域名强制验证
            httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            httpsURLConnection.setHostnameVerifier(getHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

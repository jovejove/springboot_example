package com.panda.excel.http;

import com.alibaba.fastjson.JSON;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @author Administrator
 */
public class OKHttpClientBuilder {
    public static OkHttpClient.Builder buildOKHttpClient() {
        try {
            TrustManager[] trustAllCerts = buildTrustManagers();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            // 不作证书校验
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            return new OkHttpClient.Builder();
        }
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }


    public static void main(String[] args) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "https://www.baidu.com";
        String json = "{}";
        // 1. create request
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(new HashMap<>()))
                .post(RequestBody.create(JSON, json))
                .build();
        // 2. execute request
        Response response = OKHttpClientBuilder.buildOKHttpClient().build().newCall(request).execute();
        String s = new String(response.body().bytes());
        System.out.println(s);

    }
}
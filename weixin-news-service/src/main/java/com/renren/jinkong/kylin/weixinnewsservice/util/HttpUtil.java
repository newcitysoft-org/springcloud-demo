package com.renren.jinkong.kylin.weixinnewsservice.util;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * 网络请求工具类
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/5/29 17:29
 */
public final class HttpUtil {

    private HttpUtil() {}

    public static String postJson(String url, String json) throws IOException {
        if(json == null || json.equals("")) {
            json = "{}";
        }

        OkHttpClient okHttpClient  = new OkHttpClient();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();
        response.body().close();

        return result;
    }

    public static String doGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}

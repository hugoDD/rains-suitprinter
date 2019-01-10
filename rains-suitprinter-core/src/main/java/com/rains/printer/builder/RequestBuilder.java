package com.rains.printer.builder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestBuilder {

    public static InputStream requestStream(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 设置连接超时时间
        conn.setConnectTimeout(3000);
        InputStream is = null;

        // 正常响应时获取输入流, 在这里也就是图片对应的字节流
        if (conn.getResponseCode() == 200) {
            is = conn.getInputStream();
        }
        return is;
    }
}

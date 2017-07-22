package com.demo.jdk8;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Base64是一种用64个字符来表示任意二进制数据的方法
 *      A-Z, a-z, 0-9 , + , /
 * Java 8在java.util包下面实现了BASE64编解码API
 */
public class J5_Base64 {

    @Test
    public void test() throws UnsupportedEncodingException {
        //basic编码, 输出的内容不添加换行符，而且输出的内容由64个基本字符等组成
        String base64 = Base64.getEncoder().encodeToString("hello jdk8".getBytes("utf-8"));
        System.out.println(base64);
        //解码
        byte[] asBytes = Base64.getDecoder().decode("aGVsbG8gamRrOA==");
        System.out.println(new String(asBytes, "utf-8"));
        //URL编码, 使用"-"和"_"替换URL里面的"+"和"/"
        String urlBase64 = Base64.getUrlEncoder().encodeToString("http://www.baidu.com?id=123".getBytes("utf-8"));
        System.out.println(urlBase64);
        byte[] urlBytes = Base64.getUrlDecoder().decode(urlBase64);
        System.out.println(new String(urlBytes, "utf-8"));
        //MIME编码器会使用基本的字母数字产生BASE64输出，而且对MIME格式友好：每一行输出不超过76个字符，而且每行以“\r\n”符结束
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 10; ++t) {
            sb.append(UUID.randomUUID().toString());
        }
        String mimeEncoded = Base64.getMimeEncoder().encodeToString(sb.toString().getBytes("utf-8"));
        System.out.println(mimeEncoded);
    }

}

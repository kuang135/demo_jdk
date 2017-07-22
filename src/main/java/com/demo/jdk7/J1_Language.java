package com.demo.jdk7;

import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/20.
 */
public class J1_Language {

    /**
     *  数字下划线
     */
    @Test
    public void test1() {
        int i = 123_567;
        long l = 100_000l;
        System.out.println(i);
        System.out.println(l);
    }

    /**
     *  try-with-resources(TWR) AutoCloseable
     * */
    @Test
    public void test2() throws IOException {
        File file = new File("e:\\test\\foo.txt");
        URL url = new URL("http://www.baidu.com/");
        try (
            OutputStream out = new FileOutputStream(file);
            InputStream is = url.openStream()
        ) {
            byte[] buf = new byte[4096];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
    }

}

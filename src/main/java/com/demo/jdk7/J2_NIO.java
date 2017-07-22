package com.demo.jdk7;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/20.
 */
public class J2_NIO {

    /**
     *  Paths工具类 Path
     */
    @Test
    public void paths() throws IOException {
        Path path = Paths.get(System.getProperty("java.io.tmpdir"));
        System.out.println("path: " + path);
        System.out.println("absolute path: " + path.toAbsolutePath());
        System.out.println("real path: " + path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        System.out.println("file name: " + path.getFileName());
        System.out.println("root: " + path.getRoot());
        System.out.println("parent: " + path.getParent());

        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println("Name element " + i + " is: " + path.getName(i));
        }

        System.out.println("Subpath (1,3): " + path.subpath(1, 3));
    }

    /**
     *  遍历目录
     */
    @Test
    public void walk() throws IOException {
        //不会循环遍历
        Path dir = Paths.get("D:\\file");
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        for (Path entry : stream) {
            System.out.println(entry);
        }
        //会循环遍历
        Path path = Paths.get("e:\\test\\drag");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     *  Files工具类
     */
    @Test
    public void files() throws IOException {
        Path path = Paths.get("e:\\test");
        System.out.println("last modified time: " + Files.getLastModifiedTime(path));
        System.out.println("size: " + Files.size(path));
        System.out.println("is directory: " + Files.isDirectory(path));
        System.out.println("readable: " + Files.isReadable(path));
        System.out.println("writable: " + Files.isWritable(path));
        System.out.println("hidden: " + Files.isHidden(path));
        System.out.println("");
        Map<String, Object> attributes = Files.readAttributes(path, "*");
        Set<Map.Entry<String, Object>> entries = attributes.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * 媒体类型
     */
    @Test
    public void contentType() throws IOException {
        Path path1 = Paths.get("e:\\test\\test.doc");
        Path path2 = Paths.get("e:\\test\\test.pdf");
        Path path3 = Paths.get("e:\\test\\test.txt");
        Path path4 = Paths.get("e:\\test\\test.zip");
        System.out.println(Files.probeContentType(path1));
        System.out.println(Files.probeContentType(path2));
        System.out.println(Files.probeContentType(path3));
        System.out.println(Files.probeContentType(path4));
    }

    /**
     * 复制和移动
     */
    @Test
    public void copyAndMove() throws IOException {
        Path source = Paths.get("e:\\test\\foo.txt");
        Path target = Paths.get("f:\\test\\foo\\foo.txt");
        if (Files.exists(source)) {
            //目录必须存在
            if (Files.notExists(target.getParent())) {
                Files.createDirectories(target.getParent());
            }
            //复制
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            //移动
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * 读和写
     */
    @Test
    public void readAndWrite() throws IOException {
        Path source = Paths.get("F:\\test\\foo\\foo.txt");
        Path target = Paths.get("F:\\test\\target\\foo2.txt");
        //目录和文件必须存在
        if (Files.notExists(target)) {
            Files.createDirectories(target.getParent());
            Files.createFile(target);
        }
        //读一行，写一行
        try (
            BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8);
            BufferedWriter writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8, StandardOpenOption.WRITE)
        ) {
            String line ;
            while((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
        }
        //读取所有行
        List<String> lines = Files.readAllLines(source, StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("");
        //读取所有字节
        byte[] bytes = Files.readAllBytes(source);
        System.out.println(new String(bytes));
    }

}

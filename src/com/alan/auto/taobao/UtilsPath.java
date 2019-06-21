package com.alan.auto.taobao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UtilsPath {

    /**
     * @throws
     * @title
     * @description 取得当前类所在的文件 包括文件
     * @author HUAZAI
     * @date 2018-10-31 11:09:00
     */
    public static Path getClassFilesPath(Class clazz) {
        URL path = clazz.getResource(clazz.getName().substring(
                clazz.getName().lastIndexOf(".") + 1)
                + ".class");
        if (path == null) {
            String name = clazz.getName().replaceAll("[.]", "/");
            path = clazz.getResource("/" + name + ".class");
        }
        return Paths.get(path.getPath()).toAbsolutePath();
    }

    /**
     * @throws
     * @title
     * @description 同getClassFilesPath 解决中文编码问题
     * @author HUAZAI
     * @date 2018-10-31 11:08:37
     */

    public static String getClassFilesPathStr(Class clazz) {
        try {
            return java.net.URLDecoder.decode(getClassFilesPath(clazz).toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * @throws
     * @title
     * @description 取得当前类所在的ClassPath目录
     * @author HUAZAI
     * @date 2018-10-31 11:10:13
     */

    public static Path getClassDirPath(Class clazz) {
        Path path = getClassFilesPath(clazz);
        for (int index = path.getNameCount(); index >= 0 && StringUtils.lastIndexOf(StringUtils.lowerCase(path.toString()), ".jar!") > 0; index--) {
            path = path.subpath(0, index);
        }
        return path;
    }

    /**
     * @throws
     * @title
     * @description 同getClassDirPath 解决中文编码问题
     * @author HUAZAI
     * @date 2018-10-31 11:13:11
     */

    public static String getClassDirPathStr(Class clazz) {
        try {
            return java.net.URLDecoder.decode(getClassDirPath(clazz).toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * @throws
     * @title
     * @description 获取当前jar包所在的目录
     * @author HUAZAI
     * @date 2018-10-31 10:35:42
     */

    public static Path getJarFilePath(Class clazz) {
        //该种方式在Windows上获取的路径有问题  获取的路径  类似  /D:/asd/asd  路径前多了一个/
        // Path path = Paths.get(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());

        String str = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(str);//使用该方法将/D:/asd/asd 转换为 D:\asd\asd

        try {
            str = file.getCanonicalPath();
        } catch (IOException e) {
        }
        Path path = Paths.get(str);
        if (!path.isAbsolute()) {
            path = path.toAbsolutePath();
        }
        return path;
    }

    /**
     * @throws
     * @title
     * @description 获取jar文件路径 解决中文乱码
     * @author HUAZAI
     * @date 2018-10-31 10:37:17
     */

    public static String getJarPath(Class clazz) {
        try {
            return java.net.URLDecoder.decode(getJarFilePath(clazz).toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * @throws
     * @title
     * @description 获取jar文件所在文件夹路径 解决中文乱码
     * @author HUAZAI
     * @date 2018-10-31 10:37:17
     */

    public static String getJarDirPath(Class clazz) {
        try {
            return java.net.URLDecoder.decode(getJarFilePath(clazz).getParent().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String getJarParentDir(String path) {
        File file = new File(path);
        return file.getParent();
    }

    /**
     * @throws
     * @title
     * @description 加载配置文件 配置文件所在文件夹位置是Class的jar包所在的位置
     * @author HUAZAI
     * @date 2018-10-31 14:10:14
     */

    public static Properties reloadConfig(Class clazz) {
        Properties pro = new Properties();
        String jarDirPath = getJarDirPath(clazz);
        Path path = Paths.get(jarDirPath, "args.properties");
        try (FileInputStream in = new FileInputStream(path.toString())) {
            pro.load(in);
            return pro;
        } catch (IOException e) {
            return pro;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(getClassFilesPathStr(UtilsPath.class));
        System.out.println(getClassDirPathStr(UtilsPath.class));
        System.out.println(getJarPath(UtilsPath.class));
        System.out.println(getJarDirPath(UtilsPath.class));
        System.out.println(System.getProperty("user.dir"));//这种方式不靠谱 这是在哪里执行命令 获取的路径就是哪里
    }
}

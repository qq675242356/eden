package com.example.jvm;

import com.sun.net.ssl.internal.ssl.Provider;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @author Dongguo
 * @date 2021/8/26 0026-9:27
 * @description:
 */
public class ClassLoader2 {
    public static void main(String[] args) {
        System.out.println("------启动类加载器------");
        //获取BootStrapClassLoader能够加载的API的路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
        /*
        ------启动类加载器------
        file:/E:/software/java/jdk/jre/lib/resources.jar
        file:/E:/software/java/jdk/jre/lib/rt.jar
        file:/E:/software/java/jdk/jre/lib/sunrsasign.jar
        file:/E:/software/java/jdk/jre/lib/jsse.jar
        file:/E:/software/java/jdk/jre/lib/jce.jar
        file:/E:/software/java/jdk/jre/lib/charsets.jar
        file:/E:/software/java/jdk/jre/lib/jfr.jar
        file:/E:/software/java/jdk/jre/classes
         */
        //从上面的路径中随意选择一个类，查看使用的类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);//null    //说明使用的是BootStrapClassLoader加载
    }
}

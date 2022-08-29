package com.example.jvm;

import sun.misc.Launcher;
import sun.security.ec.ECKeyFactory;

import java.net.URL;
import java.security.interfaces.ECKey;
import java.util.Properties;

/**
 * @author Dongguo
 * @date 2021/8/26 0026-9:47
 * @description:
 */
public class ClassLoader3 {
    public static void main(String[] args) {
        System.out.println("------扩展类加载器------");
        //获取SystemClassLoader能够加载的API的路径
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }
        /*
        ------扩展类加载器------
        E:\software\java\jdk\jre\lib\ext
        C:\WINDOWS\Sun\Java\lib\ext
        */
        //从上面的路径中随意选择一个类，查看使用的类加载器
        ClassLoader classLoader = ECKeyFactory.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$ExtClassLoader@1b6d3586 //扩展类加载器
    }
}

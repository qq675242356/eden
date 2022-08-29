package com.example.jvm;

/**
 * @author Dongguo
 * @date 2021/8/26 0026-9:07
 * @description:
 */
public class ClassLoader1 {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取上层 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@1b6d3586

        //获取上层 引用类加载器  获取不到引用类加载器
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);//null

        //对于用户自定义类使用的加载器  默认使用系统类加载器加载
        ClassLoader classLoader = ClassLoader1.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //String类使用引导类加载器加载，Java核心类库都是使用引导类加载器加载
        ClassLoader StringClassLoader = String.class.getClassLoader();
        System.out.println(StringClassLoader);//null
    }
}

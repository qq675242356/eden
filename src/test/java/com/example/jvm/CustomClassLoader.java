package com.example.jvm;

import java.io.FileNotFoundException;

/**
 * @author Dongguo
 * @date 2021/8/26 0026-10:01
 * @description: 自定义用户类加载器大致流程
 */
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {

            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }

    private byte[] getClassFromCustomPath(String name) {
        //细节略
        //根据路径读取二进制流的方式将指定类读取到内存中形成字节数组
        //如果指定路径的字节码文件进行了加,则需要在此方法中进行解密操作。
        return null;
    }
    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("your class path", true, customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

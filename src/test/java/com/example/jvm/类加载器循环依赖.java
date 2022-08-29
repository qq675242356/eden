package com.example.jvm;

/**
 * @auther yangjianwu
 * @since 2022/8/8
 */
public class 类加载器循环依赖 {

    public static class LazyDemo {

        private static boolean initialized = false;

        // LazyDemo static模块执行时（类还未完全初始化），Runnable 匿名内置类随之初始化
        // 如果Runnable匿名内置类依赖了外部对象，就会导致类加载时出现循环等待，产生死锁
        static {
            print("static 模块执行！");

            // Case 1 匿名内置类：（不能运行）
            // 如果是匿名内内置类（LazyDemo$1.class）的话，它的类初始化依赖于外部类初始化
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // Runnable类依赖了LazyDemo类中的对象，产生了循环等待，导致死锁情况。
                    initialized = true;
                }
            });
            t1.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            print("main 执行！");
            System.out.println(initialized);
        }

        private static void print(Object obj) {
            System.out.printf("线程执行：[%s] - %s", Thread.currentThread().getName(), obj);
        }
    }

}

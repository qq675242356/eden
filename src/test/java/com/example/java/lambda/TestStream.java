package com.example.java.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作
 * 1.创建stream
 * 2.中间操作
 * 3.终止操作（终端操作）
 **/
public class TestStream {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18),
            new Employee(102, "李四", 28),
            new Employee(103, "王五", 38),
            new Employee(104, "赵六", 48),
            new Employee(104, "赵六", 48),
            new Employee(105, "田七", 58)
    );

    //创建stream
    @Test
    public void test1() {
        //1.可以通过Collection,系列集合提供的stream() -- 串行
        //或 paralleStream -- 并行
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream() 获取数组
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过Stream 类中的静态方法of();
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //4.1 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        //中间操作， 主要10个
        stream4.limit(10).forEach(System.out::println);

        //4.2 生成
        Stream.generate(() -> Math.random())
                .limit(5)//中间操作
                .forEach(System.out::println);//终止操作
    }

    /**
     * 中间操作
     * 筛选与切片
     * filter -- 接受lambda 从流中排除元素
     * limit -- 截断流，是其元素不超过给定数量
     * skip(n) -- 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，返回一个空流，与limit（n） 互补
     * distinct -- 筛选，通过流所生成元素的hashCode()和equals()去重元素
     * <p>
     * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，
     * 否则中间操作不会执行任何的处理，而在终止操作是一次型全部处理，称为“惰性求值”
     **/

    //内部迭代，迭代操作由Stream API完成
    @Test
    public void test2() {
        //中间操作，不会执行任何操作
        Stream<Employee> stream = emps.stream()
                .filter((x) -> x.getAge() > 35);
        //终止操作 一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test3() {
        Iterator<Employee> it = emps.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test //短路，提高效率
    public void test4() {
        emps.stream().filter((e) -> {
            System.out.println("短路");
            return e.getAge() > 20;
        }).limit(2)
                .forEach(System.out::println);
    }

    @Test // skip 跳过前n个元素
    public void test5() {
        emps.stream().filter((e) -> e.getAge() > 20)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test // distinct 去重
    public void test6() {
        emps.stream()
                .filter((e) -> e.getAge() > 20)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map -- 接收Lambda 将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用
     * 到每个元素上
     * flatMap -- 接收一个参数作为参数，将流中的每个值都转换成另一个流，然后把所有流连接成一个流
     **/
    @Test
    public void test7() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream()
                .map((e) -> e.toUpperCase())
                .forEach(System.out::println);

        System.out.println("--------------------------------");

        emps.stream() // 流中的每个元素都用的函数map映射
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test8() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        Stream<CharSequence> sm = list.stream()
                .flatMap(TestStream::filterCharacter);
        sm.forEach(System.out::println);
    }

    public static Stream<CharSequence> filterCharacter(String str) {
        List<CharSequence> list = new ArrayList();

        list.add(str);

        return list.stream();
    }

    /**
     * 排序
     * sorted() -- 自然排序 Comparable
     * sorted(Comparator com) -- 定制排序
     **/
    @Test
    public void test9() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        //自然排序
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------------------");

        emps.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    @Test
    public void test10() {
        Iterator<Double> i = Stream.generate(() -> 1d).iterator();
        while (i.hasNext()) System.out.println(i.next());
    }

    @Test
    public void test11() {
        emps.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.counting())).forEach((k, v) -> {
            System.out.println(k + " 数量:" + v);
        });

        emps.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.summingInt(Employee::getAge))).forEach((k, v) -> {
            System.out.println(k + " 的总年龄数:" + v);
        });
        emps.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.mapping(Function.identity(), Collectors.maxBy(Comparator.comparing(Employee::getAge))))).forEach((k, v) -> {
            System.out.println(k + " 最大年龄数:" + v);
        });
    }


}

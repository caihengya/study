package stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流 常用方法总结
 * <p>
 * 中间聚合操作：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered。
 * <p>
 * 最终输出操作：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator。
 * <p>
 * 短路操作：anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit。
 * <p>
 * <p>
 * 1.	concat方法    合并两个Stream。
 * 2.	distinct    元素去重
 * 3.	filter	 根据指定条件进行筛选过滤，留下满足条件的元素。
 * 4.	map     将Stream中的元素进行映射转换，比如将“a”转为“A” (toUpperCase 大写)，期间生产了新的Stream。
 * 5.	flatMap  将流中的每一个元素映射为一个流，再把每一个流连接成为一个流。期间原有的Stream的元素会被逐一替换。
 * 6.	peek     生成一个相同的Stream，并提供一个消费函数，当新Stream中的元素被消费（执行操作）时，该消费函数会在此之前先执行。
 * 7.	skip	 跳过前N个元素，取剩余元素，如果没有则为空Stream。
 * 8.	sorted  排序
 * <p>	     sorted() 升序
 * <p>	     sorted(Comparator.reverseOrder()) 降序
 * 9.	limit	 限制返回前N个元素，与SQL中的limit相似。
 * 10.	collect	 收集方法，实现了很多归约操作，比如将流转换成集合和聚合元素等。
 * 11.	count	 返回Stream中元素个数。
 * 12.	max      根据指定的比较器（Comparator），返回Stream中最大元素的Optional对象，Optional中的value便是最大值。
 * 13.	min     与max操作相同，功能相反，取最小值。
 * 14.	reduce  reduce可实现根据指定的规则从Stream中生成一个值，比如之前提到的count，max和min方法是因为常用而被纳入标准库中。
 * 15.	allMatch    判断Stream中的所有元素是否满足指定条件。全部满足返回true，否则返回false。
 * 16.	anyMatch    判断Stream中的元素至少有一个满足指定条件。如果至少有一个满足则返回true，否则返回false。
 * 17.findAny       获得其中一个元素（使用stream()时找到的是第一个元素；使用parallelStream()并行时找到的是其中一个元素）。
 * 18.	findFirst   获得第一个元素。
 * 19.noneMatch     判断Stream中是否所有元素都不满足指定条件。都不满足则返回true，否则false。
 * <p>
 * <p>
 * <p>
 * <p>
 * Collectors类的静态工厂方法
 * 工厂方法      返回类型               用 于
 * toList       List<T>         把流中所有项目收集到一个 List
 * 使用示例：List<Dish> dishes = menuStream.collect(toList());
 * <p>
 * toSet        Set<T>      把流中所有项目收集到一个 Set，删除重复项
 * 使用示例：Set<Dish> dishes = menuStream.collect(toSet());
 * <p>
 * toCollection         Collection<T>       把流中所有项目收集到给定的供应源创建的集合
 * 使用示例：Collection<Dish> dishes = menuStream.collect(toCollection(),ArrayList::new);
 * counting         Long        计算流中元素的个数
 * 使用示例：long howManyDishes = menuStream.collect(counting());
 * <p>
 * summingInt       Integer         对流中项目的一个整数属性求和
 * 使用示例：int totalCalories =menuStream.collect(summingInt(Dish::getCalories));
 * <p>
 * averagingInt     Double      计算流中项目 Integer 属性的平均值
 * 使用示例：double avgCalories =menuStream.collect(averagingInt(Dish::getCalories));
 * <p>
 * summarizingInt   IntSummaryStatistics      收集关于流中项目 Integer 属性的统计值，例如最大、最小、总和与平均值
 * 使用示例：IntSummaryStatistics menuStatistics = menuStream.collect(summarizingInt(Dish::getCalories));
 * <p>
 * joining  String      连接对流中每个项目调用 toString 方法所生成的字符串
 * 使用示例：String shortMenu =menuStream.map(Dish::getName).collect(joining(", "));
 * <p>
 * maxBy        Optional<T>         一个包裹了流中按照给定比较器选出的最大元素的 Optional，或如果流为空则为 Optional.empty()
 * 使用示例：Optional<Dish> fattest =menuStream.collect(maxBy(comparingInt(Dish::getCalories)));
 * <p>
 * minBy         Optional<T>        一个包裹了流中按照给定比较器选出的最小元素的 Optional，或如果流为空则为 Optional.empty()
 * 使用示例：Optional<Dish> lightest =menuStream.collect(minBy(comparingInt(Dish::getCalories)));
 * <p>
 * reducing         归约操作产生的类型       从一个作为累加器的初始值开始，利用 BinaryOperator 与流中的元素逐个结合，从而将流归约为单个值
 * 使用示例：int totalCalories =menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
 * <p>
 * collectingAndThen        转换函数返回的类型       包裹另一个收集器，对其结果应用转换函数
 * 使用示例：int howManyDishes =menuStream.collect(collectingAndThen(toList(), List::size));
 * <p>
 * groupingBy       Map<K, List<T>>     根据项目的一个属性的值对流中的项目作问组，并将属性值作为结果 Map 的键
 * 使用示例：Map<Dish.Type,List<Dish>> dishesByType = menuStream.collect(groupingBy(Dish::getType));
 * <p>
 * partitioningBy        Map<Boolean,List<T>>       根据对流中每个项目应用谓词的结果来对项目进行分区
 * 使用示例：Map<Boolean,List<Dish>> vegetarianDishes = menuStream.collect(partitioningBy(Dish::isVegetarian));
 */
public class StreamTest {


    /**
     * Demo class
     * 1. concat方法
     * 合并两个Stream。
     * 如果输入Stream有序，则新Stream有序；
     * 如果其中一个Stream为并行，则新Stream为并行；如果关闭新Stream，原Stream都将执行关闭。
     *
     * @author caiheng
     * @date 2020/04/07
     */
    @Test
    public void concat() {
        Stream.concat(Stream.of("欢迎", "关注"), Stream.of("程序新视界")).forEach(System.out::println);
    }

    /**
     * Stream中元素去重。
     */
    @Test
    public void distinct() {
        Stream.of(1, 1, 1, 2).distinct().forEach(System.out::println);
    }

    /**
     * 根据指定条件进行筛选过滤，留下满足条件的元素。
     */
    @Test
    public void filter() {
        Stream.of(1, 2, 3, 4, 5).filter(i -> i >= 3).forEach(System.out::println);
    }

    /**
     * 将Stream中的元素进行映射转换，比如将“a”转为“A” (toUpperCase 大写)，期间生产了新的Stream。
     * 同时为了提升效率，官方也提供了封装好的方法：mapToDouble，mapToInt，mapToLong。
     */
    @Test
    public void map() {
        Stream.of("a", "b", "c").map(item -> item.toUpperCase()).forEach(System.out::println);
        Stream.of("a", "b", "c").map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 将流中的每一个元素映射为一个流，再把每一个流连接成为一个流。期间原有的Stream的元素会被逐一替换。
     * 官方提供了三种原始类型的变种方法：flatMapToInt，flatMapToLong和flatMapToDouble。
     */
    @Test
    public void flatMap() {
        Stream.of(1, 2, 3).flatMap(i -> Stream.of(i * 10)).forEach(System.out::println);
    }

    /**
     * 生成一个相同的Stream，并提供一个消费函数，当新Stream中的元素被消费（执行操作）时，该消费函数会在此之前先执行。
     */
    @Test
    public void peek() {
        Stream.of(1, 2).peek(i -> System.out.println("peekCall:" + i)).forEach(System.out::println);
    }


    /**
     * 跳过前N个元素，取剩余元素，如果没有则为空Stream。
     */
    @Test
    public void skip() {
        Stream.of(1, 2, 3).skip(1).forEach(System.out::println);
    }

    /**
     * 对Stream元素进行排序，可采用默认的sorted()方法进行排序，也可通过sorted(Comparator)方法自定义比较器来进行排序，
     * 前者默认调用equals方法来进行比较，
     * sorted() 升序
     * sorted(Comparator.reverseOrder()) 降序
     */
    @Test
    public void sorted() {
        //默认升序
        Stream.of(1, 3, 2).sorted().forEach(System.out::println);
        System.out.println("降序");
        Stream.of(1, 3, 2).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }


    /**
     * 限制返回前N个元素，与SQL中的limit相似。
     */
    @Test
    public void limit() {
        Stream.of(1, 2, 3).limit(2).forEach(System.out::println);
    }

    /**
     * 收集方法，实现了很多归约操作，比如将流转换成集合和聚合元素等。
     */
    @Test
    public void collect() {
        List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
        Set<Integer> collect1 = Stream.of(4, 5, 6).collect(Collectors.toSet());
        collect1.stream().forEach(System.out::println);
    }


    /**
     * 返回Stream中元素个数。
     */
    @Test
    public void count() {
        long count = Stream.of(1, 2, 3, 4).count();
        System.out.println(count);
    }


    /**
     * 根据指定的比较器（Comparator），返回Stream中最大元素的Optional对象，Optional中的value便是最大值。
     * <p>
     * Optional可以代表一个值或不存在，主要是为了规避返回值为null，而抛出NullPointerException的问题，也是由Java8引入的。
     * 但当调用其get()方法时，如果当前值不存在则会抛出异常。
     */
    @Test
    public void max() {
        Optional<Integer> max = Stream.of(1, 2, 3).max(Comparator.comparingInt(o -> o));
        System.out.println("max:" + max.get());
        //ASCII码表大小比较
        Optional<String> max1 = Stream.of("a", "b", "c", "A").max(Comparator.comparing(o -> o));
        System.out.println("max:" + max1.get());
    }

    /**
     * 与max操作相同，功能相反，取最小值。
     */
    @Test
    public void min() {
        Optional<Integer> max = Stream.of(1, 2, 3).min(Comparator.comparingInt(o -> o));
        System.out.println("min:" + max.get());
        //ASCII码表大小比较
        Optional<String> max1 = Stream.of("a", "b", "c", "?", "@").min(Comparator.comparing(o -> o));
        System.out.println("min:" + max1.get());
    }

    /**
     * reduce可实现根据指定的规则从Stream中生成一个值，比如之前提到的count，max和min方法是因为常用而被纳入标准库中。
     * 实际上，这些方法都是reduce的操作。
     */
    @Test
    public void reduce() {
        Stream.of(1, 2, 3).reduce(Integer::sum);
        Integer reduce = Stream.of(1, 2, 3, 5).reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
        //以上两个方法都是对结果进行求和，不同的是第一个方法调用的是reduce的reduce((T, T) -> T)方法，

        // 而第二个调用的是reduce(T, (T, T) -> T)。其中第二个方法的第一个参数表示初始值
        Integer reduce1 = Stream.of(1, 2, 3, 5).reduce(3, (a, b) -> a + b);
        System.out.println(reduce1);

    }

    /**
     * 判断Stream中的所有元素是否满足指定条件。全部满足返回true，否则返回false。
     */
    @Test
    public void allMatch() {
        boolean result = Stream.of(1, 2, 3).allMatch(i -> i > 0);
        boolean result1 = Stream.of(1, 2, 3).allMatch(i -> i > 3);
        System.out.println(result);
        System.out.println(result1);

    }

    /**
     * 判断Stream中的元素至少有一个满足指定条件。如果至少有一个满足则返回true，否则返回false。
     */
    @Test
    public void anyMatch() {
        boolean anyResult = Stream.of(1, 2, 3).anyMatch(i -> i > 2);
        boolean anyResult1 = Stream.of(1, 2, 3).anyMatch(i -> i > 4);
        System.out.println(anyResult);
        System.out.println(anyResult1);

    }


    /**
     * 获得其中一个元素（使用stream()时找到的是第一个元素；使用parallelStream()并行时找到的是其中一个元素）。
     * 如果Stream为空，则返回一个为空的Optional。
     */
    @Test
    public void findAny() {
        Optional<String> any = Stream.of("A", "B", "C").findAny();
        System.out.println(any.get());

    }

    /**
     * 获得第一个元素。
     * 如果Stream为空，则返回一个为空的Optional。
     */
    @Test
    public void findFirst() {
        Optional<String> any = Stream.of("A", "B", "C").findFirst();
        System.out.println(any.get());

    }

    /**
     * 判断Stream中是否所有元素都不满足指定条件。都不满足则返回true，否则false。
     */
    @Test
    public void noneMatch() {
        boolean noneMatch = Stream.of(1, 2, 3).noneMatch(i -> i > 5);
        System.out.println(noneMatch);

        boolean noneMatch1 = Stream.of(1, 2, 3).noneMatch(i -> i > 2);
        System.out.println(noneMatch1);

    }

    /**
     * Optional是Java8新加入的一个容器，这个容器只存1个或0个元素
     * <p>
     * isPresent()
     * 判断容器中是否有值。
     * ifPresent(Consume lambda)
     * 容器若不为空则执行括号中的Lambda表达式。
     * T get()
     * 获取容器中的元素，若容器为空则抛出NoSuchElement异常。
     * T orElse(T other)
     * 获取容器中的元素，若容器为空则返回括号中的默认值。
     */
    @Test
    public void Optional() {
        Optional<String> any = Stream.of("A", "b", "c").findFirst();
        boolean present = any.isPresent();
        System.out.println("容器内是否存在元素   " + present);

        String s = any.get();
        System.out.println("容器内存在的唯一一个元素  " + s);

        String s1 = any.orElse("1");
        System.out.println("容器内存在的唯一一个元素不存在输出默认值  " + s1);
    }

    /**
     * list 集合拼接
     */
    @Test
    public void splice() {
        List<String> list = Arrays.asList("张三", "李四", "王五", "赵六", "孙七", "任八");

        String names = list.stream().collect(Collectors.joining(","));
        System.out.println(names);
    }

    /**
     * //单元信息已同步过，直接取出使用unit_id
     * List<CommunityBuildingUnit> unitList = communityBuildingUnitService.selectByMap(null);
     * </p>
     * //单元id
     * 对象list取id  转list
     * List<String> unitIdList = unitList.stream().map(CommunityBuildingUnit::getBuildingId).collect(Collectors.toList());
     * </p>
     * 对象集合 取id为k 对象为v 转 map
     * //单元id 单元对象
     * Map<String, CommunityBuildingUnit> unitMap = unitList.stream().collect(Collectors.toMap(item -> item.getBuildingUnitId(), item -> item, (oldVal, currVal) -> oldVal));
     */


    /**
     * 两个集合 取出两个集合中不相同的部分
     */
    @Test
    public void removeRepeat() {

        //集合一
        List<String> _first = new ArrayList<String>();
        _first.add("jim");
        _first.add("tom");
        _first.add("jack");
        //集合二
        List<String> _second = new ArrayList<String>();
        _second.add("jack");
        _second.add("happy");
        _second.add("sun");
        _second.add("good");

        Collection exists = new ArrayList<String>(_second);

        exists.removeAll(_first);
        System.out.println("_second中不存在于_set中的：" + exists);


        Collection notexists = new ArrayList<String>(_second);
        notexists.removeAll(exists);
        System.out.println("_second中存在于_set中的：" + notexists);

        HashMap<String, String> map = new HashMap<>();
        map.put("1","1");
    }

}

package lambad;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambadTest1 {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("babbbaaaaa");
        names.add("acccbbbaaaaa");
        names.add("aabbbaaaaa");
        pickName(names, "a");
    }

    /**
     * 打印出集合中第一个以某字母开头的元素时
     * 
     * @param names
     * @param startingLetter
     */
    @Test
    public static void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
        System.out
            .println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
        /**
         * 在调用filter后，调用了findFirst方法，这个方法返回的对象类型时Optional。关于这个Optional， 可以将它理解成一个可能存在，也可能不存在的结果。这样的话，就可以避免对返回结果进行空检查了。
         * 对于结果是否真的存在，可以使用isPresent()方法进行判断，而get()方法用于尝试对结果的获取。 当结果不存在时，我们也可以使用orElse()来指定一个替代结果
         */
    }

    /**
     * Lambda表达式的重用
     */
    public void test3() {

        /**
         * 当需要对不止一个集合进行操作时：
         */
        List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        List<String> comrades = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        List<String> editors = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        /**
         * 检测的不是以N开头
         */
        final long countFriendsStartN = friends.stream().filter(name -> name.startsWith("N")).count();
        final long countComradesStartN = comrades.stream().filter(name -> name.startsWith("N")).count();
        final long countEditorsStartN = editors.stream().filter(name -> name.startsWith("N")).count();

        /**
         * 使用lambda检测以N开头的
         */
        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final long countFriendsStartN1 = friends.stream().filter(startsWithN).count();
        final long countComradesStartN1 = comrades.stream().filter(startsWithN).count();
        final long countEditorsStartN1 = editors.stream().filter(startsWithN).count();

        /**
         * 使用lambda检测以N B 开头的
         */
        final Predicate<String> startsWithN2 = name -> name.startsWith("N");
        final Predicate<String> startsWithB2 = name -> name.startsWith("B");
        final long countFriendsStartN2 = friends.stream().filter(startsWithN2).count();
        final long countFriendsStartB2 = friends.stream().filter(startsWithB2).count();
        // lambda表达式无法重用 每次检测某个字母开头都需要重新定义lambda表达式

        /**
         * 抽象 解决多个字母开头检测的问题
         */
        final long countFriendsStartN3 = friends.stream().filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB3 = friends.stream().filter(checkIfStartsWith("B")).count();

        final Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
            Predicate<String> checkStartsWith = (String name) -> name.startsWith(letter);
            return checkStartsWith;
        };
        // 初次简化
        final Function<String, Predicate<String>> startsWithLetter1 =
            (String letter) -> (String name) -> name.startsWith(letter);

        // 二次简化
        final Function<String, Predicate<String>> startsWithLetter2 = letter -> name -> name.startsWith(letter);

    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    @Test
    public void test2() {

        /**
         * 一般方法实现对集合的遍历
         */
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }

        /**
         * 使用方法的引用遍历 stream流的方式遍历
         */
        friends.stream()
            // 转换为大写
            // .map(String::toUpperCase)
            .forEach(name -> System.out.println(name));

        /**
         * 一般方法寻找元素
         */
        final List<String> startsWithN = new ArrayList<String>();
        for (String name : friends) {
            if (name.startsWith("N")) {
                startsWithN.add(name);
            }
        }
        System.out.println("一般方法寻找元素");
        startsWithN.stream().forEach(name -> System.out.println(name));

        /**
         * 使用stream流的方式的方式寻找元素
         */
        System.out.println("使用stream流的方式的方式寻找元素");
        friends.stream().filter(name -> name.startsWith("N")).forEach(System.out::println);
        // .collect(Collectors.toList());

    }

    /**
     * 假设对于20元以上的商品，进行9折处理，最后得到这些商品的折后价格
     */
    public void test1() {
        final List<BigDecimal> prices =
            Arrays.asList(new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"), new BigDecimal("20"),
                new BigDecimal("15"), new BigDecimal("18"), new BigDecimal("45"), new BigDecimal("12"));

        BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            if (price.compareTo(BigDecimal.valueOf(20)) > 0) {
                totalOfDiscountedPrices = totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
            }
        }
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);

        /**
         * 使用声明式的代码 使用lambda表达式
         */
        final BigDecimal totalOfDiscountedPrices1 =
            prices.stream().filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.9))).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices1);

    }

}

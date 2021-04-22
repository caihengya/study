package optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * java8 Optional类
 * 空指针处理
 * <p>
 * 1.    empty方法(私有)
 *   没有入参,直接new出一个Optional实例返回.
 * 2.    of方法
 *   接受一个传入类型构造一个包含传入值的Optional实例返回,该方法并没有对入参做非空判定,如果传入的参数是null的话,
 * 直接就会抛出异常,可用来阻挡null值的传入.
 * 3.    ofNullable方法
 *   看名字就知道,在传入参数的时候,对入参做了非空判定,如果传入的参数是null的话,就会new出来一个新的Optional实例.
 * 如果不是null,则构造一个包含传入值的Optional实例返回.下面讲解其他方法.
 * 4.    isPresent方法
 *   没有入参,返回一个布尔值(boolean).判断调用方法的对象是否为空,为空则返回false,非空则返回true.如同object != null的返回结果.
 * 5.    get方法
 *   如果Optional中有值,则返回该值,如果没有,则抛出NoSuchElementException的异常.
 * 6.    ifPresent方法
 *   如果Optional中有值,则进行下面的操作,如果为空,则什么也不做.
 * 7.    orElse方法
 *   接受一个传入类型,如果Optional中有值,则返回该值,如果为空,则返回默认值
 * 8.    orElseGet方法
 *   接受一个Supplier类型的数据,如果Optional中有值,则返回该值,如果为空,则返回一个Supllier生成的一个数据.
 * 9.    orElseThrow方法
 *   同第8个方法,只是在为空的时候,抛出一个由Supplier生成的指定异常.
 * 10.    filter方法
 *   顾名思义,这是一个过滤方法.传入一个Predicate类型的参数,如果满足Predicate条件,则返回该Optional的值,反之则返回一个空的Optional对象.
 * 11.    map方法
 *   如果Optional的值存在,则进行传入的Function接口的方法,并返回其方法结束后的值,并封装成一个Optional的对象,否则返回一个空的Optional对象.
 * 12.    flatMap方法
 *   如同第11方法,但是其返回值必须是一个Optianal类型的对象.
 * <p>
 * <p>
 * isPresent    如果值存在返回true，否则返回false。
 * get          如果Optional有值则将其返回，否则抛出NoSuchElementException。
 * ifPresent    如果Optional实例有值则为其调用consumer，否则不做处理
 * orElse       如果有值则将其返回，否则返回指定的其它值。
 * orElseGet    orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，
 * orElseGet方法可以接受Supplier接口的实现用来生成默认值
 * orElseThrow   如果有值则将其返回，否则抛出supplier接口创建的异常
 */
public class OptionalTest {


    @Test
    public void commonlyUsed() {

        //创建Optional实例，也可以通过方法返回值得到。
        Optional<String> name = Optional.of("caiheng");

        //创建没有值的Optional实例，例如值为'null'
        Optional empty = Optional.ofNullable(null);

        //isPresent方法用来检查Optional实例是否有值。
        if (name.isPresent()) {
            //调用get()返回Optional值。
            System.out.println(name.get());
        }


        try {
            //在Optional实例上调用get()抛出NoSuchElementException。
            System.out.println(empty.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ifPresent方法接受lambda表达式参数。
        //如果Optional值不为空，lambda表达式会处理并在其上执行操作。
        name.ifPresent(val -> System.out.println("Optional对象的长度" + val.length()));

        //如果有值orElse方法会返回Optional实例，否则返回传入的错误信息。
        System.out.println(empty.orElse("There is no value present!"));
        System.out.println(name.orElse("There is some value!"));

        //orElseGet与orElse类似，区别在于传入的默认值。
        //orElseGet接受lambda表达式生成默认值。
        System.out.println(empty.orElseGet(() -> "Default Value"));
        System.out.println(name.orElseGet(() -> "Default Value"));

        try {
            //orElseThrow与orElse方法类似，区别在于返回值。
            //orElseThrow抛出由传入的lambda表达式/方法生成异常。
            empty.orElseThrow(Exception::new);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //map方法通过传入的lambda表达式修改Optonal实例默认值。
        //lambda表达式返回值会包装为Optional实例。
        Optional<String> upperName = name.map(s -> s.toUpperCase());
        System.out.println(upperName.orElse("对象为空"));

        //flatMap与map（Funtion）非常相似，区别在于lambda表达式的返回值。
        //map方法的lambda表达式返回值可以是任何类型，但是返回值会包装成Optional实例。
        //但是flatMap方法的lambda返回值总是Optional类型。
        Optional<String> upperName1 = name.flatMap(s -> Optional.of(s.toLowerCase()));
        System.out.println(upperName1.orElse("对象名称为空"));

        //filter方法检查Optiona值是否满足给定条件。
        //如果满足返回Optional实例值，否则返回空Optional。
        Optional<String> longName = name.filter(s -> s.length() > 10);
        System.out.println(longName.orElse("姓名大于5个字符的为空"));

        Optional<String> anotherName = Optional.of("Sana");
        Optional<String> shortName = anotherName.filter(s -> s.length() < 6);
        System.out.println(shortName.orElse("姓名小于6个字符的为空"));
    }


    @Test
    public void test1() {

        //一般方式获取字符串的长度
        String s = "1234";
        if (null == s) {
            System.out.println(0);
        } else {
            System.out.println(s.length());
        }
        System.out.println();
        //采用Optional类
        System.out.println(Optional.ofNullable(s).map(String::length).orElse(0));
    }

    /**
     * 创建空对象
     */
    @Test
    public void test2() {
        //创建空对象
        Optional<String> empty = Optional.empty();
        System.out.println(empty.toString());

        //创建非空对象
        //Optional<String> empty = Optional.of(null);// 当str为null的时候，将抛出NullPointException
        Optional<String> optional = Optional.of("11");
        System.out.println(optional.toString());


        Optional<String> optional1 = Optional.ofNullable(null);// 如果str是null，则创建一个空对象
        System.out.println(optional1);
        Optional<String> optional2 = Optional.ofNullable("2");
        System.out.println(optional2);

    }


    /**
     * 流式处理:映射 map与flatMap
     */
    @Test
    public void test3() {
        //映射是将输入转换成另外一种形式的输出的操作，
        // 比如前面例子中，我们输入字符串，而输出的是字符串的长度，这就是一种隐射，
        // 我们利用方法map()得以实现。假设我们希望获得一个人的姓名，那么我们可以如下实现：
        User user = new User("ch", 18);
        String name = Optional.ofNullable(user).map(User::getName).orElse("没有名字");
        System.out.println(name);

        //这样当入参user不为空的时候则返回其name，否则返回no name
        // 如我我们希望通过上面方式得到phone或email，利用上面的方式则行不通了，
        // 因为map之后返回的是Optional，我们把这种称为Optional嵌套，我们必须在map一次才能拿到我们想要的结果：

        //map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
        //为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
        List<String> list = Optional.of(user).map(User::getName).map(s -> s.toUpperCase()).map(Arrays::asList).orElse(new ArrayList<>());
        System.out.println(list);
        boolean present = Optional.of(user).isPresent();
        System.out.println(present);
        User user1 = Optional.of(user).get();
        System.out.println(user1);
        User ch1 = Optional.of(user).orElse(new User("ch1", 20));
        System.out.println(ch1);

    }


    class User {

        /**
         * 用户编号
         */
        private long id;

        private String name;

        private int age;

        private Optional<Long> phone;

        private Optional<String> email;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Optional<Long> getPhone() {
            return phone;
        }

        public void setPhone(Optional<Long> phone) {
            this.phone = phone;
        }

        public Optional<String> getEmail() {
            return email;
        }

        public void setEmail(Optional<String> email) {
            this.email = email;
        }
    }


}

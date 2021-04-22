package lambad;

/**
 * Lambad表达式 解析
 * <p>
 * JDK1.8
 * 格式:标准格式三部分组成
 * 1、一个参数
 * 2、一个箭头
 * 3、一段代码
 * (参数类型)->{重写代码语句}
 * 1、参数，接口中抽象方法的参数列表,没有参数就空着，
 * 2、箭头就是参数传递给方法体
 * 3、大括号里面是重写接口的抽象方法体；
 * <p>
 * ->左边表达式，相当于重写方法里面的参数，有三种情况：
 * 重写的方法没有参数时，用（）表示；
 * 有一个参数时，表示方式有下面三种：
 * v ：变量名；可以是任意自定义的，当然最好和类型、具体参数意义相关
 * <p>     （v）：括号包裹变量名；
 * <p>     （View v）：括号里面数据类型加上变量名
 * 重写的方法里面有多个参数，表示方式：
 * <p>     （dir, filename）：括号包裹变量名，变量名可任意自定义，各个变量之间有逗号隔开
 * <p>     （File dir, String filename）：在上面的基础上给变量加上数据类型
 * <p>
 * -> 右边表达式，相当于自己重写方法里面的具体内容，有两种情况：
 * 重写的方法表达式只有一句话时，分两种情况：
 * 返回值为void：
 * <p>     Log.i(TAG, “onCreate: “)：直接写上这句话，并省略分号；
 * <p>     {Log.i(TAG, “onCreate: “);}：用大括号包裹，并且有语句结束符号分号
 * 返回值为非void：
 * <p>     filename.endsWith(“.png”)：直接写上这句话，并省略分号；
 * <p>     {return filename.endsWith(“.png”);}：用大括号包裹，并且有语句结束符号分号，语句前面必须加上return关键字；
 * 重写的方法    表示式有多句话时：示例代码3
 * <p>     {Log.i(TAG, “onCreate: “);return filename.endsWith(“.png”);}：大括号包裹多条语句，每句话都必须有结束符号分号；
 * <p>     如果返回值类型为非void，必须有return结束语句；如果返回值为void，可以省略return 语句；
 * PS: Lambde表达式只支持重写一个方法的, 如果你重写的方法有多个, 不好意思, 不支持
 */
public class LambadTest {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + "   主线程");

        //使用传统方式实现多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "   传统方式创建的线程");
            }
        }).start();

        //使用lambad表达式创建线程
        new Thread(() ->
                System.out.println(Thread.currentThread().getName() + "   lambad表达式创建线程")
        ).start();

        //使用基本方法实现厨子接口
        LambadTest.incokeCook(new Cook() {
            @Override
            public void makefood() {
                System.out.println("使用基本方法重写方法");
            }
        });

        //使用Lambad表达式重写方法
        LambadTest.incokeCook(() -> System.out.println("使用Lambad表达式重写方法"));
    }


    /**
     * 定义接口
     */
    private interface Cook {
        abstract void makefood();
    }

    /**
     * 实现接口
     *
     * @param cook
     */
    public static void incokeCook(Cook cook) {
        cook.makefood();
    }


}

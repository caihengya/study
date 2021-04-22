/**
 * @program: test
 * @description: 匿名类谜题
 * @author: Mr.ch
 * @create: 2020-06-26
 **/
public class MeaningOfThis {

    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    /**
     * 因为this指的是包含它的Runnable，而不是外面的类MeaningOfThis。
     * @param args
     */
    public static void main(String... args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}


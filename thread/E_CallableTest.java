package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口c创建线程
 */
public class E_CallableTest {

    /**
     * 实现Callable接口c创建线程
     * <p>
     * 与使用Runnable相比， Callable功能更强大些
     * 相比run()方法，可以有返回值
     * 方法可以抛出异常
     * 支持泛型的返回值
     * 需要借助FutureTask类，比如获取返回结果
     * <p>
     * 计算1-100所有偶数的和
     */
    static class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                if (i % 2 == 0) {
                    //System.out.println(i);
                    sum = sum + i;
                }
            }
            return sum;//自动装箱
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThread callableThread = new CallableThread();
        FutureTask futureTask = new FutureTask(callableThread);
        Thread thread = new Thread(futureTask);
        thread.start();

        //get方法的返回值即为FutureTask构造器参数Callable实现类对象重写的call方法的返回值。
        //get方法只是为了返回call方法的返回值。
        //get方法自动调用newThread类对象的call()方法
        Object o = futureTask.get();
        System.out.println(o);


    }


}

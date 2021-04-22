package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池的方式实现多线程
 * <p>
 * 1.提供指定实数量的线程池
 * 2.执行指定的线程的操作，需要提供实现Runnable接口或Callable接口的实现类对象
 * 3.关闭线程池
 * <p>
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 * <p>  corePoolSize：核心池的大小
 * <p>  maximumPoolSize：最大线程数
 * <p>  keepAliveTime：线程没有任务时最多保持多长时间后会终止
 */
public class F_ThreadPool {


    public static void main(String[] args) {

        /**
         * public ThreadPoolExecutor(int corePoolSize, //线程池核心池大小
         *         int maximumPoolSize,//线程池最大线程数量
         *         long keepAliveTime,//当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
         *         TimeUnit unit,//上一个参数的单位
         *         BlockingQueue<Runnable> workQueue,//用来储存等待执行任务的队列。
         *         ThreadFactory threadFactory //线程工厂
         *                               ) {
         *         ....
         *         }
         */
        //创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        NewThread5 newThread5 = new NewThread5();
        NewThread6 newThread6 = new NewThread6();

        //执行偶数的线程
        poolExecutor.execute(newThread5);
        //执行奇数的线程
        poolExecutor.execute(newThread6);

        //关闭线程
        poolExecutor.shutdown();

    }


    /**
     * 打印1-100的偶数的线程
     */
    private static class NewThread5 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "偶数：" + i);
                }
            }
        }
    }

    /**
     * 打印1-100的奇数的线程
     */
    private static class NewThread6 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "奇数：" + i);
                }
            }
        }
    }

}

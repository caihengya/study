package thread;

/**
 * 比如有两个线程A和B，两个线程都在执行，执行过程中发现，A线程要用到B线程的结果，然后再继续执行，
 * 这时候，就需要把并行改成串行，相当于把B线程插入到了A线程中，然后再执行A线程，
 * 这时候就需要线程B调用join（）方法，进行插入操作；
 */
public class D_joinTest {

    /**
     * 假如我要求1到10000000之间所有的质数
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // f1();
        // 把单线程改成多个子线程
        f5();
    }

    /**
     * 在子线程还没执行完的时候，main主线程就开始调用了输出语句，获取结果了，所以肯定是错误的，
     * 这时候就要用到join方法，main主线程等待子线程执行完再执行，把子线程调用join，插入到main线程
     *
     * @throws Exception
     */
    private static void f1() throws Exception {

        System.out.println("单线程测试质数个数：");

        // 单线程测试
        long s = System.currentTimeMillis();
        JoinThread thread = new JoinThread(1, 10000000);
        thread.start();

        // 修改代码 添加join 当前线程等待被调用的线程结束；
        thread.join();

        int sum = thread.count;

        s = System.currentTimeMillis() - s;
        System.out.println("总共" + sum + "个质数，耗时：" + s);

    }

    // 定义子线程
    static class JoinThread extends Thread {
        int start;
        int end;
        int count;

        public JoinThread(int start, int end) {
            this.start = start;
            this.end = end;
            if (start <= 2) {
                count = 1;
                start = 3;
            }
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                if (isZhishu(i)) {
                    count++;
                }
            }
        }

        private boolean isZhishu(int i) {
            double max = 1 + Math.sqrt(i);
            for (int j = 2; j < max; j++) {
                if (i % j == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 把单线程改成多个子线程
     *
     * @throws InterruptedException
     */
    private static void f5() throws InterruptedException {

        System.out.println("5个线程测试质数个数：");

        // 单线程测试
        long s = System.currentTimeMillis();

        JoinThread[] threads = new JoinThread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new JoinThread(10000000 / 5 * i, 10000000 / 5 * (i + 1));
            threads[i].start();
            // threads[i].join();//千万不要在这里进行join，否则会变成多个子线程串行，和单线程无区别了

        }
        for (JoinThread joinThread : threads) {
            joinThread.join();
        }

        int sum = 0;
        for (int i = 0; i < threads.length; i++) {
            sum += threads[i].count;
        }

        s = System.currentTimeMillis() - s;
        System.out.println("总共" + sum + "个质数，耗时：" + s);

    }

}

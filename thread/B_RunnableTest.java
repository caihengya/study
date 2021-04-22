package thread;

public class B_RunnableTest {


    /**
     * 实现Runnable接口创建线程
     */
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("该时间片段线程名称：" + Thread.currentThread().getName() + "  打印出来的数据是：" + i);
            }
        }
    }

    /**
     * main主线程也许会在自线程之后执行
     *
     * @param args
     */
    public static void main(String[] args) {
        MyRunnable runnable1 = new MyRunnable();
        Thread thread1 = new Thread(runnable1);

        MyRunnable runnable2 = new MyRunnable();
        Thread thread2 = new Thread(runnable2);

        MyRunnable runnable3 = new MyRunnable();
        Thread thread3 = new Thread(runnable3);

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("主线程名称：" + Thread.currentThread().getName());
    }


}

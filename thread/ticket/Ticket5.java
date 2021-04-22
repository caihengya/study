package thread.ticket;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 与 Lock 的对比：
 *
 * Lock是显式锁（手动开启和关闭锁，别忘记关闭锁），synchronized是隐式锁，出了作用域自动释放
 * Lock只有代码块锁，synchronized有代码块锁和方法锁
 * 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）
 */
public class Ticket5 {


    public static void main(String[] args) {
        Window5 w5 = new Window5();
        Thread t1 = new Thread(w5);
        Thread t2 = new Thread(w5);
        Thread t3 = new Thread(w5);


        t1.setName("窗口一：");
        t2.setName("窗口二：");
        t3.setName("窗口三：");


        t1.start();
        t2.start();
        t3.start();
    }

    private static class Window5 implements Runnable {
        private int ticket = 100;

        //1. 实力化一个ReentrantLock对象
        //默认参数是false，写为true之后，表示是公平的锁
        private ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            while (true) {
                try {
                    //2. 调用锁定方法。lock方法。获得锁
                    lock.lock();
                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                        ticket--;
                    } else {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

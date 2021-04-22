package thread.ticket;

/**
 * 用Runnable接口  实现卖票的逻辑
 * <p>
 * 解决线程安全问题，有几种方式？
 * <p>  1.synchronized
 * <p>      同步代码块
 * <p>      同步方法
 * <p>  2.Lock
 *
 * @author Administrator
 */
public class Ticket1 {

    /**
     * 出现问题 100的票会卖三次
     *
     * @param args
     */
    public static void main(String[] args) {
        Window window = new Window();
        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        thread1.start();
        thread2.start();
        thread3.start();
    }


    static class Window implements Runnable {
        private int ticket = 100;

        @Override
        public void run() {
            while (true) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }


}

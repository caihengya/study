package thread.ticket;

/**
 * 用Runnable接口  实现卖票的逻辑
 * 使用同步代码块 解决线程安全问题
 */
public class Ticket3 {

    /**
     * 在实现Runnable接口创建多线程的方式中，我们可以使用this充当锁，代替手动new一个对象，
     * 因为后面我们只创建一个线程的对象。
     *
     * @param args
     */
    public static void main(String[] args) {

        //因为只new了一个Window对象，所有共用一个ticket
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

    /**
     * 同步的优缺点：
     * 好处：解决了线程安全问题
     * 缺点；操作同步代码时，只能有一个线程参与，其他线程等待，相当于单线程。
     * 同步的范围：
     * （1）如何找问题，即代码是否存在线程安全？（非常重要）
     * 明确哪些代码是多线程运行的代码
     * 明确多个线程是否有共享数据
     * 明确多线程运行代码中是否有多条语句操作共享数据
     * （2）如何解决呢？（非常重要）
     * 　　对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行。即所有操作共享数据的这些语句都要放在同步范围中
     * （3）切记：
     * <p>
     * 范围太小：没锁住所有有安全问题的代码
     * 范围太大：没发挥多线程的功能
     */
    static class Window implements Runnable {
        //不用static也可以三个线程共用一个ticket
        private int ticket = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
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
}

package thread.ticket;

/**
 * 用Therad类 实现卖票的逻辑
 * 使用同步代码块 解决线程安全问题
 */
public class Ticket4 {

    /**
     * 在继承Thread类创建多线程的方式中，慎用this，考虑我们的this是不是唯一的。
     * 我们可以使用当前类来充当这个是锁。synchronized (Window.class)
     *
     * @param args
     */
    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();

        window1.setName("窗口一");
        window2.setName("窗口二");
        window3.setName("窗口三");

        window1.start();
        window2.start();
        window3.start();
    }

    static class Window extends Thread {
        //使用static保证 共用ticket
        private static int ticket = 100;

        @Override
        public void run() {
            //不能使用this，因为后面会有三个Window对象。
            //可以使用Window.class，类也是一个对象，是唯一的
            synchronized (this) {
                while (true) {
                    synchronized (Window.class) {
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
}

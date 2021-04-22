package thread.ticket;

/**
 * 用Therad类 实现卖票的逻辑
 */
public class Ticket2 {

    public static void main(String[] args) {
        Window window1=new Window();
        Window window2=new Window();
        Window window3=new Window();

        window1.setName("窗口一");
        window2.setName("窗口二");
        window3.setName("窗口三");

        window1.start();
        window2.start();
        window3.start();
    }


    static class Window extends Thread{
        private  static int ticket = 100;
        @Override
        public void run() {

            synchronized (this) {
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
}

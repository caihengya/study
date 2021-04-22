package thread;

import java.util.Scanner;

/**
 * inturrupt打断线程
 */
public class C_InterruptTest {

    /**
     * 当前秒数内抛异常之后，会继续执行后续线程中的内容
     * @param args
     */
    public static void main(String[] args) {
        MyThread thread=new MyThread();
        thread.start();

        System.out.println("按回车结束线程");

        new Scanner(System.in).nextLine();
        thread.interrupt();
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<=10;i++) {
                try {
                    //每隔1秒钟打印一次
                    System.out.println("该时间片段线程名称："+Thread.currentThread().getName()+"  打印出来的数据是："+i);
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("main线程打断了子线程");
                }
            }
        }
    }

}

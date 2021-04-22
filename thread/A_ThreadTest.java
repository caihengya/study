package thread;

/**
 * 一个程序至少有一个进程，一个进程至少有一个线程；
 * 线程是进程的一个实体，是CPU调度和分派的基本单位；
 * 一个线程可以创建和撤销另一个线程；
 * 同一个进程中的多个线程之间可以并发执行
 * 在Java中，每次程序运行至少启动2个线程：一个是main线程，一个是垃圾收gc集线程。
 * 每当使用java命令执行一个类的时候，实际上都会启动一个JVM，每一个JVM实际上就是在操作系统中启动了一个进程；
 * 多线程可以等价于多任务，当有多个任务要处理，多个任务一起做所消耗的时间比任务串行起来所消耗的时间短；
 * 可以通过Thead和Runnable两种方式创建线程；
 * 线程和进程一样分为五个阶段：创建、就绪、运行、阻塞、终止；
 * 多个线程同时运行的时候，每段时间片中只执行一个线程;
 * 线程创建成功启动之后，并不是马上执行，而是等待CPU创建分配时间片之后才会真正执行，多个线程谁先抢到时间片，谁就先执行；
 * main主线程也许会在自线程之后执行（下面有例子）；
 * 线程的生命周期只有一次；
 *
 * 线程常用方法
 * <p>     线程暂停方法 sleep（毫秒）；例如我们实现一个显示时间功能，可以sleep（1000）；
 * <p>     getName（）/setName；在run方法中获取线程和设置线程名称；
 * <p>     currentThread（）；获取当前线程，结合Runnable使用的时候会调用；
 * <p>     interrupt（）；打断线程暂停状态，B线程在sleep暂停，A线程打断B线程，B线程会异常；
 * <p>     join（）；当前线程等待被调用的线程结束；
 * <p>     yiled（）；当前线程让出CPU资源，主动让给其他线程，以后也可能会继续抢占CPU时间片；
 * <p>     setPriority()和getPriority()；优先级，默认优先级是5，不需要手动改，从1到10，优先级越高，获得的CPU越多；
 * <p>     setDaemon(true)；设置为后台线程，或者叫守护线程；
 */
public class A_ThreadTest {


    /**
     * 继承Thread创建线程
     */
    static class MyThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("该时间片段线程名称："+Thread.currentThread().getName()+"  打印出来的数据是："+i);
            }
        }
    }

    /**
     * main主线程也许会在自线程之后执行
     * @param args
     */
    public static void main(String[] args) {
        MyThread myThread1=new MyThread();
        myThread1.start();
        MyThread myThread2=new MyThread();
        myThread2.start();
        MyThread myThread3=new MyThread();
        myThread3.start();

        //Thread.currentThread().getName() 获取当前线程名称
        System.out.println("线程名称："+Thread.currentThread().getName());
    }



}

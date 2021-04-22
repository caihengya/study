package thread;

/**
 * 不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 * 出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续类似与死循环。
 * <p>
 * 出现死锁的四个前提条件
 * <p>     1.互斥条件：一个资源每次只能被一个线程使用
 * <p>     2.请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放
 * <p>     3.不剥夺条件：进程已经获得的资源，在未使用完之前，不能强行剥夺
 * <p>     4.循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系
 * <p>
 * 如何避免死锁？
 * 答：
 * <p>      指定获取锁的顺序，举例：
 * <p>      比如某个线程只有获得 A 锁和 B 锁才能对某资源进行操作，在多线程条件下，如何避免死锁？
 * <p>      获得锁的顺序是一定的，比如规定只有获得 A 锁的线程才有资格获取 B 锁，按顺序获取锁就可以避免死锁。
 */
public class G_Deadlock {

    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        //继承方式创建匿名多线程
        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("A");
                    s2.append("1");
                    try {
                        Thread.sleep(100);//加大死锁概率
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2) {
                        s1.append("B");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        //实现方式创建匿名多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("C");
                    s2.append("3");
                    synchronized (s1) {
                        s1.append("D");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }

}

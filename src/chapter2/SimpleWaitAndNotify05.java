package chapter2;

/**
 * Created by 13 on 2017/5/4.
 * wait()方法会释放目标对象的锁,而Thread.sleep()方法不会释放任何资源.
 * 执行wait()方法后，Thread2执行时就可以获得锁
 * 
 * 
 * 线程操作的wait()、notify()、notifyAll()方法只能在同步控制方法或同步控制块内调用。
 * 如果在非同步控制方法或控制块里调用，程序能通过编译，但运行的时候，将得到 IllegalMonitorStateException 异常，
 * 并伴随着一些含糊信息，比如 ‘当前线程不是拥有者’。其实异常的含义是 调用wait()、notify()、notifyAll()的任务在调用这些方法前必须 ‘拥有’（获取）对象的锁。
 */
public class SimpleWaitAndNotify05 {
    final static Object object = new Object();

    public static class Thread1 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + ": start !");
                try {
                    System.out.println(Thread.currentThread().getName() + ": wait for object !");
                    object.wait(0);
                    System.out.println("wait 0");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": end!");
            }
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + ": start ! notify one thread");
                object.notify();
                try {
                	Thread.sleep(2000);
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": end!");
        }
    }

    public static void main(String args[]) {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
//        thread2.start();
    }

}

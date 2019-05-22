package chapter2;

/**
 * Created by 13 on 2017/5/4.
 */
public class AccountSync13 implements Runnable {
    static AccountSync13 instance = new AccountSync13();
    static volatile int i = 0;

    public static synchronized void increase() {
        i++;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    /**
     * 主函数是通过两个线程对i进行累加操作,最终的正确结果应为20000000,但是实际运行却远远小于正确数值,因为多个线程同时对i进行写入操作时,
     * 其中一个线程的结果会覆盖另外一个线程的操作,线程不安全导致了这种冲突.
     * 读取的值相同，写的时候就会覆盖
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
    	
        Thread thread1 = new Thread(new AccountSync13());
        Thread thread2 = new Thread(new AccountSync13());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }

}

package chapter2;

/**
 * Created by 13 on 2017/5/4.
 */
public class CreateThread01 implements Runnable {
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
        System.out.println("Hi!I am Runnable");
        System.out.println("2:"+Thread.currentThread().getName());
        
    }

    /**
     * 注意：
     * thread.run():当前线程调用的普通方法
     * thread.start():新线程
     * @param args
     */
    public static void main(String args[]) {
        Thread thread = new Thread(new CreateThread01());
//        thread.run();
        thread.start();
        System.out.println("1:"+Thread.currentThread().getName());
    }
}

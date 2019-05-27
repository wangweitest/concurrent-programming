package chapter3.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 13 on 2017/5/5.
 */
public class ReenterLockCondition06 implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {

        try {
            lock.lock();
            condition.await();
            System.out.println(Thread.currentThread().getName()+":Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String args[]) throws InterruptedException {
    	ReenterLockCondition06 reenterLockCondition = new ReenterLockCondition06();
        Thread thread1 = new Thread(reenterLockCondition,"thread1");
        Thread thread2 = new Thread(reenterLockCondition,"thread2");
        thread1.start();
        thread2.start();
        System.out.println("睡眠2秒钟");
        Thread.sleep(200);
        lock.lock();
        condition.signal();
        condition.signalAll();
        lock.unlock();
    }
}

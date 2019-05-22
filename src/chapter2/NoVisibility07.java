package chapter2;

/**
 * @author Administrator
 * while中打印时就会感知到flag的改变，不打印时就不能感知到，需要jvm深入学习
 */
public class NoVisibility07 {  
	
    private static boolean flag = false;  
    public static void main(String[] args) {  
  
        new Thread() {  
            int i = 0;  
            public void run() {  
                long tm = System.currentTimeMillis();  
                while (!flag) {  
                    i++;  
//                    System.out.println(i);  
                }  
                System.out.println(System.currentTimeMillis() - tm);  
            }  
        }.start();  
  
        new Thread() {  
            public void run() {  
                try {  
                    Thread.sleep(2000);  
                    flag = true;  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }.start();  
    }  
}  
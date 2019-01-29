package threadtest;


import java.util.ArrayList;
import java.util.List;

public class MaxThreadsMain {

  public static final int BATCH_SIZE = 10000;

  public static void main(String... args) throws InterruptedException {
	
	  
    List<Thread> threads = new ArrayList<Thread>();
    for(int wj = 0; wj< 20 ; wj++){
    System.out.println("attempt: "+ wj);
    try {
      for (int i = 0; i <= 40 * 1000; i += BATCH_SIZE) {
        long start = System.currentTimeMillis();
        addThread(threads, BATCH_SIZE);
        long end = System.currentTimeMillis();
        Thread.sleep(1000);
        long delay = end - start;
        System.out.printf("%,d threads: Time to create %,d threads was %.3f seconds %n", threads.size(), BATCH_SIZE, delay / 1e3);
      }
    } catch (Throwable e) {
      System.err.printf("After creating %,d threads, ", threads.size());
      e.printStackTrace();
    }
    for(Thread th:threads){
    	th.interrupt();
    }
    threads.clear();
    }
  }

  private static void addThread(List<Thread> threads, int num) {
    for (int i = 0; i < num; i++) {
      Thread t = new Thread(new Runnable() {
        volatile boolean run=true;
        public void interrupt() {
        	run = false;
        }
    	@Override
        public void run() {
          try {
            while (!Thread.interrupted() && run) {
              Thread.sleep(1000);
            }
          } catch (InterruptedException ignored) {
            //
          }
        }
      });
      t.setDaemon(true);
      t.setPriority(Thread.MIN_PRIORITY);
      threads.add(t);
      t.start();
    }
  }
}
package threads;

import java.util.concurrent.*;

//corePoolSize,maximumPoolSize,keepAliveTime,unit(时间单位)

public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(6);
        account a=new account(2000);
        ThreadPoolExecutor tp = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        ThreadPoolExecutor tp2 = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 10; i++) {
            task t = new task(i+100,a);
            task2 t1=new task2(i,a);
            tp.execute(t);
            tp2.execute(t1);
           // System.out.println("线程池中正在执行的数目：" + tp.getActiveCount() + "\n" + "总线程数目" + tp.getTaskCount() + tp.getQueue());

            //System.out.println("=========================="+a.account);

        }
        tp.shutdown();
        tp2.shutdown();
        try {
            boolean loop = true;
            do {    //等待所有任务完成
                loop = !tp.awaitTermination(100, TimeUnit.MILLISECONDS);  //阻塞，直到线程池里所有任务结束
            } while(loop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.account);
    }


    static class task implements Runnable {

        private int taskNum;
        private account a;

        public task(int num,account a) {
            this.taskNum = num;
            this.a = a;
        }

        @Override
        public void run() {
          //  System.out.println("正在执行task " + taskNum);
            try {
                a.save(500);
                System.out.println("存钱 " + taskNum + "执行完毕");
                System.out.println("当前money:    "+a.account);
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    static class task2 implements Runnable {

        private int taskNum;
        private account a;

        public task2(int num,account a) {
            this.taskNum = num;
            this.a = a;
        }

        @Override
        public void run() {
           // System.out.println("正在执行task " + taskNum);
            try {
                if (a.account>=1000) {
                    a.withDraw(1000);
                    System.out.println("取钱 " + taskNum + "执行完毕");
                    System.out.println("当前money:    "+a.account);
                    Thread.currentThread().sleep(500);
                }else {
                System.out.println("余额不足");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}

package threads;

import java.util.concurrent.*;

//corePoolSize,maximumPoolSize,keepAliveTime,unit(时间单位)

public class ThreadPool {

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(6);

        ThreadPoolExecutor tp=new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        for(int i=0;i<10;i++){
            task t=new task(i);
            tp.execute(t);
System.out.println("线程池中正在执行的数目："+tp.getActiveCount()+"\n"+"总线程数目"+tp.getTaskCount()+tp.getQueue());
            if (i==7){
                pool.shutdown();
            }
        }

    }


    static class task implements  Runnable{

        private int taskNum;

        public task(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }
}

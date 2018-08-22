package threads;

public class EvenNum implements Runnable {
    private NumberAdd na;

    public EvenNum(NumberAdd na) {
        this.na = na;
    }

    @Override
    public void run() {
        while(na.start<=100){

            synchronized (NumberAdd.class){
           //     System.out.print("偶数线程！");
                if (na.flag){
                    System.out.println(Thread.currentThread().getName()+"  :  "+na.start);
                    na.start++;
                    na.flag=false;
                    NumberAdd.class.notify();

                }
                else{
                    try {
                        NumberAdd.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

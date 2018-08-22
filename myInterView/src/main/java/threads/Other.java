package threads;

public class Other implements  Runnable {
    NumberAdd na;

    public Other(NumberAdd na) {
        this.na = na;
    }

    @Override
    public void run() {
        while (na.start<100){
            synchronized (NumberAdd.class){
                if(!na.flag){
                    System.out.println(Thread.currentThread().getName()+"  :  "+"我是来捣乱的");

                    na.flag=false;
                    na.flag_="我走了";
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

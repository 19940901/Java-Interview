package threads;

public class OddNum implements  Runnable {

    private NumberAdd na;

    public OddNum() {
    }

    public OddNum(NumberAdd na) {
        this.na = na;
    }

    @Override
    public void run() {
        while (na.start<=100){
            synchronized (NumberAdd.class){
                //System.out.print("奇数线程!");
                if(!na.flag){
                    System.out.println(Thread.currentThread().getName()+"  :  "+na.start);
                    na.start++;
                    na.flag=true;
                    NumberAdd.class.notify();
                }else{
                    try {
                        NumberAdd.class.notify();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }
            }
        }

    }
}

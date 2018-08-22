package threads;

public class NumberAdd {

    public Integer start=1;
    public boolean flag=false;
    public String flag_="time";

    public static void main(String[] args){

        NumberAdd num=new NumberAdd();
        Thread a=new Thread(new EvenNum(num));
        Thread b=new Thread(new OddNum(num));
      //  Thread c=new Thread(new Other(num));

        a.setName("A偶数");
        b.setName("B奇数");
      //  c.setName("C捣乱");

        b.start();

        a.start();

        //c.start();






    }


}

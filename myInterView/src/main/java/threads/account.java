package threads;

public class account {
    public int account;

    public account(int account) {
        this.account = account;
    }

    public  void save(int num){
        synchronized(this){
            account+=num;
        }

    }

    public void withDraw(int num){
        synchronized (this){
            account-=num;
        }
    }
}

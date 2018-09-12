package algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Fibonacci {
    public static void main(String[] args) {
        Long s = System.currentTimeMillis();
        BigDecimal a= new BigDecimal(1);
        BigDecimal b=new BigDecimal(1);
        BigDecimal res=new BigDecimal(0);

        for (int i = 0; i <= 1000000; i++) {

            res =a.add(b);
            a = b;
            b = res;
        }
        long e = System.currentTimeMillis();

        System.out.println("结果 : " + res.toEngineeringString().length()+ "  总用时: " + (e - s) / 1000 + "s");
System.out.println();

    }
}

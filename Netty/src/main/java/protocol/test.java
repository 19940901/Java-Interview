package protocol;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
//        System.out.println("890");
//        int a = 0;
//        System.out.println(a);
//        if (a < 10) {
//            System.out.println(a + "======");
//            {
//                while (a < 10) {
//                    a++;
//                    System.out.println("aaaaaaa" + a);
//                    if (a == 8) {
//                        System.out.println(a + "_______________");
//                        break;
//                    }
//                }
//            }
//            System.out.println(a);
//            if(a<9){
//                System.out.println("return"+a);
//                return;
//            }
//            System.out.println("=======");
//        }

        String a="kljkj";
        byte[] b=a.getBytes();
        System.out.println(b.toString()+"======"+new String(b)+"=========="+ Arrays.toString(b));
    }
}

package netty.protocol;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class test {
    static byte[] data;

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

//        String a="kljkj";
//        byte[] b=a.getBytes();
//        System.out.println(b.toString()+"======"+new String(b)+"=========="+ Arrays.toString(b));
//        byte[] b = new byte[2048];
//        try {
//
//
//            FileImageInputStream fin = new FileImageInputStream(new File("E:\\code\\Netty\\src\\main\\resources\\images\\jin.jpg"));
//
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//            byte[] buf = new byte[2048];
//            int temp = 0;
//            while ((temp = fin.read(buf)) != -1) {
//                out.write(buf, 0, temp);
//            }
//            data = out.toByteArray();
//            System.out.println(data.length+"===============");
//
//            out.close();
//
//
//            FileImageOutputStream fo = new FileImageOutputStream(new File("E:\\code\\Netty\\src\\main\\resources\\images\\jin1.jpg"));
//            fo.write(data, 0, data.length);
//            fo.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        double a= (Math.random()*10);
//        double b= (Math.random()*10);
//        System.out.println(a+b);
//
//
//        String s="ab";
//        String t="ab";
//        String u=new String("ab");
//        System.out.println(s==t);
//        System.out.println(s==u);

       final int init=6;
        Map<String, String> a = new HashMap<String, String>();

        Map<String, String> b = new LinkedHashMap<String, String>(init,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size()>init;
            }
        };


        a.put("1", "2");

        a.put("2", "3");
        a.put("3", "4");
        a.put("4", "5");
        a.put("5", "6");

        b.put("1", "2");

        b.put("2", "3");
        b.put("3", "4");
        b.put("4", "5");
        b.put("5", "6");
        b.put("6", "5");
        b.put("7", "6");
        b.put("8", "5");
        b.put("9", "6");

        System.out.println(a.get("3")+b.get("3"));


    }
}

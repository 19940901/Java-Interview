package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 阿拉伯数字转汉字，
 */
public class NumToCh {
    /*
    小数点前
     */
    private long INT_NUM = 0;
    /*
    小数点后
     */
    private String FLO_NUM;
    /*
    前一位是否为零，默认为true
     */
    private static boolean pre = false;

    private static boolean carry1 = false;

    private static final Map<Character, String> map = new HashMap<>(9);
    private static final Map<Integer, String> carry = new HashMap<>(5);
    private static Stack<Character> stack = new Stack<>();

    private final String[] digital = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private final String[] radices = {"", "十", "百", "千"};
    private final String[] bigRadices = {"", "万", "亿"};

    static {
        carry.put(0, null);
        carry.put(1, "十");
        carry.put(2, "百");
        carry.put(3, "千");
        carry.put(4, "万");
        carry.put(5, "亿");


        map.put((char) 0, "零");
        map.put((char) 1, "一");
        map.put((char) 2, "二");
        map.put((char) 3, "三");
        map.put((char) 4, "四");
        map.put((char) 5, "五");
        map.put((char) 6, "六");
        map.put((char) 7, "七");
        map.put((char) 8, "八");
        map.put((char) 9, "九");
    }


    public void SplitNum(String num) {

        INT_NUM = Long.parseLong(num.split(".")[0]);
        FLO_NUM = num.split(".")[1];
    }

    String d = "";
    int quotient;
    int modulus;
    int zeroCount = 0;
    String ch = "";

    public String IntToCh(String num) {
        for (int i = 0; i < num.length(); i++) {
            int p = num.length() - 1 - i;
            d = num.substring(i, i + 1);
            quotient = p / 4;
            modulus = p % 4;

            if (d.equals("0")) {
                zeroCount++;

            } else {
                if (zeroCount > 0) {
                    ch += digital[0];
                }

                if (d.equals("1")&&radices[modulus].equals("十")&&i==0) {
                    ch += radices[modulus];
                }else if (d.equals("1")&&radices[modulus].equals("十")&&zeroCount>0){
                    ch += radices[modulus];
                }else{
                    ch += digital[Integer.parseInt(d)] + radices[modulus];
                }
                zeroCount = 0;

            }
            if (modulus == 0 && zeroCount < 4) {
                ch += bigRadices[quotient];
            }
        }
        return ch;

    }


    public String floToCh(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            sb.append(map.get(Integer.parseInt(num.substring(i, i + 1))));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String a = "12345";
        // char c = a.charAt(1);

        String s = new NumToCh().IntToCh("110000000011");
        System.out.println(s);


    }
}

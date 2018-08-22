package algorithm;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
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
    private static Stack<Character> stack=new Stack<>();

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

    public String IntToCh(String num) {

        char[] n = num.toCharArray();
        for(int i=0;i<n.length;i++){
            stack.push(n[i]);
        }
        String s=null;
        String c=null;
        int index=0;

        while (!stack.empty()){
            //获取单位
            if (index==8){
                carry1=true;
            }else if (index%4!=0){
                c=carry.get(index%4);
            }else{
                c=carry.get(4);
            }

            //获取汉字
            s=map.get(stack.pop());
            index++;
        }



        return null;
    }


    public String floToCh(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            sb.append(map.get(Integer.parseInt(num.substring(i, i + 1))));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
       String a="12345";
       // char c = a.charAt(1);
        
       while (!stack.empty()){

           System.out.println(stack.pop());
       }


    }
}

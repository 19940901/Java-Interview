package Test;

import java.util.*;

public class LeftMove {
    public static void main(String [] args){
        System.out.print("sdfasfdas");
        List a=new ArrayList<String>();
        LinkedList b=new LinkedList();

        HashMap map=new HashMap();

        HashSet<Integer> aa=new HashSet<Integer>();
        aa.add(1);
        aa.add(1);
        System.out.println(aa.size()+"======");


        b.add("q");
        b.add("w");
        b.set(1,"e");
        System.out.println();
        System.out.println(b.get(1)+""+b.size());

        //count hash值
        int h=2;
        String name="cao";
        int i = (h = name.hashCode()) ^ (h >>> 16);
        System.out.println(h >>> 16);
    }

}

package algorithm;

import java.util.HashSet;

public class WithSumPair {
    static int[] d = {1, 3, 4, 8, 7, 9};
    static int[] d1 = {1, 5, 3, 2, 9, 7};

    public static boolean withSum(int[] data, int sum) {
        int low = 0;
        int high = data.length - 1;
        while (low < high) {
            int t = data[low] + data[high];
            if (t > sum) high--;
            else if (t < sum) low++;
            else return true;
        }
        return false;
    }

    public static boolean withSumNosorted(int[] data, int sum) {
        HashSet<Integer> set=new HashSet<>();
        for (int a:data){
            if (set.contains(a))return true;
            set.add(sum-a);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(withSum(d, 9));
        System.out.println(withSumNosorted(d1, 8));
    }
}

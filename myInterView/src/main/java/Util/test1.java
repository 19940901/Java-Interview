package Util;

import java.util.regex.Pattern;

public class test1 {
    public static void main(String[] args) {
        String line = "00:03:10,483 --> 00:03:13,849";
        String regex = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
        System.out.println(Pattern.matches(regex, line));
    }
}

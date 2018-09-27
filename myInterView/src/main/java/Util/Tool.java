package Util;

import java.io.IOException;
import java.util.regex.Pattern;

public class Tool {
    public static void main(String[] args)  {

        String path = "C:\\Users\\soul.cao\\Desktop\\srt\\";

        String line = "00:03:10,483 --> 00:03:13,849";
        String regex = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
        System.out.println(Pattern.matches(regex, line));
        System.out.println(Language.Lan.CH);
        Translator.translate(path + "test1" + ".srt", path + "test3" + ".srt");
        System.out.println();
    }
}

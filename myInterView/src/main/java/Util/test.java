package Util;

import java.io.IOException;
import java.util.Properties;

public class test {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        p.load(Tool.class.getResourceAsStream("/pro/youdao.properties"));
        System.out.println(p.getProperty("ID"));
    }
}

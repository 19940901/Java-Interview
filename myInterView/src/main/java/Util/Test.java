package Util;

import javax.swing.*;
import java.io.*;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws Exception {
        Youdao u = new Youdao();

        FileInputStream reader = new FileInputStream(new File("E:/code/subs.srt"));
        ProgressMonitorInputStream monitor= new ProgressMonitorInputStream(null, "翻译进度", reader);

        FileWriter writer = new FileWriter(new File("E:/code/subss.srt"));
        InputStreamReader inputStreamReader = new InputStreamReader(monitor);
        BufferedReader br = new BufferedReader(inputStreamReader);

        BufferedWriter bw = new BufferedWriter(writer);
        String regex = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";

        String line = null;
        while ((line = br.readLine()) != null) {

            if (Pattern.matches(regex, line)) {
                StringBuilder sb = new StringBuilder();
                //时间轴
                bw.write(line);
                bw.newLine();
                bw.flush();
                String sub = null;
                while ((sub = br.readLine()) != null) {
                    //英语字幕
                    if (!sub.equals("")) {
                        bw.write(sub);
                        bw.newLine();
                        bw.flush();
                        sb.append(sub + " ");
                    }
                    if (sub.equals("")) {
                        System.out.println(sb.toString());
                       // String trs = u.Inter(sb.toString());
                        //中文字幕
                        bw.write("test");
                        bw.newLine();
                        bw.newLine();
                        bw.flush();

                        break;
                    }
                }
            } else {
                //字幕编号
                if (!line.equals("")) {
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
            }
        }


    }


}

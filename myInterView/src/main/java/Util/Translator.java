package Util;

import javax.swing.*;
import java.io.*;
import java.util.regex.Pattern;

public class Translator {
    /**
     * @param sourceFile     源文件
     * @param desFile        翻译后的文件
     * @param sourceLanguage 源文件语言
     * @param tarLanguage    翻译语言
     */
    public static void translate(String sourceFile, String desFile, String sourceLanguage, String tarLanguage) {
        try {
            Youdao u = new Youdao();

            FileInputStream reader = new FileInputStream(new File(sourceFile));
            ProgressMonitorInputStream monitor = new ProgressMonitorInputStream(null, "翻译进度", reader);
            InputStreamReader inputStreamReader = new InputStreamReader(monitor);
            BufferedReader br = new BufferedReader(inputStreamReader);
            FileWriter writer = new FileWriter(new File(desFile));
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

                        //中文字幕
                        if (sub.equals("")) {
                            System.out.println(sb.toString());
                            String trs = u.Inter(sb.toString(), sourceLanguage, tarLanguage);
                            bw.write(trs);
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

            reader.close();
            monitor.close();
            inputStreamReader.close();
            br.close();
            writer.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("翻译完成");
    }


    /**
     * 默认源文件为英语
     *
     * @param sourceFile
     * @param desFile
     * @param desLanguage
     */
    public static void translate(String sourceFile, String desFile, String desLanguage) {
        translate(sourceFile, desFile, Language.Lan.EN.toString());
    }

    /**
     * 默认英语转汉语
     *
     * @param sourceFile 源文件
     * @param desFile    目标文件
     */
    public static void translate(String sourceFile, String desFile) {
        translate(sourceFile, desFile, Language.Lan.EN.toString(), Language.Lan.CH.toString());
    }

}

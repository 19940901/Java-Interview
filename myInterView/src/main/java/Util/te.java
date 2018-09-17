//package movie;
//
//import java.io.*;
//import java.util.regex.*;
//
//public class SrtDesigner extends SubtitleDesigner {
//    public static final int DELAY = 0;
//    public static final int FORWARD = 1;
//
//    public SrtDesigner(String src, String des) {
//        super(src, des);
//    }
//
//    private long timesToMs(int times[]) {
//        long ms;
//        ms = (times[0] * 3600 + times[1] * 60 + times[2]) * 1000 + times[3];
//        return ms;
//    }
//
//    private int[] msToTimes(long ms) {
//        int[] times = new int[4];
//        long tmp = ms;
//        times[3] = (int) (tmp % 1000);
//        tmp = tmp / 1000;
//        times[2] = (int) (tmp % 60);
//        tmp = tmp / 60;
//        times[1] = (int) (tmp % 60);
//        tmp = tmp / 60;
//        times[0] = (int) (tmp);
//        return times;
//    }
//
//    private int[] addMs(int[] times, long ms) {
//        long tmp;
//        int ret[];
//        tmp = timesToMs(times);
//        tmp += ms;
//        ret = msToTimes(tmp);
//        return ret;
//    }
//
//    private int[] reduceMs(int[] times, long ms) {
//        long tmp;
//        int ret[];
//        tmp = timesToMs(times);
//        tmp -= ms;
//        if (tmp < 0) return null;
//        ret = msToTimes(tmp);
//        return ret;
//    }
//
//    private String formatTime(int[] times) {
//        String ret;
//        ret = String.format("%02d:%02d:%02d,%03d", times[0], times[1], times[2], times[3]);
//        return ret;
//    }
//
//    private int[] getTimes(String inTime) {
//        int[] times = new int[4];
//        String tmp[] = inTime.split(",");
//        times[3] = Integer.parseInt(tmp[1]);
//        String time[] = tmp[0].split(":");
//        times[0] = Integer.parseInt(time[0]);
//        times[1] = Integer.parseInt(time[1]);
//        times[2] = Integer.parseInt(time[2]);
//        return times;
//    }
//
//    private String[] getStartAndEndTime(String line) {
//        Pattern p = Pattern.compile("(//d//d://d//d://d//d,//d//d//d)");
//        Matcher m = p.matcher(line);
//        String times[] = new String[2];
//        times[0] = m.find() ? m.group() : null;
//        times[1] = m.find() ? m.group() : null;
//        System.out.println("Start:" + times[0] + "End:" + times[1]);
//        if (times[0] == null || times[1] == null) return null;
//        return times;
//    }
//
//    private boolean editSubtitle(int ctl, long msecond) {
//        File srcFile = new File(src);
//        File desFile = new File(des);
//        BufferedReader reader = null;
//        BufferedWriter writer = null;
//        boolean visibleFlag = true;//是否显示String line;
//        try {
//            reader = new BufferedReader(new FileReader(srcFile));
//            writer = new BufferedWriter(new FileWriter(desFile));
//            String regex = "//d//d://d//d://d//d,//d//d//d --> //d//d://d//d://d//d,//d//d//d";
//            while ((line = reader.readLine()) != null) {
//                if (Pattern.matches(regex, line)) {
//                    visibleFlag = true;
//                    String startAndEndTimes[];
//                    int startTimes[], endTimes[];
//                    if ((startAndEndTimes = getStartAndEndTime(line)) == null) return false;
//                    startTimes = getTimes(startAndEndTimes[0]);
//                    endTimes = getTimes(startAndEndTimes[1]);
//                    if (ctl == FORWARD) {
//                        startTimes = reduceMs(startTimes, msecond);
//                        endTimes = reduceMs(endTimes, msecond);
//                        if (startTimes == null || endTimes == null) {
//                            visibleFlag = false;
//                            continue;
//                        }
//                    } else {
//                        startTimes = addMs(startTimes, msecond);
//                        endTimes = addMs(endTimes, msecond);
//                    }
//                    line = formatTime(startTimes) + " --> " + formatTime(endTimes);
//                    System.out.println(formatTime(startTimes));//
//                    System.out.println(formatTime(endTimes));
//                }
//                if (visibleFlag) {
//                    writer.write(line + "/n");//System.out.println(line);}}} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} finally{try {reader.close();writer.close();} catch (IOException e) {e.printStackTrace();}}return true;}@Overridepublic boolean delay(long msecond) {editSubtitle(DELAY, msecond);return false;}@Overridepublic boolean forward(long msecond) {editSubtitle(FORWARD, msecond);return false;}}
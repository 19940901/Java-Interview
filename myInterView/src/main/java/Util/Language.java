package Util;

public class Language {
    public static  enum Lan{
        CH("zh-CHS"),//中文
        EN("EN");//女

        private final String text;
        private Lan(final String text){
            this.text=text;
        }
        @Override
        public String toString(){
            return text;
        }
    }
}

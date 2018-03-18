package web.utilities;

/**
 * 用于生成6位验证码
 */
public class FormatValid {
    public static String createValid(){
        int valid =(int)((Math.random()*9+1)*100000);
        return String.valueOf(valid);
    }
}

package com.android.lala.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author emotiona
 * Email emotiona_xiaoshi@icloud.com
 * Date 2016.07.25
 **/
public class CommUtils {
    public static boolean isMobile(String mobiles) {
        if (mobiles.length() == 11) {
            String regExp = "^[1]([3,4,5,7,8][0-9]{1}|59|58|88|89)[0-9]{8}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } else {
            return false;
        }
    }

    /***
     *
     * @param pwd
     * @return
     */
    public static boolean isEnabalePwd(String pwd) {
        String regExp = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,18}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    /**
     * @param instream
     * @return
     */
    public static String InputStreamToString(InputStream instream) {
        StringBuffer buffer = new StringBuffer();
        String s = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(instream, "UTF-8"));

            while ((s = bufferedReader.readLine()) != null) {
                buffer.append(s);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}

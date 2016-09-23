package com.android.lala.api;

import android.app.LauncherActivity;

import com.android.lala.config.LalaConfig;

/**
 * Author emotiona
 * Email emotiona_xiaoshi@icloud.com
 * Date 2016.07.25
 **/
public class ApiContacts {
    public static final String USER_LOGIN = LalaConfig.httpServer + "/app/login";
    public static final String USER_REGISTER = LalaConfig.httpServer + "/app/register";
    public static final String REGISTER_GETCODE=LalaConfig.httpServer+"/app/mobilephone/sendVerifyCodeSms";
    public static final String REGISTER_ISEXISTENCE=LalaConfig.httpServer+"/app/finduser";
    public static final String ARTICLE=LalaConfig.httpServer+ "/app/article";
    public static final String CONTENTURL=LalaConfig.httpServer+"/static/html";
    public static final String ARTICLERE_COM =LalaConfig.httpServer+"/app/article/channels";
    public static final String INFORMATION_ARTICLE_TECHNALOGY = LalaConfig.httpServer+"/app/technology";
    public static final String INFORMATION_ARTICLE_CULTURE=LalaConfig.httpServer+"/app/CurturalInnovation";
    public static final String INFORMATION_CHANNEL =LalaConfig.httpServer+"/app/recommend/channels";
    public static final String INFORMATION_ARTICLE_COM=LalaConfig.httpServer+"/app/recommend/article";
    public static final String CHANNEL=LalaConfig.httpServer+"/app/channels/article";
    public static final String INFORMATION_ARTICLE_SELETC=LalaConfig.httpServer+"/app/technology/screen";
    public static final String ARTICLE_GETCOMMENT=LalaConfig.httpServer+"/app/article/comment";
    public static final String ARTICLE_POSTCOMMENT=LalaConfig.httpServer+"/app/article/comment/save";
    public static final String ARTICLE_SEARCH=LalaConfig.httpServer+"/app/article/search";
    public static final String FORGETPASSWORD_GETCODE=LalaConfig.httpServer+"/app/mobilephone/sendVerifyCodeSms/password";
    public static final String FORGETPASSWPRD_SETPWD=LalaConfig.httpServer+"/app/find_password";
    public static final String UPLOADFILE=LalaConfig.httpServer+"/app/user/photo";
    public static final String UPDATENAME=LalaConfig.httpServer+"/app/user/name";
    public static final String MARKET_GETNEW=LalaConfig.httpServer+"/app/market";
    public static final String MARKET_GETOLD=LalaConfig.httpServer+"/app/market/xia";
    public static final String BUY_GETCONTENT=LalaConfig.httpServer+"/app/market/product";
}


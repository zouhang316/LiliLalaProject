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
    public static final String ARTICLE=LalaConfig.httpServer+ "/app/article";
    public static final String CONTENTURL=LalaConfig.httpServer+"/static/html";
    public static final String ARTICLERECOMMENDURL=LalaConfig.httpServer+"/app/article/channels";
    public static final String INFORMATIONARTICLE= LalaConfig.httpServer+"/app/technology";
}

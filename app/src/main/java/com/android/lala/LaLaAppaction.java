package com.android.lala;

import android.app.Application;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.android.lala.utils.LalaLog;

import java.util.HashMap;

public class LaLaAppaction extends Application {
    public static boolean isLoadLocaldata = true;
    public static boolean isUseHTTPS = false;
    public static boolean SAMULATION = false;
    /**
     * 存储验证码倒计时时间
     */
    public static HashMap<String, Long> longHashMap;
    //OSS的Bucket
    public static final String OSS_BUCKET = "shunxe";
    //设置OSS数据中心域名或者cname域名
    public static final String OSS_BUCKET_HOST_ID = "http://oss-cn-shenzhen.aliyuncs.com";
    //Key
    private static final String accessKey = "9p0YXjt1J654vrX2";
    private static final String screctKey = "nIr4FVcUmJvcuR8TdcnmFRDZs2iqDB";
    public static OSS oss;

    @Override
    public void onCreate() {
        super.onCreate();
        LalaLog.setDebug(true);
        longHashMap = new HashMap<>();
        initOSSConfig();
    }
    private void initOSSConfig(){
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKey, screctKey);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        if(BuildConfig.DEBUG){
            OSSLog.enableLog();
        }
        oss = new OSSClient(getApplicationContext(), LaLaAppaction.OSS_BUCKET_HOST_ID, credentialProvider, conf);
    }
}

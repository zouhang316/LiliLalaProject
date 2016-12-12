package com.android.lala.market.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.market.adapter.ClassficationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2016/11/30.
 * 497239511@qq.com
 */
public class ClassificationActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private GridView zhineng;
    private GridView jiaju;
    private GridView wenhua;
    private GridView jiankang;
    private GridView zhuangshi;
    private GridView muying;
    private GridView chuxing;
    private GridView yinshi;
    private String [] zhinengtag;
    private String [] jiajutag;
    private String [] wenhuatag;
    private String [] jiankangtag;
    private String [] zhuangshitag;
    private String [] muyingtag;
    private String [] chuxingtag;
    private String [] yinshitag;
    private RelativeLayout allA;
    private RelativeLayout zhinengA;
    private RelativeLayout jiajuA;
    private RelativeLayout wenhuaA;
    private RelativeLayout jiankangA;
    private RelativeLayout zhuangshiA;
    private RelativeLayout muyingA;
    private RelativeLayout chuxingA;
    private RelativeLayout yinshiA;


    @Override
    protected void initData() {
        zhinengtag=new String[]{"助眠产品","健康管理","娱乐影音","手机拍档","电脑配件","充电设备","智能家电","插座/开关","路由器","3D打印"
        ,"无人机","智能机器人","其它"};
        jiajutag=new String[]{"家具","家纺","灯饰照明","清洁用品","收纳/置物","园艺/宠物","其它"};
        wenhuatag=new String[]{"文具","包袋","服装","配饰","其它"};
        jiankangtag=new String[]{"美容仪器","环境监测","空气监测","智能水杯","身材管理","其它"};
        zhuangshitag=new String[]{"墙面装饰","桌面装饰","其它"};
        muyingtag=new String[]{"妈妈用的","宝宝用的","儿童玩具","其它"};
        chuxingtag=new String[]{"自行车","电动车","平衡车","滑板车","运动器材","运动穿戴","运动着装","车载配件","其它"};
        yinshitag=new String[]{"饮品","进口食品","糖果巧克力","餐具","厨房用具","其它"};

    }

    @Override
    protected void initListener() {
        zhineng.setOnItemClickListener(this);
        jiaju.setOnItemClickListener(this);
        wenhua.setOnItemClickListener(this);
        jiankang.setOnItemClickListener(this);
        zhuangshi.setOnItemClickListener(this);
        muying.setOnItemClickListener(this);
        chuxing.setOnItemClickListener(this);
        yinshi.setOnItemClickListener(this);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.market_all:
                        intentActionA("全部商品");
                        break;
                    case R.id.market_zhineng:
                        intentActionA("智能产品");
                        break;
                    case R.id.market_wenhua:
                        intentActionA("文化创意");
                        break;
                    case R.id.market_jiaju:
                        intentActionA("家居生活");
                        break;
                    case R.id.market_jiankang:
                        intentActionA("健康美容");
                        break;
                    case R.id.market_zhuangshi:
                        intentActionA("装饰摆件");
                        break;
                    case R.id.market_muying:
                        intentActionA("母婴儿童");
                        break;
                    case R.id.market_yundong:
                        intentActionA("运动/出行");
                        break;
                    case R.id.market_yinshi:
                        intentActionA("饮食/料理");
                        break;
                }
            }
        };
        allA.setOnClickListener(listener);
        zhinengA.setOnClickListener(listener);
        jiajuA.setOnClickListener(listener);
        wenhuaA.setOnClickListener(listener);
        jiankangA.setOnClickListener(listener);
        zhuangshiA.setOnClickListener(listener);
        muyingA.setOnClickListener(listener);
        chuxingA.setOnClickListener(listener);
        yinshiA.setOnClickListener(listener);
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_classification);
        setTitle("分类");
        zhineng=findView(R.id.zhinengchanpin);
        jiaju=findView(R.id.jiajushenghuo);
        wenhua=findView(R.id.wenhuachuangyi);
        jiankang=findView(R.id.jiankangmeirong);
        zhuangshi=findView(R.id.zhuangshibaijian);
        muying=findView(R.id.muyingertong);
        chuxing=findView(R.id.yundongchuxing);
        yinshi=findView(R.id.yinshiliaoli);
        allA=findView(R.id.market_all);
        zhinengA=findView(R.id.market_zhineng);
        jiajuA=findView(R.id.market_jiaju);
        wenhuaA=findView(R.id.market_wenhua);
        jiankangA=findView(R.id.market_jiankang);
        zhuangshiA=findView(R.id.market_zhuangshi);
        muyingA=findView(R.id.market_muying);
        chuxingA=findView(R.id.market_yundong);
        yinshiA=findView(R.id.market_yinshi);

        ClassficationAdapter zhinengAdapter=new ClassficationAdapter(this,zhinengtag);
        zhineng.setAdapter(zhinengAdapter);
        ClassficationAdapter jiajuAdapter=new ClassficationAdapter(this,jiajutag);
        jiaju.setAdapter(jiajuAdapter);
        ClassficationAdapter wenhuaAdapter=new ClassficationAdapter(this,wenhuatag);
        wenhua.setAdapter(wenhuaAdapter);
        ClassficationAdapter jiankangAdapter=new ClassficationAdapter(this,jiankangtag);
        jiankang.setAdapter(jiankangAdapter);
        ClassficationAdapter zhuangshiAdapter=new ClassficationAdapter(this,zhuangshitag);
        zhuangshi.setAdapter(zhuangshiAdapter);
        ClassficationAdapter muyingAdapter=new ClassficationAdapter(this,muyingtag);
        muying.setAdapter(muyingAdapter);
        ClassficationAdapter chuxingAdapter=new ClassficationAdapter(this,chuxingtag);
        chuxing.setAdapter(chuxingAdapter);
        ClassficationAdapter yinshiAdapter=new ClassficationAdapter(this,yinshitag);
        yinshi.setAdapter(yinshiAdapter);

    }
    private void intentActionA( String tag){
        Intent intent=new Intent();
        intent.putExtra("tag",tag);
        setResult(RESULT_FIRST_USER,intent);
        finish();
    }

    private void intentActionB( String tag){
        Intent intent=new Intent();
        intent.putExtra("tag",tag);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intentActionB(parent.getAdapter().getItem(position).toString());

    }
}

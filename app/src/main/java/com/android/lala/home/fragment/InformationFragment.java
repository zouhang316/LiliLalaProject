package com.android.lala.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.article.adapter.InformationArticleAdapter;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:资讯
 */
public class InformationFragment extends BaseFragment {
    private HttpListener<String> httpListener;
    private ConvenientBanner banner;
    private ArrayList<Integer> pageimagelist;
    private ListView mArticlelistview;
    private InformationArticleAdapter adapter;
    @Override
    public void initData(Bundle savedInstanceState) {
        getPageImages();
        commDataDao=new CommDataDaoImpl(getActivity(),false,"article.json");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                LalaLog.i("information",response);
                Helper helper= JsonResultUtils.helper(response);
                String article=helper.getContentByKey("article");
                List<ArticleViewBean> articleViewBeanList= FastJsonHelper.getObjects(article,ArticleViewBean.class);
                if (null !=articleViewBeanList){
                    adapter=new InformationArticleAdapter(getActivity(),articleViewBeanList);
                    mArticlelistview.setAdapter(adapter);
                }
            }

            @Override
            public void onFail(String errMsg) {
            }
        };



    }

    @Override
    public void initView(View view) {
        banner= (ConvenientBanner) view.findViewById(R.id.information_viewpage);
        mArticlelistview= (ListView) view.findViewById(R.id.information_articlelistview);
        initViewPager();
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.INFORMATIONGETARTICLE,
                ApiContacts.INFORMATIONARTICLE,httpListener, new HashMap<String, String>(),true);
    }



    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_information;
    }

    public void initViewPager(){
            banner.setPages(
                    new CBViewHolderCreator< LocalImageHolderView>() {
                        @Override
                        public LocalImageHolderView createHolder() {
                            return new LocalImageHolderView();
                        }
                    },pageimagelist
            ).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_selected});
    }
    public void getPageImages(){
        pageimagelist=new ArrayList<>();
        pageimagelist.add(R.drawable.view_dot1);
        pageimagelist.add(R.drawable.view_dot2);

    }
    public void getDataByVolley(){
//        RequestQueue queue= Volley.newRequestQueue(getActivity());
//        StringRequest request=new StringRequest(Request.Method.POST,)
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }
}

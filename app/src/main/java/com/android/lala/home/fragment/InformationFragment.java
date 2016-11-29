package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.article.activity.ArticleActivity;
import com.android.lala.article.activity.SearchArticleActivity;
import com.android.lala.article.adapter.ArticleRecommendAdapter;
import com.android.lala.article.adapter.InformationArticleAdapter;
import com.android.lala.article.adapter.InformationChannelViewAdapter;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.article.bean.ChannelViewBean;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.view.CircleImageView;
import com.android.lala.view.MyListView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:资讯
 */
public class InformationFragment extends BaseFragment implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private ConvenientBanner banner;
    private ArrayList<Integer> pageimagelist;
    private ListView mArticlelistview,mArticleComListview;
    private MyListView subListview;
    private List<ArticleViewBean> articleViewBeanList,articleCommendBeanList;
    private InformationArticleAdapter adapter,adapter2;
    private InformationChannelViewAdapter channelViewAdapter;
    private RecyclerView mChannelView;
    private TextView indcator1,indcator2,indcator3;
    private TextView pop_a_btn1,pop_a_btn2,pop_a_btn3,pop_a_btn4,pop_a_btn5;
    private TextView pop_b_btn1,pop_b_btn2,pop_b_btn3,pop_b_btn4,pop_b_btn5,pop_b_btn6,pop_b_btn7;
    private PopupWindow pop_a,pop_b;
    private RadioButton radioButton_a,radioButton_b,radioButton_c;
    private View popview_a,popview_b;
    private ImageView search;
    private ScrollView scrollView;
    @Override
    public void initData(Bundle savedInstanceState) {

        getPageImages();
        commDataDao=new CommDataDaoImpl(getActivity(),false,"article.json");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                switch (what){
                    case HttpWhatContacts.GETARTICLE :
                        LalaLog.i("information", response);
                        Helper helper = JsonResultUtils.helper(response);
                        String article = helper.getContentByKey("article");
                        articleViewBeanList = FastJsonHelper.getObjects(article, ArticleViewBean.class);
                        if (null != articleViewBeanList) {
                            adapter = new InformationArticleAdapter(getActivity(), articleViewBeanList);
                            mArticlelistview.setAdapter(adapter);
                        }
                        break;
                    case HttpWhatContacts.GETCHANNEL:
                        LalaLog.i("information_channel", response);
                        Helper helper2=JsonResultUtils.helper(response);
                        String channel=helper2.getContentByKey("channels");
                        List<ChannelViewBean> channelViewBeenList=FastJsonHelper.getObjects(channel,ChannelViewBean.class);
                        if (null !=channelViewBeenList){
                            channelViewAdapter=new InformationChannelViewAdapter(getActivity(),channelViewBeenList);
                            mChannelView.setAdapter(channelViewAdapter);
                        }
                        break;
                    case HttpWhatContacts.GETARTICLERECOMMEND:
                        LalaLog.i("information_com", response);
                        Helper helper3 = JsonResultUtils.helper(response);
                        String article2 = helper3.getContentByKey("article");
                        articleCommendBeanList = FastJsonHelper.getObjects(article2, ArticleViewBean.class);
                            adapter2 = new InformationArticleAdapter(getActivity(), articleCommendBeanList);
                            mArticleComListview.setAdapter(adapter2);
                        break;
                }
            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("提示",errMsg);
            }
        };



    }

    @Override
    public void initPopwind(LayoutInflater inflater) {
        popview_a=inflater.inflate(R.layout.popwind_technology,null);
        popview_b=inflater.inflate(R.layout.popwind_culture,null);
        pop_a_btn1= (TextView) popview_a.findViewById(R.id.pop_a_btn1);
        pop_a_btn2= (TextView) popview_a.findViewById(R.id.pop_a_btn2);
        pop_a_btn3= (TextView) popview_a.findViewById(R.id.pop_a_btn3);
        pop_a_btn4= (TextView) popview_a.findViewById(R.id.pop_a_btn4);
        pop_a_btn5= (TextView) popview_a.findViewById(R.id.pop_a_btn5);
        pop_a_btn1.setOnClickListener(this);
        pop_a_btn2.setOnClickListener(this);
        pop_a_btn3.setOnClickListener(this);
        pop_a_btn4.setOnClickListener(this);
        pop_a_btn5.setOnClickListener(this);

        pop_b_btn1= (TextView) popview_b.findViewById(R.id.pop_b_btn1);
        pop_b_btn2= (TextView) popview_b.findViewById(R.id.pop_b_btn2);
        pop_b_btn3= (TextView) popview_b.findViewById(R.id.pop_b_btn3);
        pop_b_btn4= (TextView) popview_b.findViewById(R.id.pop_b_btn4);
        pop_b_btn5= (TextView) popview_b.findViewById(R.id.pop_b_btn5);
        pop_b_btn6= (TextView) popview_b.findViewById(R.id.pop_b_btn6);
        pop_b_btn7= (TextView) popview_b.findViewById(R.id.pop_b_btn7);
        pop_b_btn1.setOnClickListener(this);
        pop_b_btn2.setOnClickListener(this);
        pop_b_btn3.setOnClickListener(this);
        pop_b_btn4.setOnClickListener(this);
        pop_b_btn5.setOnClickListener(this);
        pop_b_btn6.setOnClickListener(this);
        pop_b_btn7.setOnClickListener(this);
    }

    @Override
    public void initView(View view) {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        banner= (ConvenientBanner) view.findViewById(R.id.information_viewpage);
        mArticlelistview= (ListView) view.findViewById(R.id.information_articlelistview);
        mArticlelistview.setFocusable(false);
        mArticleComListview= (ListView) view.findViewById(R.id.information_articlerecommendlist);
        mArticleComListview.setFocusable(false);
        mArticlelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("id",articleViewBeanList.get(position).getId());
                LalaLog.i("intentid",articleViewBeanList.get(position).getId());
                intent.putExtra("toppic",articleViewBeanList.get(position).getBackground());
                startActivity(intent);
            }
        });
        mArticleComListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("id",articleCommendBeanList.get(position).getId());
                LalaLog.i("intentid",articleCommendBeanList.get(position).getId());
                intent.putExtra("toppic",articleCommendBeanList.get(position).getBackground());
                startActivity(intent);
            }
        });
        mChannelView= (RecyclerView) view.findViewById(R.id.information_channel_recycleview);
        mChannelView.setLayoutManager(manager);
        subListview= (MyListView) view.findViewById(R.id.information_articlesublv);
        indcator1= (TextView) view.findViewById(R.id.radio_indcator1);
        indcator2= (TextView) view.findViewById(R.id.radio_indcator2);
        indcator3= (TextView) view.findViewById(R.id.radio_indcator3);
        radioButton_a= (RadioButton) view.findViewById(R.id.information_rbt_technology);
        radioButton_b= (RadioButton) view.findViewById(R.id.information_rbt_culture);
        radioButton_c= (RadioButton) view.findViewById(R.id.information_rbt_subscription);
        scrollView= (ScrollView) view.findViewById(R.id.information_scrollview);
        scrollView.smoothScrollTo(0,0);
        search= (ImageView) view.findViewById(R.id.information_articlesearch);
        search.getBackground().setAlpha(170);
        initViewPager();
        getDataByVolley();
        initListener();
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
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETARTICLE,
                ApiContacts.INFORMATION_ARTICLE_TECHNALOGY,httpListener, new HashMap<String, String>(),false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETCHANNEL,
                ApiContacts.INFORMATION_CHANNEL,httpListener,new HashMap<String, String>(),false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETARTICLERECOMMEND,
                ApiContacts.INFORMATION_ARTICLE_COM,httpListener,new HashMap<String, String>(),false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LalaLog.i("informationstate","resum");
        banner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.information_rbt_technology:
                pop_a=new PopupWindow(popview_a,indcator1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                pop_a.showAsDropDown(indcator1,0,0);
                popview_a.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (pop_a !=null && pop_a.isShowing()){
                            pop_a.dismiss();
                            pop_a=null;
                        }
                        return false;
                    }
                });
                break;

            case R.id.information_rbt_culture:
                pop_b=new PopupWindow(popview_b,indcator2.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                pop_b.showAsDropDown(indcator2,0,0);
                popview_b.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (pop_b !=null && pop_b.isShowing()){
                            pop_b.dismiss();
                            pop_b=null;
                        }
                        return false;
                    }
                });
                break;
            case R.id.pop_a_btn1:
                radioButton_a.setText("高新科技");
                getDatabyVolley("全部");
                showIndcator();
                pop_a.dismiss();
                break;
            case R.id.pop_a_btn2:
                radioButton_a.setText(pop_a_btn2.getText());
                getDatabyVolley(pop_a_btn2.getText().toString());
                showIndcator();
                pop_a.dismiss();
                break;
            case R.id.pop_a_btn3:
                radioButton_a.setText(pop_a_btn3.getText());
                getDatabyVolley(pop_a_btn3.getText().toString());
                showIndcator();
                pop_a.dismiss();
                break;
            case R.id.pop_a_btn4:
                radioButton_a.setText(pop_a_btn4.getText());
                getDatabyVolley(pop_a_btn4.getText().toString());
                showIndcator();
                pop_a.dismiss();
                break;
            case R.id.pop_a_btn5:
                radioButton_a.setText(pop_a_btn5.getText());
                getDatabyVolley(pop_a_btn5.getText().toString());
                showIndcator();
                pop_a.dismiss();
                break;
            case R.id.pop_b_btn1:
                radioButton_b.setText("文化创意");
                getDatabyVolley("全部");
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.pop_b_btn2:
                radioButton_b.setText(pop_b_btn2.getText());
                getDatabyVolley(pop_b_btn2.getText().toString());
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.pop_b_btn3:
                radioButton_b.setText(pop_b_btn3.getText());
                getDatabyVolley(pop_b_btn3.getText().toString());
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.pop_b_btn4:
                radioButton_b.setText(pop_b_btn4.getText());
                getDatabyVolley(pop_b_btn4.getText().toString());
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.pop_b_btn5:
                radioButton_b.setText(pop_b_btn5.getText());
                getDatabyVolley(pop_b_btn5.getText().toString());
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.pop_b_btn6:
                radioButton_b.setText(pop_b_btn6.getText());
                getDatabyVolley(pop_b_btn6.getText().toString());
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.pop_b_btn7:
                radioButton_b.setText(pop_b_btn7.getText());
                getDatabyVolley(pop_b_btn7.getText().toString());
                showIndcator();
                pop_b.dismiss();
                break;
            case R.id.information_articlesearch:
                Intent intent=new Intent(getActivity(), SearchArticleActivity.class);
                startActivity(intent);
        }

    }

    public void initListener(){
        radioButton_a.setOnClickListener(this);
        radioButton_b.setOnClickListener(this);
        radioButton_c.setOnClickListener(this);
        search.setOnClickListener(this);


    }
    public void getDatabyVolley(final String sort){
        String url;
        if (sort.equals("全部") && radioButton_a.isChecked()){
            url=ApiContacts.INFORMATION_ARTICLE_TECHNALOGY;
        }else  if (sort.equals("全部") && radioButton_b.isChecked()){
            url=ApiContacts.INFORMATION_ARTICLE_CULTURE;
        }else{
            url=ApiContacts.INFORMATION_ARTICLE_SELETC;
        }


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Helper helper = JsonResultUtils.helper(s);
                String value = helper.getContentByKey("article");
                articleViewBeanList.clear();
                articleViewBeanList = FastJsonHelper.getObjects(value, ArticleViewBean.class);
                LalaLog.i("ArticleViewBean", articleViewBeanList.toString());
                adapter = new InformationArticleAdapter(getActivity(), articleViewBeanList);
                mArticlelistview.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("sort", sort);
                return map;
            }
        };
        queue.add(request);

    }

    public void showIndcator(){
        if (radioButton_a.isChecked()){
            indcator1.setVisibility(View.VISIBLE);
            indcator2.setVisibility(View.INVISIBLE);
            indcator3.setVisibility(View.INVISIBLE);
        }else if (radioButton_b.isChecked()){
            indcator1.setVisibility(View.INVISIBLE);
            indcator2.setVisibility(View.VISIBLE);
            indcator3.setVisibility(View.INVISIBLE);
        }else {
            indcator1.setVisibility(View.INVISIBLE);
            indcator2.setVisibility(View.INVISIBLE);
            indcator3.setVisibility(View.VISIBLE);
        }
    }
}

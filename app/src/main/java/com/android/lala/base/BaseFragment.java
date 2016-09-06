package com.android.lala.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.http.VolleyHelper;

public abstract class BaseFragment extends Fragment {
//    private OnFragmentInteractionListener mListener;
    public CommDataDaoImpl commDataDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        VolleyHelper.getInstance().init(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View convertView = inflater.inflate(getFragmentLayoutId(), container, false);
        initView(convertView);
        initPopwind(inflater);
        return convertView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    public void initPopwind(LayoutInflater inflater){

    }

    /***
     * Interface callback templates, developers can customize according to their needs
     * 接口回调模版，开发者可以根据自己需求自定义
     */
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }

    /***
     * Initialization data, the subclass must implements this method
     * 初始化数据，子类必须实现此方法
     *
     * @param savedInstanceState
     */
    public abstract void initData(Bundle savedInstanceState);

    /***
     * Initialization view, the subclass must implements this method
     * 初始化控件，子类必须实现此方法
     *
     * @param view
     */
    public abstract void initView(View view);

    /***
     * Back Layout, subclass must implements this method
     * 返回子类布局，子类需要实现此方法
     *
     * @return
     */
    public abstract int getFragmentLayoutId();

}

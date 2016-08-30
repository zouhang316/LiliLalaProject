package com.android.lala.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.http.VolleyHelper;
import com.android.lala.utils.CommUtils;
import com.android.lala.utils.LalaLog;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author ssx
 */
public abstract class BaseActivity extends AppCompatActivity {
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private FrameLayout mContentLayout;
    public String TAG = this.getClass().getSimpleName();

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setContentView(R.layout.activity_base);
        VolleyHelper.getInstance().init(this);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mContentLayout = (FrameLayout) findViewById(R.id.content);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setBackBar(true);
        if (!isShowToolBar()) {
            getSupportActionBar().hide();
        }
        onActivityCreate(savedInstanceState);
        initListener();
    }

    protected abstract void initListener();

    protected abstract boolean isShowToolBar();

    protected abstract void onActivityCreate(Bundle savedInstanceState);

    public <T extends View> T findView(int viewId) {
        return (T) mContentLayout.findViewById(viewId);
    }


    public FrameLayout getContentLayout() {
        return mContentLayout;
    }

    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    @Override
    public void setTitle(CharSequence title) {
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        } else {
            mTitle.setText("");
        }
    }

    @Override
    public void setTitle(int titleId) {
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        if (titleId > 0) {
            mTitle.setText(titleId);
        } else {
            mTitle.setText("");
        }
    }

    public void setSubtitle(CharSequence title) {
        mToolbar.setSubtitle(title);
    }

    public void setSubtitle(int titleId) {
        mToolbar.setSubtitle(titleId);
    }

    public void setSubtitleTextColor(int color) {
        mToolbar.setSubtitleTextColor(color);
    }

    public void setTitleTextColor(int color) {
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        mTitle.setTextColor(color);
    }

    public void setBackBar(boolean isShow) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

    public void setContentBackground(int color) {
        mContentLayout.setBackgroundResource(color);
    }

    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        getLayoutInflater().inflate(layoutResID, mContentLayout, true);
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.addView(view, params);
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return onOptionsItemSelectedCompat(item);
    }

    protected boolean onOptionsItemSelectedCompat(MenuItem item) {
        return false;
    }

    public ViewGroup getContentRoot() {
        return mContentLayout;
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(int title, int message) {
        showMessageDialog(getText(title), getText(message));
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(int title, CharSequence message) {
        showMessageDialog(getText(title), message);
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, int message) {
        showMessageDialog(title, getText(message));
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /***
     * Show message dialog with custom DialogInterface.OnClickListener
     *
     * @param title
     * @param message
     * @param okListener
     */
    public void showMessageDialog(CharSequence title, CharSequence message, DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok, okListener);
        builder.show();
    }

    /***
     * show msg with Toast
     *
     * @param msg
     */
    public void showToastMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /***
     * 是否使用离线数据
     *
     * @return
     */
    public boolean isSamulation() {
        return false;
    }

    /***
     * 返回Assets目录下面json 文件名称
     *
     * @return
     */
    public String getJsonStrName() {
        return null;
    }

    /***
     * 根据 jsonName获取文件内容
     *
     * @return
     */
    public String getAssData() {
        String jsonName = getJsonStrName();
        if (TextUtils.isEmpty(jsonName)) {
            LalaLog.e(TAG, "you should override getJsonStrName");
            return null;
        } else {
            String result;
            try {
                InputStream inputStream = getAssets().open(jsonName);
                result = CommUtils.InputStreamToString(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return result;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

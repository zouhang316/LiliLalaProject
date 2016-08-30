package com.android.lala.base.adapter.layoutmanager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.FooterSpanSizeLookup;


/***
 * GridLayoutManger
 */
public class CustomGridLayoutManager extends GridLayoutManager implements ILayoutManager {

    public CustomGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastVisiblePosition() {
        return findLastVisibleItemPosition();
    }

    @Override
    public void setUpAdapter(BaseRecycleAdapter adapter) {
        FooterSpanSizeLookup lookup = new FooterSpanSizeLookup(adapter, getSpanCount());
        setSpanSizeLookup(lookup);
    }
}

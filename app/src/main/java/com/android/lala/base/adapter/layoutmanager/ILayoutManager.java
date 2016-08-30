package com.android.lala.base.adapter.layoutmanager;

import android.support.v7.widget.RecyclerView;

import com.android.lala.base.adapter.BaseRecycleAdapter;


/***
 *
 */
public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int findLastVisiblePosition();
    void setUpAdapter(BaseRecycleAdapter adapter);
}

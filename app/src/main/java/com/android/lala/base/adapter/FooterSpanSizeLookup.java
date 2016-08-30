package com.android.lala.base.adapter;

import android.support.v7.widget.GridLayoutManager;

public class FooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private BaseRecycleAdapter adapter;
    private int spanCount;

    public FooterSpanSizeLookup(BaseRecycleAdapter adapter, int spanCount) {
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isLoadMoreFooter(position) || adapter.isSectionHeader(position)) {
            return spanCount;
        }
        return 1;
    }
}

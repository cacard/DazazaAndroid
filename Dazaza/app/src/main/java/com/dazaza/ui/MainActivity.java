package com.dazaza.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.dazaza.R;
import com.dazaza.ui.adapter.MainAdapter;
import com.etsy.android.grid.StaggeredGridView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class MainActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        AdapterView.OnItemClickListener,
        AbsListView.OnScrollListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridView)
    StaggeredGridView gridView;

    private boolean hasLoadMore = false;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initListener();
        initAdapter();
    }

    private void initView() {

    }

    private void initAdapter() {
        adapter = new MainAdapter(this);
        if (gridView != null) {
            gridView.setAdapter(adapter);
        }
    }

    private void initListener() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this);
        }
        if (gridView != null) {
            gridView.setOnItemClickListener(this);
            gridView.setOnScrollListener(this);
        }
    }

    /**
     * 下拉刷新触发
     */
    @Override
    public void onRefresh() {
        log("->onRefresh()");

        // stop loading after some seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 5000);
    }

    /**
     * 瀑布流中每个项目的点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 瀑布流滑动状态改变事件
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * 瀑布流滑动事件
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!hasLoadMore) {
            if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                log("->will loading more...");
                hasLoadMore = true;
            }
        }
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }


}

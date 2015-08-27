package com.dazaza.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.dazaza.R;
import com.dazaza.api.ApiStoryFake;
import com.dazaza.config.Constants;
import com.dazaza.model.ModelStory;
import com.dazaza.ui.adapter.MainAdapter;
import com.dazaza.ui.view.MenuTopView;
import com.dazaza.ui.web.StoryActivity;
import com.etsy.android.grid.StaggeredGridView;

import java.util.List;

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
        log("->onCreate()");
        this.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initListener();
        initAdapter();
        startLoadingData();
    }

    private void initView() {
        final MenuTopView menuTopView = new MenuTopView(this);
        if (gridView != null) {
            gridView.addHeaderView(menuTopView);
        }
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
     * 从网络开始加载数据
     */
    private void startLoadingData() {
        showLoading();
        final List<ModelStory> list = ApiStoryFake.getStoryList("", 0);

        if (list != null && list.size() > 0) {
            adapter.setData(list);
            adapter.notifyDataSetChanged();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopLoading();
                }
            }, 3000);
        }
    }

    private void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }
        });
    }

    private void stopLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
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
                stopLoading();
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
        if (adapter != null) {
            final int headerCount = gridView.getHeaderViewsCount();
            if (position <= headerCount -1) {
                return;
            }
            final int p = position - headerCount;
            final ModelStory story = (ModelStory) adapter.getItem(p);
            if (story != null) {
                Intent intent = new Intent(MainActivity.this, StoryActivity.class);
                intent.putExtra(Constants.KEY_STORY_ID, story.getId());
                MainActivity.this.startActivity(intent);
            }
        }
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

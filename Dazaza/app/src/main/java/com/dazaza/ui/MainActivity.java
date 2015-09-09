package com.dazaza.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dazaza.R;
import com.dazaza.api.ApiStory;
import com.dazaza.api.ApiStoryFake;
import com.dazaza.config.Constants;
import com.dazaza.model.ModelStory;
import com.dazaza.system.MyApplication;
import com.dazaza.system.NetworkStatReceiver;
import com.dazaza.ui.adapter.MainAdapter;
import com.dazaza.ui.view.MenuTopView;
import com.dazaza.ui.web.StoryActivity;
import com.dazaza.utils.StoryListUtil;
import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class MainActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        AdapterView.OnItemClickListener,
        AbsListView.OnScrollListener,
        com.squareup.okhttp.Callback,
        NetworkStatReceiver.WifiStatChangeCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridView)
    StaggeredGridView gridView;

    private boolean isLoadingData = true;
    private MainAdapter adapter;
    private List<ModelStory> data;
    private byte[] lock4Data = new byte[0];
    private int nextPageIndex = 1;

    private MyHandler handler = new MyHandler(this);
    private static final int MSG_UPDATE_ADAPTER = 1;
    private int backCount = 0;
    private boolean backTwice = false;

    private NetworkStatReceiver networkStatReceiver;
    OkHttpClient client = new OkHttpClient();
    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("->onCreate()");
        this.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initListener();
        initAdapter();
        regReceiver();
        startLoadingData(1);
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
        adapter.notifyDataSetChanged();
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

    private void regReceiver() {
        if (networkStatReceiver == null) {
            networkStatReceiver = new NetworkStatReceiver(this);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkStatReceiver.ACTION);
        this.registerReceiver(networkStatReceiver, filter);
    }

    private void unregReceiver() {
        if (networkStatReceiver != null) {
            try {
                this.unregisterReceiver(networkStatReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void networkStateChanged(boolean isAvialble, int type) {
        log("->networkStateChanged(),isAvialble:" + isAvialble + "/type:" + type);
        if (!isAvialble) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.network_invaliable), Toast.LENGTH_SHORT).show();
            if (call != null && call.isCanceled() == false) {
                call.cancel();
                stopLoading();
            }
        } else {

        }
    }

    private static class MyHandler extends Handler {

        private WeakReference<MainActivity> ref;

        public MyHandler(MainActivity activity) {
            if (activity != null) {
                ref = new WeakReference<MainActivity>(activity);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            if (ref == null) {
                return;
            }

            MainActivity activity = ref.get();
            if (activity == null) {
                return;
            }

            switch (msg.what) {
                case MSG_UPDATE_ADAPTER:
                    if (activity.adapter != null) {
                        activity.adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    /**
     * 从网络开始加载数据
     */
    private void startLoadingData(int pageIndex) {
        isLoadingData = true;
        showLoading();

        Request request = ApiStory.getRquest4StoryList(pageIndex);
        if (request != null) {
            call = client.newCall(request);
            call.enqueue(this);
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        showLoadingError();
    }

    @Override
    public void onResponse(Response response) throws IOException {
        stopLoading();
        isLoadingData = false;

        if (response == null) {
            return;
        }

        if (!response.isSuccessful()) {
            showLoadingError();
        } else {
            final String body = response.body().string();
            if (body != null && body.length() > 0) {
                final List<ModelStory> list = new Gson().fromJson(body, new TypeToken<ArrayList<ModelStory>>() {
                }.getType());
                synchronized (lock4Data) {
                    if (nextPageIndex == 1) {// when 1st page, no need merge
                        data = list;
                    } else {
                        data = StoryListUtil.merge(data, list);
                    }
                    adapter.setData(data);
                    handler.sendEmptyMessage(MSG_UPDATE_ADAPTER);
                }

                nextPageIndex++;
            }
        }
    }

    private void showLoadingError() {

    }

    private void startLoadingDataFromFake() {
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
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 下拉刷新触发
     */
    @Override
    public void onRefresh() {
        startLoadingData(1);
    }

    /**
     * 瀑布流中每个项目的点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (adapter != null) {
            final int headerCount = gridView.getHeaderViewsCount();
            if (position <= headerCount - 1) {
                return;
            }
            final int p = position - headerCount;
            final ModelStory story = (ModelStory) adapter.getItem(p);
            if (story != null) {
                Intent intent = new Intent(MainActivity.this, StoryActivity.class);
                intent.putExtra(Constants.KEY_STORY_ID, story.getId());
                intent.putExtra(Constants.KEY_MODEL_STORY, (Parcelable) story);
                MainActivity.this.startActivity(intent);
            }
        }
    }

    /**
     * 瀑布流滑动状态改变事件
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * 瀑布流滑动事件
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        log("1st:" + firstVisibleItem + "/visibleCount:" + visibleItemCount + "/total:" + totalItemCount);
        if (!isLoadingData) {
            if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                log("->will loading more...");
                isLoadingData = true;
                startLoadingData(nextPageIndex + 1);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        unregReceiver();
    }

    @Override
    public void onBackPressed() {
        if (backTwice) {
            quite();
            return;
        }

        backTwice = true;
        Toast.makeText(this, getResources().getString(R.string.back_again_tips), Toast.LENGTH_SHORT).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                backTwice = false;
            }
        }, 2000);
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}

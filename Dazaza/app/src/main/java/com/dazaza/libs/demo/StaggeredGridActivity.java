package com.dazaza.libs.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.dazaza.R;
import com.etsy.android.grid.StaggeredGridView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class StaggeredGridActivity extends Activity {

    private static final String TAG = StaggeredGridActivity.class.getSimpleName();
    private boolean hasLoadMore = false;

    @Bind(R.id.grid_view)
    StaggeredGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_demo_staggered_grid);
        ButterKnife.bind(this);

        StaggeredGridAdapter adapter = new StaggeredGridAdapter(this);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                log("->setOnItemClickListener()");
            }
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!hasLoadMore) {
                    if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                        log("->will loading more...");
                        hasLoadMore = true;
                    }
                }
            }
        });
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}

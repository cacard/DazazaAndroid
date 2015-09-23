package com.dazaza.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dazaza.R;
import com.dazaza.ui.adapter.Main2ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/27.
 */
public class MainActivity2 extends BaseActivity {

    private static final String TAG = MainActivity2.class.getSimpleName();

    @Bind(R.id.toolBar)
    Toolbar toolbar;

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private Main2ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        if (toolbar != null) {
            this.setSupportActionBar(toolbar);
            initToolBar();
        }

        initTabLayout();
        initViewPager();
    }

    private void initTabLayout() {
        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab().setText("tab1"));
            tabLayout.addTab(tabLayout.newTab().setText("tab1"));
            tabLayout.addTab(tabLayout.newTab().setText("tab1"));
            tabLayout.addTab(tabLayout.newTab().setText("tab1"));
            tabLayout.addTab(tabLayout.newTab().setText("tab1"));
            tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        }
    }

    private void initViewPager() {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
    }

    private void initToolBar() {
        // navigation button
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_add);

        // title
        toolbar.setTitle("Title");

        // Subtitle
        toolbar.setSubtitle("Subtitle");

        // logo
        toolbar.setLogo(android.R.drawable.ic_menu_compass);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}

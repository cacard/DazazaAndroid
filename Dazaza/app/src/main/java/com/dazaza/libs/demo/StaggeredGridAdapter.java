package com.dazaza.libs.demo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dazaza.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class StaggeredGridAdapter extends BaseAdapter {

    private Context context;
    private List<String> data;
    private List<Integer> colors;

    public StaggeredGridAdapter(Context context) {
        this.context = context;

        data = new ArrayList<String>();
        data.add("這是\n\n測試，繁體\n字\n體測試");
        data.add("123");
        data.add("這是\r\n測試，繁體字體測試");
        data.add("這是測試，繁體\n字體測試。這是測試，繁體字體測試\n\n\n這是測試，繁體\n\n\n\n\n字體測試這是測試，繁體字體測試");
        data.add("這是測試，繁體字體測\n\n\n\n\n\n\n試");
        data.add("這是\n測試，繁體\n\n字體\n測試");
        data.add("這是\n\n測試，繁體字\n\n\n\n體測試");
        data.add("這是測試，繁\n體字\n\n\n體測試");
        data.add("這是測試，繁\n\n\n體字體測試");
        data.add("這是\n測試\n，繁體\n\n\n\n\n字\n\n\n\n\n\n\n\n體測試");
        data.add("這是\n測試\n，繁體\n\n\n\n\n字\n\n\n\n\n\n\n\n體測試");
        data.add("這是\n測試\n，繁體\n\n\n\n\n字\n\n\n\n\n\n\n\n體測試");
        data.add("這是\n測試\n，繁體\n\n\n\n\n字\n\n\n\n\n\n\n\n體測試");
        data.add("這是\n測試\n，繁體\n\n\n\n\n字\n\n\n\n\n\n\n\n體測試");


        colors = new ArrayList<Integer>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.GREEN);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_demo_staggered_grid_item,null);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        if (tvTitle != null) {
            tvTitle.setText(data.get(position));
            tvTitle.setBackgroundColor(colors.get(new Random().nextInt(colors.size())));
        }

        return convertView;
    }
}

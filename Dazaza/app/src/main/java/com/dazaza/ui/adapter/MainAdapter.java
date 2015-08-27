package com.dazaza.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dazaza.R;
import com.dazaza.model.ModelStory;
import com.dazaza.utils.StoryUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class MainAdapter extends BaseAdapter {

    private Context context;
    private List<ModelStory> data = new ArrayList<ModelStory>();

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ModelStory> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (data != null && data.size() > 0 && position >= 0 && position <= data.size() - 1) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main_grid_item, null);
            if (convertView != null) {
                holder = new ViewHolder();
                holder.img = (SimpleDraweeView) convertView.findViewById(R.id.img);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (holder != null) {
            final ModelStory modelStory = data.get(position);
            if (modelStory != null) {
                if (modelStory.getListThumbUrl() != null && modelStory.getListThumbUrl().length() > 0) {
                    holder.img.setImageURI(Uri.parse(modelStory.getListThumbUrl()));
                }
                if (modelStory.getTitle() != null) {
                    holder.tvTitle.setText(modelStory.getTitle());
                }
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        SimpleDraweeView img;
        TextView tvTitle;
        TextView tvNote;
    }
}

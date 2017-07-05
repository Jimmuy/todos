package com.jimmy.todos.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jimmy.todos.Model.HomeListItem;
import com.jimmy.todos.R;
import com.jimmy.todos.databinding.HomeListBinding;

import java.util.ArrayList;

/**
 * Created by Jimmy on 2017/7/5.
 */

public class HomeListAdapter extends BaseAdapter {
    private ArrayList<HomeListItem> data = new ArrayList<>();
    private Context context;

    public HomeListAdapter(Context context) {
        this.context = context;
    }

    public void setData(Context context, ArrayList<HomeListItem> data) {
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HomeListItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeListBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_home_list, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (HomeListBinding) convertView.getTag();
        }
        return binding.getRoot();
    }
}

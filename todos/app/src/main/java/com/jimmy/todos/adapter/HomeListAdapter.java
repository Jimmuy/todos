package com.jimmy.todos.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
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

    public void setData(ArrayList<HomeListItem> data) {
        this.data = data;

    }

    public ArrayList<HomeListItem> getData() {
        return data;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HomeListBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_home_list, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (HomeListBinding) convertView.getTag();
        }
        final HomeListItem item = data.get(position);
        binding.tvItem.setText(item.name);
        if (item.isDone) {
            binding.tvItem.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            binding.ivItem.setImageDrawable(context.getResources().getDrawable(R.mipmap.check));
            binding.tvItem.getPaint().setAntiAlias(true);
        } else {
            binding.tvItem.getPaint().setFlags(0);
            binding.ivItem.setImageDrawable(context.getResources().getDrawable(R.mipmap.unchecked));
            binding.tvItem.getPaint().setAntiAlias(true);
        }
        final HomeListBinding finalBinding = binding;

        binding.ivItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.isDone = !item.isDone;
                notifyDataSetChanged();
            }
        });
        binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (finalBinding.ivDelete.getVisibility() == View.VISIBLE) {

                    finalBinding.ivDelete.setVisibility(View.INVISIBLE);
                } else {

                    finalBinding.ivDelete.setVisibility(View.VISIBLE);
                }
                notifyDataSetChanged();
                return true;
            }
        });
        binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                finalBinding.ivDelete.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }
}

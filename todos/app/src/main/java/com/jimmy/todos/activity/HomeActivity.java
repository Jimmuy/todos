package com.jimmy.todos.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.jimmy.todos.Model.HomeListItem;
import com.jimmy.todos.R;
import com.jimmy.todos.adapter.HomeListAdapter;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.HomeBinding;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private HomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        HomeListAdapter adapter = new HomeListAdapter(this);
        ArrayList<HomeListItem> data = new ArrayList<HomeListItem>();
        for (int i = 0; i < 4; i++) {
            HomeListItem item = new HomeListItem();
            item.isDone = true;
            item.name = "wowowo" + i;
            data.add(item);
        }
        adapter.setData(data);
        binding.lvHome.setAdapter(adapter);

    }


}

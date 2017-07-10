package com.jimmy.todos.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jimmy.todos.Model.HomeListItem;
import com.jimmy.todos.R;
import com.jimmy.todos.adapter.HomeListAdapter;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.HomeBinding;
import com.jimmy.todos.utils.PreferenceUtils;
import com.jimmy.todos.widget.RightButtonEditText;

import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends BaseActivity {
    private static final String TODO_LIST = "todo_list";

    private HomeBinding binding;
    private ArrayList<HomeListItem> data = new ArrayList<HomeListItem>();
    private HomeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        adapter = new HomeListAdapter(this);
        String prefString = PreferenceUtils.getPrefString(this, TODO_LIST, "");
        if (!TextUtils.isEmpty(prefString)) {
            data = new Gson().fromJson(prefString, new TypeToken<ArrayList<HomeListItem>>() {
            }.getType());
        }
        adapter.setData(data);
        binding.lvHome.setAdapter(adapter);
        binding.etHome.setOnClickDrawableListener(new RightButtonEditText.OnClickDrawableListener() {
            @Override
            public void onClick() {
                //点击图片将今天的待办事项加入到列表中
                addTodos(binding.etHome.getText().toString().trim(), false);
                adapter.notifyDataSetChanged();
                binding.etHome.setText("");
            }
        });
    }

    private void addTodos(String todoText, boolean isDone) {
        HomeListItem item = new HomeListItem();
        item.isDone = isDone;
        item.name = todoText;
        item.createTime = new Date().getTime();
        data.add(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        PreferenceUtils.setPrefString(this, TODO_LIST, new Gson().toJson(adapter.getData()));
    }
}

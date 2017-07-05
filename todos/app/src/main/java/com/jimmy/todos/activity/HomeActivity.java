package com.jimmy.todos.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.HomeBinding;

public class HomeActivity extends BaseActivity{

    private HomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }

    @Override
    public void onBackPressed() {

    }

}

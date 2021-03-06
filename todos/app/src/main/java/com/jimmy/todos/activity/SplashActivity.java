package com.jimmy.todos.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.SplashBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jimmy on 2017/7/4.
 */

public class SplashActivity extends BaseActivity {

    private SplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initAnimation();
    }

    private void initAnimation() {
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("alpha", 0.2f, 0.6f, 1.0f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 0.6f, 1.0f, 1.3f, 1.0f);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 0.6f, 1.0f, 1.3f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(binding.ivSplashIcon, p1, p2, p3).setDuration(1000);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                naviToHome();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void naviToHome() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
    }

    @Override
    public void onBackPressed() {
        //不让用户退出
    }
}

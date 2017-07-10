package com.jimmy.todos.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.jimmy.todos.R;
import com.jimmy.todos.databinding.RefreshViewBinding;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/10     jimmy       v1.0.0        create
 **/

public class Refreshing extends RelativeLayout {
    private Context context;
    private RefreshViewBinding binding;
    private int index = 0;
    private int[] params;
    private boolean needChange = true;

    public Refreshing(Context context) {
        this(context, null);
    }

    public Refreshing(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Refreshing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_refresh, this, false);
        addView(binding.getRoot());
        params = new int[3];
        params[0] = R.drawable.ball;
        params[1] = R.drawable.icon;
        params[2] = R.drawable.ic_menu_gallery;
        start();
    }

    private void start() {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 1.5f);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                index = index + 1;
                if (index % params.length == 0) {
                    index = 0;
                }
                if (needChange) {
                    binding.ivBall.setImageDrawable(getResources().getDrawable(params[index]));
                    needChange = false;
                } else {
                    needChange = !needChange;
                }
            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float animatedValue = (float) animation.getAnimatedValue();
                binding.ivShadow.setScaleX(animatedValue);
                binding.ivShadow.setScaleY(animatedValue * 1.2f);
                binding.ivBall.setTranslationY(200 * (animatedValue - 1));
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(600).start();


    }
}

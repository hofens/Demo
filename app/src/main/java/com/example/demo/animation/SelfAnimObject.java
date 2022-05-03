package com.example.demo.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public class SelfAnimObject {

    private final View view;
    private final float translationX;
    private final float translationY;
    private final float scale;
    private TimeInterpolator interpolator;


    private SelfAnimObject(Builder builder) {
        this.view = builder.view;

        this.translationX = builder.translationX;
        this.translationY = builder.translationY;
        this.scale = builder.scale;
        this.interpolator = builder.interpolator;
    }

    public void startAnim() {

        ObjectAnimator transAnimX = ObjectAnimator.ofFloat(view, "translationX", 0, translationX);
        ObjectAnimator transAnimY = ObjectAnimator.ofFloat(view, "translationY", 0, translationY);
        ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(view, "scaleX", 1, scale);
        ObjectAnimator scaleAnimY = ObjectAnimator.ofFloat(view, "scaleY", 1, scale);
        scaleAnimX.setInterpolator(interpolator);
        scaleAnimY.setInterpolator(interpolator);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(transAnimX, transAnimY, scaleAnimX, scaleAnimY);
        animatorSet.start();


//        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "scaleX",scale + 0.3F, scale);
//        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "scaleY", scale + 0.3F, scale);
//        AnimatorSet animatorSet2 = new AnimatorSet();
//        animatorSet2.setDuration(200);
//        animatorSet2.playTogether(animatorX, animatorY);
//        animatorSet2.start();
//        animatorSet2.setStartDelay(1000);

    }

    public static class Builder {

        private View view;
        private float translationX = 0;
        private float translationY = 0;
        private float scale = 1F;
        private TimeInterpolator interpolator = new LinearInterpolator();


        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        /**
         * 从左往右移动
         */
        public Builder leftToRight(float distance) {
            this.translationX = distance;
            return this;
        }

        /**
         * 从右往左移动
         */
        public Builder rightToLeft(float distance) {
            this.translationX = -distance;
            return this;
        }


        /**
         * 从上往下移动
         */
        public Builder topToBottom(float distance) {
            this.translationY = distance;
            return this;
        }

        /**
         * 从下往上移动
         */
        public Builder bottomToTop(float distance) {
            this.translationY = -distance;
            return this;
        }


        /**
         * 缩放
         */
        public Builder scale(float scale) {
            this.scale = scale;
            return this;
        }

        /**
         * 插值器
         */
        public Builder interpolator(TimeInterpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public SelfAnimObject build() {
            return new SelfAnimObject(this);
        }
    }

}

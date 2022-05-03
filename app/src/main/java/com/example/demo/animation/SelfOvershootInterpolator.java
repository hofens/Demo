package com.example.demo.animation;

import android.view.animation.OvershootInterpolator;

public class SelfOvershootInterpolator extends OvershootInterpolator {


    @Override
    public float getInterpolation(float t) {
        t *= 1.1226f;
        if (t < 0.9f)
            return bounce(t);
        else
            return bounce(t - 1.0435f) + 0.95f;
    }

    private static float bounce(float t) {
        return t * t * 1.6f;
    }
}

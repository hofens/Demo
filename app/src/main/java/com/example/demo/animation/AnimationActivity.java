package com.example.demo.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.demo.R;

public class AnimationActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = ((TextView) v).getText();
                Log.i("动画", "点击 " + text);
            }
        };
        textView1.setOnClickListener(onClickListener);
        textView2.setOnClickListener(onClickListener);


    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                float radius1 = textView1.getWidth() * 1F / 2;
                float pointX1 = radius1 + textView1.getX();
                float pointY1 = radius1 + textView1.getY();

                float radius2 = textView2.getWidth() * 1F / 2;
                float pointX2 = radius2 + textView2.getX();
                float pointY2 = radius2 + textView2.getY();

                // 根据圆心距进行移动
                float distanceX = pointX2-pointX1;
                float distanceY = pointY2-pointY1;

                new SelfAnimObject.Builder()
                        .setView(textView1)
                        .leftToRight(distanceX)
                        .topToBottom(distanceY)
                        .scale(radius2 /radius1)
                        .build()
                        .startAnim();

                new SelfAnimObject.Builder()
                        .setView(textView2)
                        .rightToLeft(distanceX)
                        .bottomToTop(distanceY)
                        .interpolator(new SelfOvershootInterpolator())
                        .scale(radius1 / radius2)
                        .build()
                        .startAnim();
            }
        }, 200);

    }


    private void startSet() {

        new SelfAnimObject.Builder()
                .setView(textView1)
                .leftToRight(200)
                .topToBottom(200)
                .scale(1.5F)
                .build()
                .startAnim();

    }


}
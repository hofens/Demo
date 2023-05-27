package com.example.demo.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;

public class ViewAnimation extends AppCompatActivity {

    ImageView textView1;
    ImageView textView2;
    ImageView copyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        copyView = findViewById(R.id.copyview);


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
//                copyByCanvas();
//                showCenterView();
                addView();
            }
        }, 200);

    }

    private void extracted() {
        float radius1 = copyView.getWidth() * 1F / 2;
        float pointX1 = radius1 + copyView.getX();
        float pointY1 = radius1 + copyView.getY();

        float radius2 = textView2.getWidth() * 1F / 2;
        float pointX2 = radius2 + textView2.getX();
        float pointY2 = radius2 + textView2.getY();

        // 根据圆心距进行移动
        float distanceX = pointX2-pointX1;
        float distanceY = pointY2-pointY1;

        new SelfAnimObject.Builder()
                .setView(copyView)
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

    private void copyByCanvas(ImageView view) {
        int width = textView1.getWidth();
        int height = textView1.getHeight();
        Log.i("动画 ", " 参照物 " + width +", " + height);
        Bitmap bp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bp);
        textView1.draw(canvas);
        canvas.save();
        view.setBackground(new BitmapDrawable(bp));
        Log.i("动画 ", " 复制 " + bp.getWidth() +", " + bp.getHeight());
    }


    public void showCenterView() {

        ViewGroup mRootView = findViewById(R.id.container_anim);

        Rect anchorRect = new Rect();
        Rect rootViewRect = new Rect();

        textView1.getGlobalVisibleRect(anchorRect);
        mRootView.getGlobalVisibleRect(rootViewRect);

        // 调整显示区域大小
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) copyView.getLayoutParams();
        params.width = textView1.getWidth();
        params.height = textView1.getWidth();
        copyView.setLayoutParams(params);

        // 设置居中显示
        copyView.setY(anchorRect.top - rootViewRect.top + (textView1.getHeight() - 100) / 2);
        copyView.setX(anchorRect.left + (textView1.getWidth()  - 100) / 2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                extracted();

            }
        }, 2000);

    }

    public void addView() {

        ViewGroup mRootView = findViewById(R.id.container_anim);

        Rect anchorRect = new Rect();
        Rect rootViewRect = new Rect();

        textView1.getGlobalVisibleRect(anchorRect);
        mRootView.getGlobalVisibleRect(rootViewRect);

        // 调整显示区域大小
        ImageView tempView = new ImageView(this);
        copyByCanvas(tempView);
        // 在ConstraintLayout中怎么去添加一个控件，动态添加代码就怎么写
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(textView1.getWidth(), textView1.getHeight());
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;

        Log.i("动画 ", " " + anchorRect.left +", " + anchorRect.top + ", " + anchorRect.bottom +", " + anchorRect.right);
        Log.i("动画 ", " " + rootViewRect.left +", " + rootViewRect.top + ", " + rootViewRect.bottom +", " + rootViewRect.right);
        params.setMargins(anchorRect.left, anchorRect.top - rootViewRect.top, anchorRect.right, anchorRect.bottom);


        Log.i("动画 ", " " + params.leftMargin +", " + params.topMargin + ", " + params.bottomMargin +", " + params.rightMargin);
        mRootView.addView(tempView, params);


        // 设置居中显示
//        copyView.setY(anchorRect.top - rootViewRect.top + (textView1.getHeight() - 100) / 2);
//        copyView.setX(anchorRect.left + (textView1.getWidth()  - 100) / 2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                float radius1 = tempView.getWidth() * 1F / 2;
                float pointX1 = radius1 + tempView.getX();
                float pointY1 = radius1 + tempView.getY();

                float radius2 = textView2.getWidth() * 1F / 2;
                float pointX2 = radius2 + textView2.getX();
                float pointY2 = radius2 + textView2.getY();

                // 根据圆心距进行移动
                float distanceX = pointX2-pointX1;
                float distanceY = pointY2-pointY1;

                new SelfAnimObject.Builder()
                        .setView(tempView)
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
        }, 2000);

    }


}
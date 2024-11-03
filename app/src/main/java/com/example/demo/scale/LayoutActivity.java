package com.example.demo.scale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.demo.R;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        constrainAddView();
    }

    private void constrainAddView() {

        /* ---- 先在parent_constrain添加下ImageView */
        // 先找到要添加ImageView的爸爸容器
        ConstraintLayout parentConstraintLayout = findViewById(R.id.parent_constrain);
        // new一个ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getDrawable(R.mipmap.ic_launcher));
        // 因为子View要添加到ConstraintLayout中，所以是new ConstraintLayout.LayoutParams，设定子view的大小为50,50
        ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(dpToPx(50), dpToPx(50));
        // 设定边距
        param.leftMargin = dpToPx(100);
        param.topMargin = dpToPx(10);
        // 因为爸爸是ConstraintLayout噢，所以还得加上相对爸爸
        param.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        param.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        // 相当于把ImageView以param参数添加到ConstraintLayout中
        parentConstraintLayout.addView(imageView, param);

        /* ---- 别忘了还要把爸爸往下偏移40dp */
        // 因为parentConstraintLayout的爸爸也是ConstraintLayout，所以getLayoutParams()获取的类型是ConstraintLayout.LayoutParams
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) parentConstraintLayout.getLayoutParams();
        // 设置偏移
        layoutParams.topMargin = dpToPx(40);
        // 重新把参数设置给parentConstraintLayout
        parentConstraintLayout.setLayoutParams(layoutParams);
    }

    private void linearAddView() {
        // 先找到要添加ImageView的爸爸容器
        LinearLayout parentLinear = findViewById(R.id.parent_linear);
        // new一个ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getDrawable(R.mipmap.ic_launcher));
        // 因为子View要添加到LinearLayout中，所以是new 一个LinearLayout.LayoutParams，设定子view的大小为50,50
        LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(dpToPx(50), dpToPx(50));
        // 设定边距
        linearParam.leftMargin = dpToPx(30);
        linearParam.topMargin = dpToPx(20);
        // 相当于把ImageView以linearParam参数添加到LinearLayout中
        parentLinear.addView(imageView, linearParam);
    }

    private int dpToPx(int dp){
        float scale=getResources().getDisplayMetrics().density;
        return (int)(dp*scale+0.5f);
    }
}



package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.demo.scroll.DateBean;
import com.example.demo.scroll.HosGridViewAdapter;

import java.util.ArrayList;

public class ScrollActivity extends AppCompatActivity {
    private ArrayList<DateBean> list;
    private RecyclerView rvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        rvTitle = findViewById(R.id.rv_title);


        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new DateBean("日期" + i, "事件" + i));
        }
        rvTitle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HosGridViewAdapter hosGridViewAdapter = new HosGridViewAdapter(list, ScrollActivity.this);
        rvTitle.setAdapter(hosGridViewAdapter);
        hosGridViewAdapter.setOnHosGridItemClick(new HosGridViewAdapter.OnRvItemClick() {
            @Override
            public void OnRvItemClick(int position, View view) {

            }
        });
    }
}
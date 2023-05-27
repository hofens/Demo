package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.fragment.FirstFragment;
import com.example.demo.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity implements clickListener {
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private Button previous;
    private Button next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous= (Button) findViewById(R.id.Btn_Previous);
        next= (Button) findViewById(R.id.Btn_Next);

        firstFragment=new FirstFragment(this);
        secondFragment=new SecondFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.Container,firstFragment)
                .commitAllowingStateLoss();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .popBackStack();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_right_in,
                                R.anim.slide_left_out,
                                R.anim.slide_left_in,
                                R.anim.slide_right_out
                        ).replace(R.id.Container,secondFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });


    }

    @Override
    public void onClick(int id) {
        if (id == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    ).replace(R.id.Container,secondFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }


}
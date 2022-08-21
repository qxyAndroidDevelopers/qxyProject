package com.qxy.helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qxy.helloword.List.TelActivity;

public class MainActivity extends AppCompatActivity {

    private Button movieButton,telButton,varietyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    private void initView() {
        movieButton=findViewById(R.id.movie);
        varietyButton=findViewById(R.id.variety);
        telButton=findViewById(R.id.tel);
    }

    private void initEvent() {
        Intent intent=new Intent();
        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this, TelActivity.class);
                startActivity(intent);
            }
        });

        telButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this, TelActivity.class);
                startActivity(intent);
            }
        });

        varietyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this, TelActivity.class);
                startActivity(intent);
            }
        });
    }

}
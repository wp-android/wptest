package com.example.wptest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    TextView text;
    List<String> time = new ArrayList<>();
    List<String> time1 = new ArrayList<>();
    List<String> time2 = new ArrayList<>();
    String[] strings = new String[]{"0", "1", "2", "3"};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Activity1);
        linearLayout = findViewById(R.id.linearlayout);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                linearLayout.removeAllViews();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        time = new Helper().getLightTime("TrafficLightId", 1,MainActivity.ip);
                        time1 = new Helper().getLightTime("TrafficLightId", 2,MainActivity.ip);
                        time2 = new Helper().getLightTime("TrafficLightId", 3,MainActivity.ip);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (i = 1; i < 4; i++) {
                                    LinearLayout layout = new LinearLayout(Activity1.this);
                                    layout.setOrientation(LinearLayout.HORIZONTAL);
                                    for (int j = 0; j < 4; j++) {
                                        text = new TextView(Activity1.this);
                                        text.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                                        if (j == 0) {
                                            text.setText(strings[i]);
                                        }else if (i==1){
                                            text.setText(time.get(j-1));
                                        }else if (i==2){
                                            text.setText(time1.get(j-1));
                                        }else if (i==3){
                                            text.setText(time2.get(j-1));
                                        }
                                        text.setGravity(Gravity.CENTER);
                                        layout.addView(text);
                                        TextView reit = new TextView(Activity1.this);
                                        //这个Textview画表格竖线
                                        reit.setLayoutParams(new LinearLayout.LayoutParams(2,
                                                LinearLayout.LayoutParams.WRAP_CONTENT));
                                        //设置Textview宽为2dp，高为不确定
                                        reit.setBackgroundColor(Color.BLACK);
                                        layout.addView(reit);
                                    }
                                    TextView reit = new TextView(Activity1.this);
                                    //这个Textview用来画横线,
                                    reit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
                                    reit.setBackgroundColor(Color.BLACK);
                                    linearLayout.addView(layout, LinearLayout.LayoutParams.MATCH_PARENT,
                                            LinearLayout.LayoutParams.WRAP_CONTENT);
                                    linearLayout.addView(reit);
                                }
                            }

                        });


                    }
                }).start();
        }
    }
}

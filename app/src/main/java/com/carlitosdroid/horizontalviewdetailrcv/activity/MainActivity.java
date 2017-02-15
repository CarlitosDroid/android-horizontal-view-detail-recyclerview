package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.carlitosdroid.horizontalviewdetailrcv.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btnRCVWithSnapHelper;
    private AppCompatButton btnRCVExtended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRCVWithSnapHelper = (AppCompatButton) findViewById(R.id.btnRCVWithSnapHelper);
        btnRCVExtended = (AppCompatButton) findViewById(R.id.btnRCVExtended);

        btnRCVWithSnapHelper.setOnClickListener(this);
        btnRCVExtended.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRCVWithSnapHelper){
         startActivity(new Intent(this, LinearSnapHelperActivity.class));
        }else{
            startActivity(new Intent(this, VerticalRCVActivity.class));
        }
    }
}

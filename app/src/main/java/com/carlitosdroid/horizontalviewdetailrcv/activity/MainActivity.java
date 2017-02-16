package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.carlitosdroid.horizontalviewdetailrcv.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btnOnlyHorizontalPSH;
    private AppCompatButton btnVerticalAndHorizontalPSH;
    private AppCompatButton btnMultipleOrientation;
    private AppCompatButton btnRCVExtended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOnlyHorizontalPSH = (AppCompatButton) findViewById(R.id.btnOnlyHorizontalPSH);
        btnVerticalAndHorizontalPSH = (AppCompatButton) findViewById(R.id.btnVerticalAndHorizontalPSH);
        btnMultipleOrientation = (AppCompatButton) findViewById(R.id.btnMultipleOrientation);
        btnRCVExtended = (AppCompatButton) findViewById(R.id.btnRCVExtended);

        btnOnlyHorizontalPSH.setOnClickListener(this);
        btnVerticalAndHorizontalPSH.setOnClickListener(this);
        btnMultipleOrientation.setOnClickListener(this);
        btnRCVExtended.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOnlyHorizontalPSH:
                startActivity(new Intent(this, PagerSnapHelperOnlyHActivity.class));
                break;
            case R.id.btnVerticalAndHorizontalPSH:
                startActivity(new Intent(this, PagerSnapHelperVActivity.class));
                break;
            case R.id.btnMultipleOrientation:
                startActivity(new Intent(this, MultipleOrientationSHActivity.class));
                break;
            case R.id.btnRCVExtended:
                startActivity(new Intent(this, VerticalRCVActivity.class));
                break;
        }

    }
}

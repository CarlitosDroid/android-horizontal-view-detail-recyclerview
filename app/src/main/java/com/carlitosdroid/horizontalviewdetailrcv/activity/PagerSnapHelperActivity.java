package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.Util.GravitySnapHelper;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.PagerSnapHelperAdapter;


import java.util.ArrayList;
import java.util.List;

public class PagerSnapHelperActivity extends AppCompatActivity implements GravitySnapHelper.SnapListener{

    RecyclerView rcvAnimals;
    private List<Object> objectList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    boolean mSnapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_snap_helper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        for (int i = 0; i < 10; i++) {
            objectList.add(new AnimalEntity(false, "item" + i));
        }
        addLoadingItem();

        PagerSnapHelperAdapter snapHelperAdapter = new PagerSnapHelperAdapter(this, objectList);
        rcvAnimals.setLayoutManager(linearLayoutManager);
        rcvAnimals.setAdapter(snapHelperAdapter);

        new PagerSnapHelper().attachToRecyclerView(rcvAnimals);
        //rcvAnimals.setOnFlingListener(null);

       // new GravitySnapHelper(Gravity.START, false, this).attachToRecyclerView(rcvAnimals);
        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //Log.e("SCROLL_STATE_SETTLING","SCROLL_STATE_SETTLING " +getSnappedPosition());
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    int position = getSnappedPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Log.e("SCROLL_STATE_IDLE","SCROLL_STATE_IDLE " + getSnappedPosition());
                    }
                }
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private int getSnappedPosition (){
        return linearLayoutManager.findFirstCompletelyVisibleItemPosition();
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    @Override
    public void onSnap(int position) {
        Log.e("FINAL POSITION ","FINAL POSITION  " + position);
    }
}

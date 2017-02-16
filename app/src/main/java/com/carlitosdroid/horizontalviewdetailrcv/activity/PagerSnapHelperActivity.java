package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.Util.GravitySnapHelper;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.PagerSnapHelperAdapter;


import java.util.ArrayList;
import java.util.List;

public class PagerSnapHelperActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcvAnimals;
    private List<Object> objectList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private AppCompatImageView imgBack;
    private AppCompatImageView imgFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_snap_helper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgBack = (AppCompatImageView) findViewById(R.id.imgBack);
        imgFavorite = (AppCompatImageView) findViewById(R.id.imgFavorite);
        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        for (int i = 0; i < 10; i++) {
            if (i==2 || i==5 || i==9) {
                objectList.add(new AnimalEntity(false, "item" + i));
            }else{
                objectList.add(new AnimalEntity(true, "item" + i));
            }
        }
        addLoadingItem();

        PagerSnapHelperAdapter snapHelperAdapter = new PagerSnapHelperAdapter(this, objectList);
        rcvAnimals.setLayoutManager(linearLayoutManager);
        rcvAnimals.setAdapter(snapHelperAdapter);

        new PagerSnapHelper().attachToRecyclerView(rcvAnimals);

        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = getSnappedPosition();
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //Log.e("ONSCROLLSTATECHANGED", "SCROLL_STATE_SETTLING " + position);
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (position != RecyclerView.NO_POSITION) {
                        //Log.e("ONSCROLLSTATECHANGED", "SCROLL_STATE_IDLE " + position);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = getSnappedPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.e("ONSCROLLED", "ONSCROLLED " + position);
                    if (objectList.get(position) instanceof AnimalEntity) {
                        imgFavorite.setVisibility(View.VISIBLE);
                        AnimalEntity animalEntity = (AnimalEntity) objectList.get(position);

                        if (animalEntity.isFavorite()) {
                            imgFavorite.setImageResource(R.drawable.ic_favorite_red_500_24dp);
                        } else {
                            imgFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                        }
                    }else if(objectList.get(position) instanceof LoadingEntity){
                        imgFavorite.setVisibility(View.GONE);
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

        imgBack.setOnClickListener(this);
        imgFavorite.setOnClickListener(this);

    }

    private int getSnappedPosition() {
        return linearLayoutManager.findFirstCompletelyVisibleItemPosition();
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgFavorite:
                int position = getSnappedPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.e("POSITION CLICKED","POSITION CLICKED " + position);
                }
                break;
        }
    }
}

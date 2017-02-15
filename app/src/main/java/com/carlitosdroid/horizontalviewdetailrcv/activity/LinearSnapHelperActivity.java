package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.JuryEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.SnapAdapter;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.VerticalAdapter;

import java.util.ArrayList;
import java.util.List;

public class LinearSnapHelperActivity extends AppCompatActivity {

    RecyclerView rcvAnimals;
    private SnapAdapter snapAdapter;
    private List<Object> objectList = new ArrayList<>();
    private List<AnimalEntity> animalEntities = new ArrayList<>();
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_snap_helper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        for (int i = 0; i < 10; i++) {
            animalEntities.add(new AnimalEntity(false, "item" + i));
        }
        objectList.add(new JuryEntity("HOLAAA", animalEntities));
        addLoadingItem();

        layoutManager = new LinearLayoutManager(this);
        //when you want horizontal
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        snapAdapter = new SnapAdapter(this, objectList);
        rcvAnimals.setLayoutManager(layoutManager);
        rcvAnimals.setAdapter(snapAdapter);

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }


}

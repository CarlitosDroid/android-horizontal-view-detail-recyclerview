package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.JuryEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.MultipleOrientationAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultipleOrientationSnapHelperActivity extends AppCompatActivity {

    RecyclerView rcvAnimals;
    private MultipleOrientationAdapter multipleOrientationAdapter;
    private List<Object> objectList = new ArrayList<>();
    private List<AnimalEntity> animalEntities = new ArrayList<>();
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_orientation_snap_helper);
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

        multipleOrientationAdapter = new MultipleOrientationAdapter(this, objectList);
        rcvAnimals.setLayoutManager(layoutManager);
        rcvAnimals.setAdapter(multipleOrientationAdapter);

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

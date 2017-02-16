package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.os.AsyncTask;
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
import android.widget.Toast;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.PagerSnapHelperHAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerSnapHelperOnlyHActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcvAnimals;
    private List<Object> objectList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    int position = 0;
    PagerSnapHelperHAdapter snapHelperHAdapter;

    private AppCompatImageView imgBack;
    private AppCompatImageView imgFavorite;

    private int lastVisibleItem;
    private int totalItemCount;
    private final int visibleThreshold = 1;

    private boolean setLoading = false;
    private RequestMoreData requestMoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_snap_helper_only_h);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgBack = (AppCompatImageView) findViewById(R.id.imgBack);
        imgFavorite = (AppCompatImageView) findViewById(R.id.imgFavorite);
        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        loadAnimalsInitialData();
        addLoadingItem();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        snapHelperHAdapter = new PagerSnapHelperHAdapter(this, objectList);
        rcvAnimals.setLayoutManager(linearLayoutManager);
        rcvAnimals.setAdapter(snapHelperHAdapter);
        linearLayoutManager.scrollToPosition(position);

        new PagerSnapHelper().attachToRecyclerView(rcvAnimals);

        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    } else if (objectList.get(position) instanceof LoadingEntity) {
                        imgFavorite.setVisibility(View.GONE);
                    }
                }

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (!setLoading) {
                        setLoading = true;
                        requestMoreData = new RequestMoreData();
                        requestMoreData.execute();
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

    private void loadAnimalsInitialData(){
        for (int i = 0; i < 10; i++) {
            if (i==2 || i==5 || i==9) {
                objectList.add(new AnimalEntity(true, "item" + i));
            }else{
                objectList.add(new AnimalEntity(false, "item" + i));
            }
        }
    }

    private int getSnappedPosition() {
        return linearLayoutManager.findFirstCompletelyVisibleItemPosition();
    }

    private class RequestMoreData extends AsyncTask<Void, Void, Void> {

        private RequestMoreData() {
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(PagerSnapHelperOnlyHActivity.this, "Requesting...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            shouldRemoveLoadingItem();
            setLoading = false;
            int tempSize = objectList.size();
            for (int i = tempSize; i < (tempSize + 10); i++) {
                if (i==(tempSize+4) || i==(tempSize+8)) {
                    objectList.add(new AnimalEntity(true, "item" + i));
                }else{
                    objectList.add(new AnimalEntity(false, "item" + i));
                }
            }
            addLoadingItem();
            snapHelperHAdapter.notifyDataSetChanged();
        }
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    private void shouldRemoveLoadingItem() {
        if (objectList.get(objectList.size() - 1) instanceof LoadingEntity) {
            objectList.remove(objectList.size() - 1);
            snapHelperHAdapter.notifyItemRemoved(objectList.size());
        }
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
                    Toast.makeText(this, "item position " + position, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

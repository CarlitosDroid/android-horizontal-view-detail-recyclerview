package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.Util.GravitySnapHelper;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.PagerSnapHelperHAdapter;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.PagerSnapHelperVAdapter;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.VerticalAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagerSnapHelperVActivity extends AppCompatActivity {

    RecyclerView rcvAnimals;
    private PagerSnapHelperVAdapter snapHelperVAdapter;
    private List<Object> objectList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private int lastVisibleItem;
    private int totalItemCount;
    private final int visibleThreshold = 1;

    private boolean setLoading = false;

    private RequestMoreData requestMoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_snap_helper_v);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        for (int i = 0; i < 10; i++) {
            if (i == 3 || i == 7) {
                objectList.add(new AnimalEntity(true, "item" + i));
            } else {
                objectList.add(new AnimalEntity(false, "item" + i));
            }
        }
        addLoadingItem();


        snapHelperVAdapter = new PagerSnapHelperVAdapter(this, objectList);
        rcvAnimals.setLayoutManager(linearLayoutManager);
        rcvAnimals.setAdapter(snapHelperVAdapter);

        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

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
    }

    private class RequestMoreData extends AsyncTask<Void, Void, Void> {

        private RequestMoreData() {
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(PagerSnapHelperVActivity.this, "Requesting1...", Toast.LENGTH_SHORT).show();
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
                if (i == (tempSize + 4)|| i == (tempSize + 8)) {
                    objectList.add(new AnimalEntity(true, "item" + i));
                } else {
                    objectList.add(new AnimalEntity(false, "item" + i));
                }
            }
            addLoadingItem();
            snapHelperVAdapter.notifyDataSetChanged();
        }
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    private void shouldRemoveLoadingItem() {
        if (objectList.get(objectList.size() - 1) instanceof LoadingEntity) {
            objectList.remove(objectList.size() - 1);
            snapHelperVAdapter.notifyItemRemoved(objectList.size());
        }
    }

    private void checkRequestMoreData() {
        if (requestMoreData != null) {
            requestMoreData.cancel(true);
        }
    }

    public void changeImageAndroid(int position, boolean isFavorited) {
        ((AnimalEntity) objectList.get(position)).setFavorite(isFavorited);
        snapHelperVAdapter.notifyDataSetChanged();
    }

    public void goToDetail(int position) {
        checkRequestMoreData();

        Intent intent = new Intent(this, PagerSnapHelperHActivity.class);
        intent.putExtra("listObject", ((Serializable) objectList));
        intent.putExtra("position", position);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                setLoading = false;
                objectList.clear();
                objectList.addAll((List<Object>) data.getSerializableExtra("newList"));
                snapHelperVAdapter.notifyDataSetChanged();
                linearLayoutManager.scrollToPosition(data.getExtras().getInt("position"));
            }
        }
    }

}

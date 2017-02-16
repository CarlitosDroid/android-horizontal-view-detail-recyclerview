package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.SnappyRecyclerView;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.HorizontalAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HorizontalRCVActivity extends AppCompatActivity {

    SnappyRecyclerView rcvAnimals;
    private HorizontalAdapter horizontalAdapter;
    private List<Object> objectList = new ArrayList<>();
    int position = 0;

    LinearLayoutManager layoutManager;

    private int lastVisibleItem;
    private int totalItemCount;
    private final int visibleThreshold = 1;

    private boolean setLoading = false;
    private RequestMoreData requestMoreData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_rcv);
        rcvAnimals = (SnappyRecyclerView) findViewById(R.id.rcvAnimals);
        objectList.addAll((List<Object>) getIntent().getSerializableExtra("listObject"));
        position = getIntent().getExtras().getInt("position");

        layoutManager = new LinearLayoutManager(this);
        //when you want horizontal
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalAdapter = new HorizontalAdapter(this, objectList);
        rcvAnimals.setLayoutManager(layoutManager);
        rcvAnimals.setAdapter(horizontalAdapter);

        layoutManager.scrollToPosition(position);

        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (!setLoading) {
                        setLoading = true;
                        requestMoreData = new RequestMoreData();
                        requestMoreData.execute();
                    }
                }
            }
        });
    }

    private class RequestMoreData extends AsyncTask<Void, Void, Void> {

        private RequestMoreData() {
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(HorizontalRCVActivity.this, "Requesting...", Toast.LENGTH_SHORT).show();
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
            for (int i = tempSize ; i < (tempSize + 10); i++) {
                objectList.add(new AnimalEntity(false, "item" + i));
            }
            addLoadingItem();
            horizontalAdapter.notifyDataSetChanged();
        }
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    private void shouldRemoveLoadingItem() {
        if (objectList.get(objectList.size() - 1) instanceof LoadingEntity) {
            objectList.remove(objectList.size() - 1);
            horizontalAdapter.notifyItemRemoved(objectList.size());
        }
    }

    private void checkRequestMoreData(){
        if(requestMoreData != null){
            requestMoreData.cancel(true);
        }
    }

    public void changeImageAndroid(int position, boolean isFavorited){
        ((AnimalEntity)objectList.get(position)).setFavorite(isFavorited);
        horizontalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        checkRequestMoreData();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("newList", (Serializable) objectList);
        returnIntent.putExtra("position", layoutManager.findLastVisibleItemPosition());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}

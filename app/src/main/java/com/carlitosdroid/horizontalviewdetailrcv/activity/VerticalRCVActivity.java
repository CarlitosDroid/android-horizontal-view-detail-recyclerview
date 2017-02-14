package com.carlitosdroid.horizontalviewdetailrcv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;
import com.carlitosdroid.horizontalviewdetailrcv.view.adapter.VerticalAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VerticalRCVActivity extends AppCompatActivity{

    RecyclerView rcvAnimals;
    private VerticalAdapter verticalAdapter;
    private List<Object> objectList = new ArrayList<>();
    LinearLayoutManager layoutManager;

    private int lastVisibleItem;
    private int totalItemCount;
    private final int visibleThreshold = 1;

    private boolean setLoading = false;

    private RequestMoreData requestMoreData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_rcv);

        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        for (int i = 0; i < 10; i++) {
            objectList.add(new AnimalEntity(false, "item" + i));
        }
        addLoadingItem();

        layoutManager = new LinearLayoutManager(this);
        //when you want horizontal
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        verticalAdapter = new VerticalAdapter(this, objectList);
        rcvAnimals.setLayoutManager(layoutManager);
        rcvAnimals.setAdapter(verticalAdapter);

        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (totalItemCount <= (lastVisibleItem + visibleThreshold )) {
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
            Toast.makeText(VerticalRCVActivity.this, "Requesting...", Toast.LENGTH_SHORT).show();
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
            verticalAdapter.notifyDataSetChanged();
        }
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    private void shouldRemoveLoadingItem() {
        if (objectList.get(objectList.size() - 1) instanceof LoadingEntity) {
            objectList.remove(objectList.size() - 1);
            verticalAdapter.notifyItemRemoved(objectList.size());
        }
    }

    private void checkRequestMoreData(){
        if(requestMoreData != null){
            requestMoreData.cancel(true);
        }
    }

    public void changeImageAndroid(int position, boolean isFavorited) {
        ((AnimalEntity) objectList.get(position)).setIsfavorite(isFavorited);
        verticalAdapter.notifyDataSetChanged();
    }

    public void goToDetail(int position) {
        checkRequestMoreData();

        Intent intent = new Intent(this, HorizontalRCVActivity.class);
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
                verticalAdapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(data.getExtras().getInt("position"));
            }
        }
    }
}

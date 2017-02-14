package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.activity.HorizontalRCVActivity;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;

import java.util.List;


/**
 * Created by Carlos Vargas on 11/22/16.
 * CarlitosDroid
 */

public class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final HorizontalRCVActivity horizontalRCVActivity;
    private static LayoutInflater inflater = null;

    private List<Object> objectList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private static final int TYPE_ERROR = 2;

    public HorizontalAdapter(HorizontalRCVActivity horizontalRCVActivity, List<Object> objectList) {
        this.horizontalRCVActivity = horizontalRCVActivity;
        inflater = (LayoutInflater) horizontalRCVActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objectList = objectList;
    }


    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof AnimalEntity) {
            return TYPE_ITEM;
        } else if (objectList.get(position) instanceof LoadingEntity) {
            return TYPE_LOADING;
        } else {
            return TYPE_ERROR;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM:
                view = inflater.inflate(R.layout.item_horizontal, parent, false);
                return new ItemViewHolder(view);
            case TYPE_LOADING:
                view = inflater.inflate(R.layout.item_horizontal_progress, parent, false);
                return new LoadingViewHolder(view);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_ITEM:
                AnimalEntity animalEntity = (AnimalEntity) objectList.get(position);
                int imgBackground = animalEntity.isfavorite() ? R.drawable.ic_favorite_red_500_24dp : R.drawable.ic_favorite_grey_500_24dp;
                ((ItemViewHolder) holder).imgAndroid.setImageResource(imgBackground);
                ((ItemViewHolder) holder).lblTitle.setText(animalEntity.getTitle());
                break;
            case TYPE_LOADING:

                break;
            default:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView lblTitle;
        private AppCompatImageView imgAndroid;

        ItemViewHolder(View view) {
            super(view);
            lblTitle = (AppCompatTextView) view.findViewById(R.id.lblTitle);
            imgAndroid = (AppCompatImageView) view.findViewById(R.id.imgAndroid);

            lblTitle.setOnClickListener(this);
            imgAndroid.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgAndroid:
                    if (((AnimalEntity) objectList.get(getAdapterPosition())).isfavorite()) {
                        horizontalRCVActivity.changeImageAndroid(getAdapterPosition(), false);
                    } else {
                        horizontalRCVActivity.changeImageAndroid(getAdapterPosition(), true);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public int getAdapterPosition(){
        return getAdapterPosition();
    }
}

package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.activity.PagerSnapHelperVActivity;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/15/17.
 *
 */

public class PagerSnapHelperVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PagerSnapHelperVActivity pagerSnapHelperVActivity;
    private List<Object> objectList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private static final int TYPE_ERROR = 2;

    public PagerSnapHelperVAdapter(PagerSnapHelperVActivity pagerSnapHelperVActivity, List<Object> objectList) {
        this.pagerSnapHelperVActivity = pagerSnapHelperVActivity;
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
                return new SnapViewHolder(view);
            case TYPE_LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_progress, parent, false);
                return new LoadingViewHolder(view);
            case TYPE_ERROR:
                return null;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ITEM:
                AnimalEntity animalEntity = (AnimalEntity) objectList.get(position);
                ((SnapViewHolder) holder).lblTitle.setText(animalEntity.getTitle());
                if (animalEntity.isFavorite()) {
                    ((SnapViewHolder) holder).imgFavorite.setImageResource(R.drawable.ic_favorite_red_500_24dp);
                } else {
                    ((SnapViewHolder) holder).imgFavorite.setImageResource(R.drawable.ic_favorite_grey_500_24dp);
                }
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


    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        final ProgressBar pbLoading;

        LoadingViewHolder(View v) {
            super(v);
            pbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        }
    }

    private class SnapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatTextView lblTitle;
        AppCompatImageView imgFavorite;

        SnapViewHolder(View itemView) {
            super(itemView);
            lblTitle = (AppCompatTextView) itemView.findViewById(R.id.lblTitle);
            imgFavorite = (AppCompatImageView) itemView.findViewById(R.id.imgFavorite);
            lblTitle.setOnClickListener(this);
            imgFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lblTitle:
                    pagerSnapHelperVActivity.goToDetail(getAdapterPosition());
                    break;
                case R.id.imgFavorite:
                    if (((AnimalEntity) objectList.get(getAdapterPosition())).isFavorite()) {
                        pagerSnapHelperVActivity.changeImageAndroid(getAdapterPosition(), false);
                    } else {
                        pagerSnapHelperVActivity.changeImageAndroid(getAdapterPosition(), true);
                    }
                    break;
            }
        }
    }
}

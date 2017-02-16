package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.activity.VerticalRCVActivity;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;

import java.util.List;


/**
 * Created by Carlos Vargas on 11/22/16.
 * CarlitosDroid
 */

public class VerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final VerticalRCVActivity verticalRCVActivity;
    private static LayoutInflater inflater = null;

    private List<Object> objectList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private static final int TYPE_ERROR = 2;

    public VerticalAdapter(VerticalRCVActivity verticalRCVActivity, List<Object> objectList) {
        this.verticalRCVActivity = verticalRCVActivity;
        inflater = (LayoutInflater) verticalRCVActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objectList = objectList;
    }


    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof AnimalEntity) {
            return TYPE_ITEM;
        }else if(objectList.get(position) instanceof LoadingEntity){
            return TYPE_LOADING;
        }else {
            return TYPE_ERROR;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_ITEM:
                view = inflater.inflate(R.layout.item_vertical, parent, false);
                return new ItemViewHolder(view);
            case TYPE_LOADING:
                view = inflater.inflate(R.layout.item_vertical_progress, parent, false);
                return new LoadingViewHolder(view);
            case TYPE_ERROR:
                view = inflater.inflate(R.layout.item_vertical_error, parent, false);
                return new ErrorViewHolder(view);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ITEM:
                AnimalEntity animalEntity = (AnimalEntity) objectList.get(position);
                int imgBackground = animalEntity.isFavorite() ? R.drawable.ic_favorite_red_500_24dp : R.drawable.ic_favorite_grey_500_24dp;
                ((VerticalAdapter.ItemViewHolder) holder).imgFavorite.setImageResource(imgBackground);
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
        private final TextView lblTitle;
        private final AppCompatImageView imgFavorite;

        ItemViewHolder(View view) {
            super(view);
            lblTitle = (TextView) view.findViewById(R.id.lblTitle);
            imgFavorite = (AppCompatImageView) view.findViewById(R.id.imgFavorite);
            lblTitle.setOnClickListener(this);
            imgFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.lblTitle:
                    verticalRCVActivity.goToDetail(getAdapterPosition());
                    break;
                case R.id.imgAndroid:
                    if (((AnimalEntity) objectList.get(getAdapterPosition())).isFavorite()) {
                        verticalRCVActivity.changeImageAndroid(getAdapterPosition(), false);
                    } else {
                        verticalRCVActivity.changeImageAndroid(getAdapterPosition(), true);
                    }
                    break;
            }

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        final ProgressBar pbLoading;

        LoadingViewHolder(View v) {
            super(v);
            pbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        }
    }

    private class ErrorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView lblError;
        final Button btnError;

        ErrorViewHolder(View itemView) {
            super(itemView);
            lblError = (TextView) itemView.findViewById(R.id.lblError);
            btnError = (Button) itemView.findViewById(R.id.btnError);
            btnError.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnError:

                    break;
            }
        }
    }

}

package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.activity.PagerSnapHelperActivity;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/15/17.
 *
 */

public class PagerSnapHelperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private PagerSnapHelperActivity linearSnapHelperActivity;
    private List<Object> objectList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private static final int TYPE_ERROR = 2;

    public PagerSnapHelperAdapter(PagerSnapHelperActivity linearSnapHelperActivity, List<Object> objectList) {
        this.linearSnapHelperActivity = linearSnapHelperActivity;
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false);
                return new SnapViewHolder(view);
            case TYPE_LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_progress, parent, false);
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
                ((SnapViewHolder)holder).lblMessage.setText(animalEntity.getTitle());
                ((SnapViewHolder)holder).imgAndroid.setImageResource(R.drawable.ic_android_green_500_48dp);
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
    public static class SnapViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView lblMessage;
        AppCompatImageView imgAndroid;

        public SnapViewHolder(View itemView) {
            super(itemView);
            lblMessage = (AppCompatTextView) itemView.findViewById(R.id.lblMessage);
            imgAndroid = (AppCompatImageView) itemView.findViewById(R.id.imgAndroid);
        }
    }

}

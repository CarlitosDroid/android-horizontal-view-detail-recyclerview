package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.activity.MultipleOrientationSnapHelperActivity;
import com.carlitosdroid.horizontalviewdetailrcv.model.JuryEntity;
import com.carlitosdroid.horizontalviewdetailrcv.model.LoadingEntity;

import java.util.List;

/**
 * Created by Carlos Leonardo Carmilo Vargas Huamán on 2/15/17.
 *
 */

public class MultipleOrientationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private MultipleOrientationSnapHelperActivity multipleOrientationSnapHelperActivity;
    private List<Object> objectList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private static final int TYPE_ERROR = 2;

    private LinearLayoutManager linearLayoutManager;

    public MultipleOrientationAdapter(MultipleOrientationSnapHelperActivity multipleOrientationSnapHelperActivity, List<Object> objectList) {
        this.multipleOrientationSnapHelperActivity = multipleOrientationSnapHelperActivity;
        this.objectList = objectList;
        linearLayoutManager = new LinearLayoutManager(multipleOrientationSnapHelperActivity, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof JuryEntity) {
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_pager, parent, false);
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
                JuryEntity juryEntity = (JuryEntity) objectList.get(position);
                ((SnapViewHolder)holder).lblTitle.setText(juryEntity.getTitle());
                ((SnapViewHolder)holder).rcvSnap.setLayoutManager(linearLayoutManager);

                OrientationSnapAdapter orientationSnapAdapter = new OrientationSnapAdapter(juryEntity.getAnimalEntities());
                ((SnapViewHolder)holder).rcvSnap.setAdapter(orientationSnapAdapter);

//                ((SnapViewHolder)holder).rcvSnap.setOnFlingListener(new OnFlingListener() {
//                    @Override
//                    public boolean onFling(int velocityX, int velocityY) {
//
//                        Log.e("VEAMOS1 ","VEAMOS " +velocityX );
//                        Log.e("VEAMOS2 ","VEAMOS "  +velocityY);
//                        return true;
//                    }
//                });

                new LinearSnapHelper().attachToRecyclerView(((SnapViewHolder)holder).rcvSnap);
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

        AppCompatTextView lblTitle;
        RecyclerView rcvSnap;

        public SnapViewHolder(View itemView) {
            super(itemView);
            lblTitle = (AppCompatTextView) itemView.findViewById(R.id.lblTitle);
            rcvSnap = (RecyclerView) itemView.findViewById(R.id.rcvSnap);
        }
    }

}

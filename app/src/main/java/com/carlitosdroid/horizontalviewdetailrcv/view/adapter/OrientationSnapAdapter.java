package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.model.AnimalEntity;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/15/17.
 *
 */

public class OrientationSnapAdapter extends RecyclerView.Adapter<OrientationSnapAdapter.HorizontalSnapViewHolder> {

    private List<AnimalEntity> animalEntities;

    public OrientationSnapAdapter(List<AnimalEntity> animalEntities) {
        this.animalEntities = animalEntities;
    }

    @Override
    public HorizontalSnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false);
        return new HorizontalSnapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalSnapViewHolder holder, int position) {
        AnimalEntity animalEntity = animalEntities.get(position);
        holder.lblMessage.setText(animalEntity.getTitle());
    }

    @Override
    public int getItemCount() {
        return animalEntities.size();
    }

    public static class HorizontalSnapViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView lblMessage;
        AppCompatImageView imgAndroid;

        public HorizontalSnapViewHolder(View itemView) {
            super(itemView);
            lblMessage = (AppCompatTextView) itemView.findViewById(R.id.lblMessage);
            imgAndroid = (AppCompatImageView) itemView.findViewById(R.id.imgAndroid);
        }
    }
}

package com.carlitosdroid.horizontalviewdetailrcv.view.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carlitosdroid.horizontalviewdetailrcv.R;
import com.carlitosdroid.horizontalviewdetailrcv.activity.LinearSnapHelperActivity;

import java.util.List;

/**
 * Created by Carlos Leonardo Carmilo Vargas Huamán on 2/15/17.
 *
 */

public class SnapAdapter {

    private LinearSnapHelperActivity linearSnapHelperActivity;
    private List<Object> objectList;

    public SnapAdapter(LinearSnapHelperActivity linearSnapHelperActivity, List<Object> objectList) {



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

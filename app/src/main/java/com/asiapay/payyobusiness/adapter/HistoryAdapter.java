package com.asiapay.payyobusiness.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.Transaction;
import com.asiapay.payyobusiness.home.history.TransactionDetailsActivity;
import com.asiapay.payyobusiness.network.Constants;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<Transaction> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    String strFromTo, strEventType, strDate;
    float floatAmount;
    Activity mContext;

    public HistoryAdapter(Activity activity, ArrayList<Transaction> data) {
        this.mInflater = LayoutInflater.from(activity);
        this.mContext = activity;
        this.mData = data;
    }


    // data is passed into the constructor
  /*  HistoryAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }*/

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.history_rv_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        strFromTo = String.valueOf(mData.get(position).getFromTo());
        floatAmount = (mData.get(position).getAmount());
        strEventType = String.valueOf(mData.get(position).getEventType());
        strDate = String.valueOf(mData.get(position).getDate());
        if ((floatAmount) < 0) {
            holder.tvAmount.setTextColor(Color.parseColor("#ff0000"));
            holder.view.setBackgroundColor(Color.parseColor("#ff0000"));
            /*holder.dollarSignImg.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_hkd_red_sign, 0, 0, 0);
            holder.plusMinusSign.setTextColor(Color.parseColor("#ff0000"));*/
        }
        holder.tvAmount.setText(String.valueOf(floatAmount));
        holder.tvFromTo.setText(strFromTo);
        holder.tvEventType.setText(strEventType);
        holder.tvDate.setText(strDate);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView tvFromTo, tvAmount, tvDate, tvEventType;// dollarSignImg, plusMinusSign;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            tvFromTo = itemView.findViewById(R.id.tvFromTo);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            view = itemView.findViewById(R.id.viewIndicatorColor);
            tvEventType = itemView.findViewById(R.id.tvEventType);
            tvDate = itemView.findViewById(R.id.tvDate);
            /*dollarSignImg = itemView.findViewById(R.id.imgDollarSign);
            plusMinusSign = itemView.findViewById(R.id.txtPlusSign);*/
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent();
            intent.setClass(mContext, TransactionDetailsActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferences pref = ApplicationClass.getInstance().getPreferences();
            SharedPreferences.Editor pref_edit = pref.edit();
            pref_edit.putString(Constants.FROM_TO, strFromTo);
            pref_edit.putString(Constants.REFUND_AMOUNT, tvAmount.getText().toString().trim());
            pref_edit.putFloat(Constants.FLOAT_AMOUNT, Float.parseFloat(String.valueOf(floatAmount)));
            pref_edit.putString(Constants.EVENT_TYPE, strEventType);
            pref_edit.putString(Constants.DATE, strDate);
            pref_edit.commit();
            mContext.startActivity(intent);

           /* Fragment myFragment = new TransactionDetailsActivity();
            Bundle bundle = new Bundle();
            bundle.putString("strFromTo", strFromTo);
            bundle.putFloat("floatAmount", floatAmount);
            bundle.putString("strEventType", strEventType);
            bundle.putString("strDate", strDate);
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).commit();*/
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return String.valueOf(mData.get(id));
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

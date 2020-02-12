package com.asiapay.payyobusiness.home.history;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asiapay.payyobusiness.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheet extends BottomSheetDialogFragment {

    private Context mContext;
    private View view;
    public BottomSheet() {
    }


    public static final String TAG = "ActionBottomDialog";


    public static BottomSheet newInstance() {

        return new BottomSheet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_pay_yo, container, false);
    }


    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tvAmountBottomSheet).setOnClickListener(this);
        //view.findViewById(R.id.textView2).setOnClickListener(this);
        // view.findViewById(R.id.textView3).setOnClickListener(this);
        // view.findViewById(R.id.textView4).setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        TextView tvSelected = (TextView) v;
        mListener.onItemClick(tvSelected.getText().toString());
        dismiss();
    }*/


}

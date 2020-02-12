package com.asiapay.payyobusiness.home;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.model.Notification;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements NotificationContracts.View {

    private View view;
    public static final String TAG = "MyTag";
    private FirebaseInstanceId firebaseInstanceId;
    private NotificationFragmentPresenter notificationFragmentPresenter;
    private RecyclerView rvNotification;
    private ArrayList<Notification> arrayListNotification;
    private Toolbar toolbar;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.notification_bottom_nav_fragment, container, false);
        view = inflater.inflate(R.layout.notification_bottom_nav_fragment, container, false);
        arrayListNotification = new ArrayList<Notification>();
        notificationFragmentPresenter = new NotificationFragmentPresenter(this, this);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Notification");
        return view;
    }

    @Override
    public void apiResponseSuccess(ArrayList<Notification> notification) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void initUI() {
        rvNotification = view.findViewById(R.id.rvNotification);
    }

    @Override
    public void addEventListener() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideKeyboard() {

    }
}

package com.example.nemo1.fragmentexample.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.Interface.DataOffline;
import com.example.nemo1.fragmentexample.Presenter.ImagePresenter;
import com.example.nemo1.fragmentexample.R;

import java.util.List;

public class DetailImageFragment extends Fragment implements DataOffline {
    private Button pre_btn,next_btn;
    private ImageView imageView;

    public DetailImageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_image,container,false);

        return view;
    }

    @Override
    public void CallBackDetailImage(String url) {

    }

    @Override
    public void DataOffline(List<Image> imageList) {

    }
}

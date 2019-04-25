package com.example.nemo1.fragmentexample;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.Interface.DataOffline;
import com.example.nemo1.fragmentexample.Views.ListImagesFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataOffline {
    private String simpleDate = "";
    private View view;
    private TextView title_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setHideOffset(0);
        view = getSupportActionBar().getCustomView();
        title_center = view.findViewById(R.id.title_center);
        CurrenTime();
        initEvent();
    }

    private void initEvent() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout,new ListImagesFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void CallBackDetailImage(String url) {

    }

    @Override
    public void DataOffline(List<Image> imageList) {

    }

    public void CurrenTime(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                simpleDate = simpleDateFormat.format(new Date());
//                Log.d("simpleDate",simpleDate);
                title_center.setText(simpleDate);
                handler.postDelayed(this,1000);
            }
        };
        handler.postDelayed(runnable,1000);
    }
}

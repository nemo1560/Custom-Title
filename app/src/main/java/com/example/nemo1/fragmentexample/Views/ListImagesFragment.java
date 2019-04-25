package com.example.nemo1.fragmentexample.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.nemo1.fragmentexample.Adapter.ListImagesAdapter;
import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.Interface.DataOffline;
import com.example.nemo1.fragmentexample.Interface.DownloadData;
import com.example.nemo1.fragmentexample.Presenter.ImagePresenter;
import com.example.nemo1.fragmentexample.R;

import java.util.ArrayList;
import java.util.List;

public class ListImagesFragment extends Fragment implements View.OnClickListener, AbsListView.OnScrollListener,DataOffline, DownloadData {
    private ImagePresenter imagePresenter;
    private Button load;
    private GridView gridView;
    private ProgressBar loading;
    private ListImagesAdapter listImagesAdapter;
    private DataOffline dataOffline;
    private int pages;
    private int indexOfPage = 12;
    private int indexGetTotal;
    private int indexGot;
    private int i = 1;
    private static List<Image> imageList = new ArrayList<>();
    private static List<Image> listAPIoffline = new ArrayList<>();

    public ListImagesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataOffline = (DataOffline) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_images,container,false);
        load = view.findViewById(R.id.load_more_btn);
        gridView = view.findViewById(R.id.list_images);
        loading = view.findViewById(R.id.loading);
        initEvent();
        initData(); //Load data tu API ve
        return view;
    }

    private void initData() {
        loading.setVisibility(View.VISIBLE);
        imagePresenter = new ImagePresenter(getActivity(),this,this);
        imagePresenter.downloadData();
    }

    private void initEvent() {
        load.setOnClickListener(this);
    }

    private void getLoadMoreData(List<Image> imageList) {
        pages = imageList.size()/indexOfPage; //lay so trang hien thi ( indexOfPage = so data muon hien o tung trang)
        indexGetTotal = imageList.size(); // tong so data trong API
        if (i<=pages){//kiem tra i(trang) co lon hon tong trang.
            if(indexGot < indexGetTotal){ // kiem tra so luong data lay xuong co lon hon tong data trong API
                for(int a=indexGot;a<(i*indexOfPage);a++){ // loop so trang * so data hien ra o tung trang
                    listAPIoffline.add(imageList.get(a));
                }
                indexGot = listAPIoffline.size(); // so luong data da lay ve.
                Log.d("APIsize",String.valueOf(indexGot));
                i++; // tang so trang len
            }
        }
        listImagesAdapter = new ListImagesAdapter(getActivity(),listAPIoffline);
        gridView.setAdapter(listImagesAdapter);
        gridView.setNumColumns(1);
        gridView.setOnScrollListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == load.getId()){
            imagePresenter.getImageList();
            if(imageList.size()!=0){
                getLoadMoreData(imageList);
            }
            else {

            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        if(visibleItemCount<totalItemCount){
//            Log.d("visibleItemCount",String.valueOf(totalItemCount));
//            int total = visibleItemCount+12;
//            for(int i=0;i<=total;i++){
//                listAPIoffline.add(imageList.get(i));
//            }
//        }
    }

    @Override
    public void CallBackDetailImage(String url) {

    }

    @Override
    public void DataOffline(List<Image> imageList) {
        this.imageList = imageList;

    }

    @Override
    public void DataOnline(List<Image> imageList) {
        if(imageList.size()!=0){
            loading.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }
    }
}

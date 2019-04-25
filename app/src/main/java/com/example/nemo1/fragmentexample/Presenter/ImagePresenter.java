package com.example.nemo1.fragmentexample.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.nemo1.fragmentexample.Controller.ImageController;
import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.Interface.DataOffline;
import com.example.nemo1.fragmentexample.Interface.DownloadData;
import com.example.nemo1.fragmentexample.Model.ImageModel;

import java.util.List;

public class ImagePresenter implements DownloadData,DataOffline{
    private DataOffline dataOffline;
    private DownloadData downloadData;
    private ImageModel imageModel;
    private Context context;
    private ImageController controller;

    public ImagePresenter(Context context,DataOffline dataOffline,DownloadData downloadData) {
        this.context = context;
        this.dataOffline = dataOffline;
        this.downloadData = downloadData;
        controller = new ImageController(context,this,this);
    }

    public void getImageList(){
        controller.getImageList();
    }

    //Load data tu API ve
    public void downloadData(){
        imageModel = new ImageModel(context,this,this);
        imageModel.getData();
    }


    @Override
    public void CallBackDetailImage(String url) {

    }

    @Override
    public void DataOffline(List<Image> imageList) {
        dataOffline.DataOffline(imageList);
    }

    @Override
    public void DataOnline(List<Image> imageList) {
        downloadData.DataOnline(imageList);
    }
}

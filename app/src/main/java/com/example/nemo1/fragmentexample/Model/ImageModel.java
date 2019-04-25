package com.example.nemo1.fragmentexample.Model;

import android.content.Context;
import android.util.Log;

import com.example.nemo1.fragmentexample.Controller.ImageController;
import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.Interface.DataOffline;
import com.example.nemo1.fragmentexample.Interface.DownloadData;
import com.example.nemo1.fragmentexample.Interface.ImagesAPI;
import com.example.nemo1.fragmentexample.Interface.RetrofitInstance;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageModel {
    private ImagesAPI imagesAPI;
    private ImageController controller;
    private Context context;
    private DataOffline dataOffline;
    private DownloadData downloadData;

    public ImageModel(Context context, DataOffline dataOffline, DownloadData downloadData) {
        this.context = context;
        this.downloadData = downloadData;
        this.dataOffline = dataOffline;
    }

    public void getData() {
        imagesAPI = RetrofitInstance.RetrofitInstance().create(ImagesAPI.class);
        retrofit2.Call<List<Image>> listCall = imagesAPI.listImages();
        listCall.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Image>> call, Response<List<Image>> response) {
                if(response.body().size()>0){
                    Log.d("JsonSize",String.valueOf(response.body().size()));
                    controller = new ImageController(context,dataOffline,downloadData);
                    controller.addImageList(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Image>> call, Throwable t) {
                Log.d("loiAPI", t.toString());
            }
        });
    }
}
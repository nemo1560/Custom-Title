package com.example.nemo1.fragmentexample.Interface;

import com.example.nemo1.fragmentexample.Entity.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImagesAPI {
    @GET("list")
    Call<List<Image>>listImages();
}

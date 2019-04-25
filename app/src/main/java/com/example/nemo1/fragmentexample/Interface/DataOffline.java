package com.example.nemo1.fragmentexample.Interface;

import com.example.nemo1.fragmentexample.Entity.Image;

import java.util.List;

public interface DataOffline {
    void CallBackDetailImage(String url);
    void DataOffline(List<Image> imageList);
}

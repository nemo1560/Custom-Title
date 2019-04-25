package com.example.nemo1.fragmentexample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListImagesAdapter extends BaseAdapter {
    private List<Image> images;
    private Context context;
    private viewHolder viewHolder;

    public ListImagesAdapter(Context context,List<Image> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new viewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_image,null);
        viewHolder.imageView = convertView.findViewById(R.id.frame_image);
        viewHolder.edit1 = convertView.findViewById(R.id.edit1);
        viewHolder.edit2 = convertView.findViewById(R.id.edit2);
        if(!images.get(position).getAuthor().isEmpty()&&!images.get(position).getAuthor_url().isEmpty()){
            viewHolder.edit1.setEnabled(false);
            viewHolder.edit1.setText(images.get(position).getAuthor());
            viewHolder.edit2.setEnabled(false);
            viewHolder.edit2.setText(images.get(position).getAuthor_url());
        }
        return convertView;
    }

    static class viewHolder{
        private ImageView imageView;
        private EditText edit1,edit2;
    }
}

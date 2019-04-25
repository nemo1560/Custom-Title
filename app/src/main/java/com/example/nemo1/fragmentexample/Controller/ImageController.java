package com.example.nemo1.fragmentexample.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nemo1.fragmentexample.Entity.Image;
import com.example.nemo1.fragmentexample.Interface.DataOffline;
import com.example.nemo1.fragmentexample.Interface.DownloadData;

import java.util.ArrayList;
import java.util.List;

public class ImageController extends SQLiteOpenHelper implements DownloadData{
    private SQLiteDatabase sqLiteDatabase;
    private DataOffline dataOffline;
    private DownloadData downloadData;
    private List<Image> images;
    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "Images";
    //Table name
    private static final String TABLE_NAME = "Image_link";
    //Columns name
    private static final String COLUMN_IMAGE_FORMAT = "format";
    private static final String COLUMN_IMAGE_WIDTH = "width";
    private static final String COLUMN_IMAGE_HEIGHT = "height";
    private static final String COLUMN_IMAGE_FILENAME = "filename";
    private static final String COLUMN_IMAGE_ID = "id";
    private static final String COLUMN_IMAGE_AUTHOR = "author";
    private static final String COLUMN_IMAGE_AUTHOR_URL = "author_url";
    private static final String COLUMN_IMAGE_POST_URL = "post_url";

    public ImageController(@Nullable Context context, DataOffline dataOffline,DownloadData downloadData) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.dataOffline = dataOffline;
        this.downloadData = downloadData;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_IMAGE_FORMAT+" TEXT,"+COLUMN_IMAGE_WIDTH+" TEXT,"+COLUMN_IMAGE_HEIGHT+" TEXT,"+COLUMN_IMAGE_FILENAME+" TEXT,"+COLUMN_IMAGE_ID+" INTEGER PRIMARY KEY,"+COLUMN_IMAGE_AUTHOR+" TEXT,"+COLUMN_IMAGE_AUTHOR_URL+" TEXT,"+COLUMN_IMAGE_POST_URL+" TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addImageList(List<Image> imageList){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try{
            for(int i=0;i<imageList.size();i++){
                contentValues.put(COLUMN_IMAGE_FORMAT,imageList.get(i).getFormat());
                contentValues.put(COLUMN_IMAGE_WIDTH,imageList.get(i).getWidth());
                contentValues.put(COLUMN_IMAGE_HEIGHT,imageList.get(i).getHeight());
                contentValues.put(COLUMN_IMAGE_FILENAME,imageList.get(i).getFilename());
                contentValues.put(COLUMN_IMAGE_ID,imageList.get(i).getId());
                contentValues.put(COLUMN_IMAGE_AUTHOR,imageList.get(i).getAuthor());
                contentValues.put(COLUMN_IMAGE_AUTHOR_URL,imageList.get(i).getAuthor_url());
                contentValues.put(COLUMN_IMAGE_POST_URL,imageList.get(i).getPost_url());
                sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
            }
            sqLiteDatabase.close();
            Log.d("addOK",String.valueOf(imageList.size()));
            downloadData.DataOnline(imageList);
        }
        catch (Exception e){
            Log.d("AddError",e.toString());
        }
    }

    public List<Image> getImageList(){
        images = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                Image image = new Image();
                image.setFormat(cursor.getString(0));
                image.setWidth(cursor.getInt(1));
                image.setHeight(cursor.getInt(2));
                image.setFilename(cursor.getString(3));
                image.setId(cursor.getInt(4));
                image.setAuthor(cursor.getString(5));
                image.setAuthor_url(cursor.getString(6));
                image.setPost_url(cursor.getString(7));
                images.add(image);
            }while (cursor.moveToNext());
            if(images.size()>0){
                dataOffline.DataOffline(images);
            }
        }
        return images;
    }

    @Override
    public void DataOnline(List<Image> imageList) {

    }
}

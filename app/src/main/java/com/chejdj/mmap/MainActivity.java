package com.chejdj.mmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="zyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            @Override
            public void run() {
                Bitmap wallpaper = BitmapFactory.decodeResource(getResources(),
                        R.drawable.wallpaper);
                byte[] data = MMAPHelper.bitmap2Bytes(wallpaper);
//                mmapWrite(data);
                writeToFile(data);
            }
        }.start();
    }

    private void mmapWrite(byte[] data) {
        MMAPHelper.mmapWriteByte(data,getExternalCacheDir().getAbsolutePath(),"mmap.data");
    }

    private void writeToFile(byte[] data){
        File file = new File(getExternalCacheDir(),"normal.data");
        try {
           if(!file.exists()){
               file.createNewFile();
           }
            FileOutputStream fileOutputStream  = new FileOutputStream(file);
            Log.i(TAG,"start write");
            fileOutputStream.write(data);
            fileOutputStream.flush();
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Log.i(TAG,"write finish");
        }
    }
}
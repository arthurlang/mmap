package com.chejdj.mmap;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * @author zhuyangyang on 6/10/21
 **/
public class MMAPHelper {
    static {
        System.loadLibrary("native-lib");
    }
    public native static void mmapWrite(String data,String path,String fileName);

    public native static void mmapWriteByte(byte[] data,String path,String fileName);

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrOutStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrOutStream);
        return byteArrOutStream.toByteArray();
    }
}

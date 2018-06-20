package com.beidou.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Base64Utils {

    public static String encode(String path) {
        //decode to bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path);
      //  Log.d(TAG, "bitmap width: " + bitmap.getWidth() + " height: " + bitmap.getHeight());
        //convert to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        //base64 encode
        byte[] encode = Base64.encode(bytes,Base64.DEFAULT);
        String encodeString = new String(encode);
       return  encodeString;
    }


    public static Bitmap getBitmap(String imgStr) {
        //将字符串转换成Bitmap类型
        if (imgStr == null) //图像数据为空
            return null;
        byte[]bitmapArray;
        bitmapArray=Base64.decode(imgStr.split(",")[1], Base64.DEFAULT);

//        YuvImage yuvimage=new YuvImage(bitmapArray, ImageFormat.NV21, 100,40, null); //20、20分别是图的宽度与高度
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        yuvimage.compressToJpeg(new Rect(0, 0,20, 20), 80, baos);//80--JPG图片的质量[0-100],100最高
//        byte[] jdata = baos.toByteArray();
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inSampleSize = 8;
//        ByteArrayInputStream input = new ByteArrayInputStream(jdata);
//        SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
//        Bitmap bitmap = (Bitmap)softRef.get();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        return bitmap;

    }

    /**
     *
     * @param base64Data
     * @param imgName
     * @param
     */
    public static void base64ToBitmap(String base64Data,String imgName) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        File appDir = new File(Environment.getExternalStorageDirectory(),"ZSLS/yzm");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = imgName + ".png";
        File myCaptureFile = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            // fos.notifyAll();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

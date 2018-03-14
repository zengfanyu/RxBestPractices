package com.zfy.rxbestpractices.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: fanyuzeng on 2018/3/14 18:03
 */
public class ImageUtil {
    /**
     * @param imageUrl imageUrl could be any String.
     */
    public static boolean saveImage(Context context, Drawable drawable, String imageUrl) {
        File file = getImageFile(context, imageUrl);
        if (file != null) {
            Bitmap bitmap = drawableToBitmap(drawable);
            bitmapToFile(bitmap, file);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            //这个广播的目的就是更新图库
            context.sendBroadcast(intent);
            return true;
        }

        return false;
    }

    private static File getImageFile(Context context, String imageUrl) {
        // 获取App自身内部存储文件夹
//        File externalFileDir = context
//                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // 获取外部公共存储文件夹
        File pictureDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!pictureDir.exists()) {
            pictureDir.mkdir();
        }
        String fileName = "IMG_Rx_" + imageUrl.hashCode() + ".jpg";
        File publicFile = new File(pictureDir, fileName);
        return publicFile;
    }

    /**
     * drawable 转换成bitmap
     *
     * @param drawable
     * @return
     */
    private static Bitmap drawableToBitmap(Drawable drawable) {
        // 取drawable的长宽
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // 取drawable的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        // 建立对应bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        // 建立对应bitmap的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        // 把drawable内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }


    private static void bitmapToFile(Bitmap bitmap, File file) {
        try {
            FileOutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

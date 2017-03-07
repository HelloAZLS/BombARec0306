package com.example.administrator.bombarec;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by WYF on 2017/3/6.
 */

public class BitmapUtil {

    public static String bitmap2String(ImageView ivIcon) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ivIcon.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        ivIcon.layout(0, 0, ivIcon.getMeasuredWidth(), ivIcon.getMeasuredHeight());
        ivIcon.buildDrawingCache();
        ivIcon.setDrawingCacheEnabled(true);
        Bitmap obmp = Bitmap.createBitmap(ivIcon.getDrawingCache());
        ivIcon.setDrawingCacheEnabled(false);
        obmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();
        return Base64.encodeToString(appicon, Base64.DEFAULT);
    }

    public static Bitmap string2Bitmap(String st) {
        Bitmap bitmap=null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);

            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            return bitmap;
        }catch (Exception e)
        {
            return null;
        }

    }
}

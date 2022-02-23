package com.example.myapplication.common;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.util.Calendar;

public class GloableUsage {

    public String saveBitmapLocal(Bitmap bmp) {
        String _time = "";
        Calendar cal = Calendar.getInstance();
        int millisecond = cal.get(Calendar.MILLISECOND);
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
         _time = "image" + hourofday + "" + minute + "" + second + ""
                + millisecond + ".png";
        String file_path = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/attendance";
        try {
            File dir = new File(file_path);
            if (!dir.exists())
                dir.mkdirs();
            File file = new File(dir, _time);
            FileOutputStream fOut = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();
            fOut.close();
            /*Toast.makeText(getApplicationContext(),
                    "Image has been saved in KidsPainting folder",
                    Toast.LENGTH_LONG).show();*/
        } catch (Exception e) {
            //Log.e("error in saving image", e.getMessage());
        }

        return file_path;
    }

}

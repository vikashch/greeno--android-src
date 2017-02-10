package com.green0.customerApp.helpers;

/**
 * Created by sachinchandra on 10/12/16.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraPhotoModified {
    final String TAG = this.getClass().getSimpleName();
    private String photoPath;
    private Context context;

    public String getPhotoPath() {
        return this.photoPath;
    }

    public CameraPhotoModified(Context context) {
        this.context = context;
    }

    public Intent takePhotoIntent() throws IOException {
        Intent in = new Intent("android.media.action.IMAGE_CAPTURE");
        if(in.resolveActivity(this.context.getPackageManager()) != null) {
            File photoFile = this.createImageFile(0);
            if(photoFile != null) {
                in.putExtra("output", Uri.fromFile(photoFile));
            }
        }

        return in;
    }

    public File createImageFile(int type) throws IOException {
        String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File myDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File picturesFolder = new File(myDir,"FarmTech");
        picturesFolder.mkdir();
        File image = new File(picturesFolder.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        this.photoPath = image.getAbsolutePath();
        return image;
    }

    public void addToGallery() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(this.photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.context.sendBroadcast(mediaScanIntent);
    }
}
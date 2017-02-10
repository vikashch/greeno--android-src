package com.green0.customerApp.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ironman.myapplication.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.green0.customerApp.RestGreeno.ApiClient;
import com.green0.customerApp.RestGreeno.ApiInterface;
import com.green0.customerApp.model.ImageResultDisease;
import com.green0.customerApp.model.ImageResultDiseaseList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cropActionsActivity extends AppCompatActivity {

    @BindView(R.id.crop_planted_confirm)
    Button cropPlantConfirm;
    @BindView(R.id.upload_image)
    Button uploadImage;
    @BindView(R.id.symptoms_check)
    Button symptomsCheck;
    @BindView(R.id.gap_instructions)
    Button gapInstructions;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    String mCurrentPhotoPath;
    Uri phto;
    private ProgressDialog progressDialog;
    private ApiInterface ApiService;


     AlertDialog imageSelectdialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_actions);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        imageSelectdialog = new AlertDialog.Builder(this).create();
        ApiService = ApiClient.getClient().create(ApiInterface.class);
        progressDialog = new ProgressDialog(this);
    }


    @OnClick(R.id.gap_instructions)
    public void onGapClick(View view){

        startActivity(new Intent(cropActionsActivity.this,GapInstructionsActivity.class));
    }

    @OnClick(R.id.crop_planted_confirm)
    public void onPlantCOnfirmationClick(View view){

    }

    @OnClick(R.id.upload_image)
    public void  onUploadImageClick(View view)  {

        //imageSelectdialog.setTitle("upload from ");
        imageSelectdialog.setButton(Dialog.BUTTON_POSITIVE,"Camera",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(cropActionsActivity.this,DiseaseCheckActivity.class));
            }
        });
        imageSelectdialog.setButton(Dialog.BUTTON_NEGATIVE,"Gallery",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(cropActionsActivity.this,"Not implemented yet",Toast.LENGTH_LONG).show();
            }
        });
       // imageSelectdialog.show();
        dispatchTakePictureIntent();

    }

    @OnClick(R.id.symptoms_check)
    public void onSymptomsCheck(View view){

    }

    private void dispatchTakePictureIntent()  {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
               // photoFile = createPublicImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(photoFile!=null){
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile);
                phto = photoURI;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                String path = photoURI.getPath();
                //String path2 = getRealPathFromURI(this,photoURI);
                File file = new File(path);

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }

    /**
     * Checking device has camera hardware or not
     * */
    private  boolean isDeviceSupportCamera() {
        return getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //Todo add username from shared preferences
        String imageFileName = "username"+"_JPEG_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private File createPublicImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //Todo add username from shared preferences
        String imageFileName = "username"+"_JPEG_"+timeStamp+"_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

//        File image = File.createTempFile(
//                imageFileName,
//                ".jpg",
//                storageDir
//        );

        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile(), "blah blah");

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {

            File imgFile = new  File(mCurrentPhotoPath);
            if(imgFile.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
              //  ImageView mImageView = (ImageView) findViewById(R.id.test_image);

                //Bitmap bitmap=mImageView.getDrawingCache();
//                String ImagePath = MediaStore.Images.Media.insertImage(
//                        getContentResolver(),
//                        bitmap,
//                        "demo_image",
//                        "demo_image"
//                );
            //    mImageView.setImageBitmap(bitmap);
               // setPic(mImageView);
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpg "), imgFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", imgFile.getName(), reqFile);
              //  MultipartBody.Part body2 = MultipartBody.Part.createFormData("file", imgFile.getName(), reqFile);;
                Call<List<ImageResultDisease>> imgUploadReq = ApiService.postImages(body);
                progressDialog.setIndeterminate(true);
                progressDialog.setTitle("uploading image");
                progressDialog.show();
                imgUploadReq.enqueue(new Callback<List<ImageResultDisease>>() {
                    @Override
                    public void onResponse(Call<List<ImageResultDisease>> call, Response<List<ImageResultDisease>> response) {
                        if(response.code()==200){
                            progressDialog.dismiss();
                            List<ImageResultDisease> list= response.body();
                            Gson gson = new Gson();
                            Intent intent = new Intent(cropActionsActivity.this, DiseasePercentageListActivity.class);
                            intent.putExtra("obj", gson.toJson(list));
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ImageResultDisease>> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(cropActionsActivity.this,"oops upload failed",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }

    private void setPic(ImageView mImageView) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCurrentPhotoPath != null) {
            outState.putString("cameraImageUri", mCurrentPhotoPath.toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("cameraImageUri")) {
            mCurrentPhotoPath = savedInstanceState.getString("cameraImageUri");
        }
    }

}

package com.example.myapplication.activities;

import static android.Manifest.permission.CAMERA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AttendenceActivity extends AppCompatActivity {
    private static final int MY_CAMERA_PERMISSION_CODE = 90;
    private TextView take_self_txt_btn,submit_btn_txt;
     private ImageView img_attendence;
    ImageCapture imageCapture1;
//    CameraView cameraView;
private Executor executor = Executors.newSingleThreadExecutor();

    PreviewView mPreviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        startProcess();
        continueProcess();
    }



    private void startProcess() {
        take_self_txt_btn = findViewById(R.id.take_self_txt_btn);
        submit_btn_txt = findViewById(R.id.submit_btn_txt);
        img_attendence = findViewById(R.id.img_attendence);
         mPreviewView = findViewById(R.id.previewView);
    }

    private void continueProcess() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {

           /*     cameraView.openAsync(CameraView.findCameraId(
                        Camera.CameraInfo.CAMERA_FACING_FRONT));*/
                final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

                cameraProviderFuture.addListener(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                            bindPreview(cameraProvider);

                        } catch (ExecutionException | InterruptedException e) {
                            // No errors need to be handled for this Future.
                            // This should never be reached.
                        }
                    }
                }, ContextCompat.getMainExecutor(this));


            }


           /* ImagePicker.with(AttendenceActivity.this)
                    .cameraOnly()	//User can only capture image using Camera
                    .start(request);*/
        }


//         cameraView = new CameraView(getApplicationContext());
        take_self_txt_btn.setOnClickListener(view -> {

            captureImage();

//            ChaptureImage( 104);
         /*   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);*/
           /* File outPutFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + PatchFixesHider.Util.SD_CARD_PATH);
            if (!outPutFile.exists()) {
                outPutFile.mkdirs();
            }*/
//            capturedImageUri = Uri.fromFile(File.createTempFile("packagename" + System.currentTimeMillis(), ".jpg", outPutFile));

//            intent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
//            startActivityForResult(intent, 104);
        });

        submit_btn_txt.setOnClickListener(view -> {
           startActivity(new Intent(AttendenceActivity.this,MainActivity.class));
        });
    }

    private void ChaptureImage( int request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {

           /*     cameraView.openAsync(CameraView.findCameraId(
                        Camera.CameraInfo.CAMERA_FACING_FRONT));*/
                final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

                cameraProviderFuture.addListener(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                            bindPreview(cameraProvider);

                        } catch (ExecutionException | InterruptedException e) {
                            // No errors need to be handled for this Future.
                            // This should never be reached.
                        }
                    }
                }, ContextCompat.getMainExecutor(this));


            }


                ImagePicker.with(AttendenceActivity.this)
                        .cameraOnly()	//User can only capture image using Camera
                        .start(request);
            }
        }

    private void bindPreview(ProcessCameraProvider cameraProvider) {

        cameraProvider.unbindAll();
//        camera selector
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build();

//        preview case
              Preview preview = new Preview.Builder()
                .build();
              preview.setSurfaceProvider(mPreviewView.getSurfaceProvider());
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .build();

        ImageCapture.Builder builder = new ImageCapture.Builder();

        //Vendor-Extensions (The CameraX extensions dependency in build.gradle)
//        HdrImageCaptureExtender hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder);

       /* final ImageCapture imageCapture = builder
                .setTargetRotation(this.getWindowManager().getDefaultDisplay().getRotation())
                .build();*/

        imageCapture1 = builder.setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
               .build();
       cameraProvider.bindToLifecycle((LifecycleOwner)this,cameraSelector,preview,imageCapture1);

    }

    private void captureImage() {
        @SuppressLint("SdCardPath") File photoDir = new File("/mnt/sdcard/pictures/SelfyImage");
        if (!photoDir.exists()){
            photoDir.mkdir();
            Date date = new Date();
            String timestamp = String.valueOf(date.getTime());
            String photoFilePath  = photoDir.getAbsolutePath()+"/"+timestamp+".jpg";
            File photoFile = new File(photoFilePath);

            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();
            imageCapture1.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback () {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AttendenceActivity.this, "Image Saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onError(@NonNull ImageCaptureException error) {
                    error.printStackTrace();
                }
            });
        }
    }


/*

    @Override
    public void onResume() {
        super.onResume();
//        cameraView.openAsync(CameraView.findCameraId(
//                Camera.CameraInfo.CAMERA_FACING_FRONT));
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraView.close();
    }
*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode,@NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 104) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String path = uri.getPath();
                File f = new File("" + path);
                String pImage = f.getName();
                Log.d("KKKKKKKKKKKK", "onActivityResult: " + Uri.parse(path));
//                img_attendence.setImageURI(Uri.parse(path));
              /*  String path1 = sizeImgCompress(getApplicationContext(), path, pImage);
                userProfile_file = new File("" + path1);
//                photo = convertLayoutToImage(R.layout.activity_capture_image_activity);
                photo = getBitMapFromView(rl_imv);
                Uri tempUri = getImageUri(getApplicationContext(), photo);
                String path_new = tempUri.getPath();
                userProfile_file = new File("" + path_new);
*/
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }
    }



    }
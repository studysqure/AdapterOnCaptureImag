package com.example.myapplication.activities;

import static android.Manifest.permission.CAMERA;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.MyDBRoom.AndroidRoom;
import com.example.myapplication.MyDBRoom.RoomQuetionData;
import com.example.myapplication.R;
import com.example.myapplication.adapers.QuetionaryAdp;
import com.example.myapplication.api.ApiClient;
import com.example.myapplication.model.QuestionaryWrapper;
import com.example.myapplication.model.QuetionData;
import com.example.myapplication.model.QuetionaryData;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int MY_CAMERA_PERMISSION_CODE = 107;

    private LinearLayout ll_six_lyt, ll_five_lyt, ll_four_lyt, ll_three_lyt,
            ll_two_lyt, ll_one_lyt;
    private RadioGroup q_six_RG, q_five_RG, q_four_RG, q_three_RG, q_two_RG, q_one_RG;
    private RadioButton radioButton;
    private TextInputLayout lang_drop_lyt;
    private AutoCompleteTextView select_language_actv;
    private RecyclerView quetionary_recyler;
    private TextView submit_btn_txt;
    private Toolbar toolbar;
    private String language, path1,path2,path3;
    private ArrayList<RoomQuetionData> dataArrayList = new ArrayList<RoomQuetionData>();
    QuetionaryAdp quetionaryAdp;
    int adapterPosition;
    private AndroidRoom androidRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startProcess();
        continueProcess();

    }


    private void startProcess() {
        androidRoom = AndroidRoom.getInstance(this);

        submit_btn_txt = findViewById(R.id.submit_btn_txt);
     /*
        ll_five_lyt = findViewById(R.id.ll_five_lyt);
        ll_four_lyt = findViewById(R.id.ll_four_lyt);
        ll_three_lyt = findViewById(R.id.ll_three_lyt);
        ll_two_lyt = findViewById(R.id.ll_two_lyt);*/
        ll_one_lyt = findViewById(R.id.ll_one_lyt);
        lang_drop_lyt = findViewById(R.id.lang_drop_lyt);
       /* q_six_RG = findViewById(R.id.q_six_RG);
        q_five_RG = findViewById(R.id.q_five_RG);
        q_four_RG = findViewById(R.id.q_four_RG);
        q_three_RG = findViewById(R.id.q_three_RG);
        */
        ;
        toolbar = findViewById(R.id.toolbar);
        q_one_RG = findViewById(R.id.q_one_RG);
        select_language_actv = findViewById(R.id.select_language_actv);
        quetionary_recyler = findViewById(R.id.quetionary_recyler);
    }

    private void continueProcess() {
        language = "0";
        fatchDataServer("1", "0");
        toolbar.setTitle(" Fill Survey Data");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbar.setNavigationOnClickListener(v -> finish());


        submit_btn_txt.setOnClickListener(view -> {
           /*   if(checkValidata()){
                  sendDataServer(dataArrayList);
              }*/
//            startActivity(new Intent(MainActivity.this, FeedBackActivity.class));
        });




        select_language_actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("RRRRRRRRRRRRRR", "afterTextChanged: " + select_language_actv.getText().toString());
                if (Objects.equals(lang_drop_lyt.getEditText().getText().toString(), "English")) {
                    fatchDataServer("1", "0");
                    Log.d("RRRRRRRRRRRRRR true", "afterTextChanged: " + select_language_actv.getText().toString());
                    language = "0";
                } else {
                    Log.d("RRRRRRRRRRRRRR", "afterTextChanged:false " + select_language_actv.getText().toString());
                    fatchDataServer("1", "1");
                    language = "1";
                }
            }
        });

        select_language_actv.setAdapter(new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.language)));


    }



    private void sendDataServer(ArrayList<QuetionData> dataArrayList) {
//        Log.d("LLLLLLLLLLLLLLLL", "fatchDataServer: ?type"+type+"&lang_type="+lang_type);
        retrofit2.Call<QuestionaryWrapper> call = ApiClient.getClient().sendAttandeceData(new Gson().toJson(dataArrayList));
        call.enqueue(new Callback<QuestionaryWrapper>() {
            @Override
            public void onResponse(retrofit2.Call<QuestionaryWrapper> call, Response<QuestionaryWrapper> response) {
                QuestionaryWrapper questionaryWrapper = response.body();
                if (response.isSuccessful()){
                    Log.d("LLLLLLLLLLLLLLLL", "success: " + questionaryWrapper.getMessage());
                    Toast.makeText(MainActivity.this, ""+questionaryWrapper.getStatus()+"message"+questionaryWrapper.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<QuestionaryWrapper> call, Throwable t) {
                Log.d("LLLLLLLLLLLLLLLL", "onFailure: " + t);

            }
        });
    }



    /*private boolean checkValidata() {
        boolean flag=true;
        for(QuetionData hh : dataArrayList){
            if(hh.getQ_yes_1_no_0().equals("")) {
//            percentage.setError("Please Enter Percentage ");
                Toast.makeText(this, "Please fill all quation anser ", Toast.LENGTH_SHORT).show();
                flag = false;
            }else
            if (hh.getBs64Img1().equals("")) {
//           result.setError("Please Select result ");
                Toast.makeText(this, "Please fill all quation anser  ", Toast.LENGTH_SHORT).show();

       *//*     MDToast mdToast = MDToast.makeText(getApplicationContext(), "Please Select Result", MDToast.TYPE_WARNING);
            mdToast.show();*//*

                flag = false;
            }else
            if (hh.getBs64Img2().equals("")) {
//           result.setError("Please Select result ");
                Toast.makeText(this, "Please fill all quation anser  ", Toast.LENGTH_SHORT).show();
       *//*     MDToast mdToast = MDToast.makeText(getApplicationContext(), "Please Select Result", MDToast.TYPE_WARNING);
            mdToast.show();*//*
                flag = false;
            }else
            if (hh.getBs64Img3().equals("")) {
//           result.setError("Please Select result ");
           Toast.makeText(this, "Please fill all quation anser  ", Toast.LENGTH_SHORT).show();

       *//*     MDToast mdToast = MDToast.makeText(getApplicationContext(), "Please Select Result", MDToast.TYPE_WARNING);
            mdToast.show();*//*
                flag = false;
            }
        }

        return flag;
    }*/

    private void fatchDataServer(String type, String lang_type) {
        Log.d("RRRRRRRRRRRRRR", "?type=" + type + "&lang_type=" + lang_type);
        retrofit2.Call<QuestionaryWrapper> call = ApiClient.getClient().getQuetinary(type, lang_type);
        call.enqueue(new Callback<QuestionaryWrapper>() {
            @Override
            public void onResponse(retrofit2.Call<QuestionaryWrapper> call, Response<QuestionaryWrapper> response) {
                QuestionaryWrapper questionaryWrapper = response.body();
//                dataArrayList = questionaryWrapper.getData();
                RoomQuetionData roomQuetionData = new RoomQuetionData();
                androidRoom.roomDao().delete(roomQuetionData);
                androidRoom.roomDao().reset(dataArrayList);
                for (QuetionData jj: Objects.requireNonNull(questionaryWrapper).getData()){
                    roomQuetionData.setId(Integer.parseInt(jj.getId()));
                    roomQuetionData.setQues(jj.getQues());
                    roomQuetionData.setQ_yes_1_no_0("0");
                    roomQuetionData.setImage("0");
                    roomQuetionData.setBs64Img1("0");
                    roomQuetionData.setBs64Img2("0");
                    roomQuetionData.setBs64Img3("0");
                    roomQuetionData.setImage2("0");
                    roomQuetionData.setImage3("0");
                    androidRoom.roomDao().insert(roomQuetionData);

//                    roomQuetionData.setQ_yes_1_no_0(jj.getQ_yes_1_no_0());
//                    roomQuetionData.setImage(jj.getImage());

                }
                dataArrayList.clear();
               dataArrayList.addAll(androidRoom.roomDao().getAll());
                attendenceQuationary(dataArrayList);
                Log.d("LLLLLLLLLLLLLLLL", "success: " + questionaryWrapper.getData());

            }

            @Override
            public void onFailure(retrofit2.Call<QuestionaryWrapper> call, Throwable t) {
                Log.d("LLLLLLLLLLLLLLLL", "onFailure: " + t);

            }
        });
    }


    public void captureImage(int request, int adapterPosition1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{CAMERA}, MY_CAMERA_PERMISSION_CODE);
            } else {
                ImagePicker.with(this)
                        .cameraOnly()    //User can only capture image using Camera
                        .start(request);
                 adapterPosition = adapterPosition1;

            }
        }
    }

    private void attendenceQuationary(ArrayList<RoomQuetionData> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        quetionary_recyler.setLayoutManager(layoutManager);
        quetionary_recyler.setItemAnimator(new DefaultItemAnimator());
        quetionaryAdp = new QuetionaryAdp(data, MainActivity.this, MainActivity.this, language);
        quetionary_recyler.setAdapter(quetionaryAdp);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                path1 = uri.getPath();
                File f = new File("" + path1);
               File fImg1= saveBitmapToFile(f);
                String  bs64Img1= Base64ImageEncode(path1);
//               dataArrayList.get(adapterPosition).setBs64Img1(bs64Img1);
                Log.d("UUUUUUUUUUU", "position="+adapterPosition+"onActivityResult: "+fImg1.getPath());
                androidRoom.roomDao().imgSet(adapterPosition,fImg1.getPath());
//                dataArrayList.get(adapterPosition).setImage(fImg1.getPath());
                dataArrayList.clear();
                dataArrayList.addAll(androidRoom.roomDao().getAll());
                String pImage = f.getName();
                Log.d("LLLLLLLLLLLLLLLL", "success: " + androidRoom.roomDao().getAll());

                quetionaryAdp = new QuetionaryAdp(dataArrayList, MainActivity.this, MainActivity.this, language);
                quetionary_recyler.setAdapter(quetionaryAdp);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (requestCode == 102) {
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    path2 = uri.getPath();
                    File f2 = new File("" + path2);
                    File fImg2= saveBitmapToFile(f2);
                    String  bs64Img2= Base64ImageEncode(path2);
                    Log.d("UUUUUUUUUUU", "onActivityResult: "+adapterPosition);
                    androidRoom.roomDao().img2Set(adapterPosition,fImg2.getPath());
//                    dataArrayList.get(adapterPosition).setBs64Img2(bs64Img2);
//                    dataArrayList.get(adapterPosition).setImage2(fImg2.getPath());
                    dataArrayList.clear();
                    dataArrayList.addAll(androidRoom.roomDao().getAll());
                    String pImage = f2.getName();
                    quetionaryAdp = new QuetionaryAdp(dataArrayList, MainActivity.this, MainActivity.this, language);
                    quetionary_recyler.setAdapter(quetionaryAdp);

//

                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
                }

            } else {
                if (requestCode == 103) {
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        path3 = uri.getPath();
                        File f3 = new File("" + path3);
                        File fImg3= saveBitmapToFile(f3);
                        String  bs64Img3= Base64ImageEncode(path3);
                        Log.d("UUUUUUUUUUU", "onActivityResult: "+bs64Img3);
//                        dataArrayList.get(adapterPosition).setBs64Img3(bs64Img3);
                        androidRoom.roomDao().img3Set(adapterPosition,fImg3.getPath());
//                        dataArrayList.get(adapterPosition).setImage3(fImg3.getPath());
                        dataArrayList.clear();
                        dataArrayList.addAll(androidRoom.roomDao().getAll());
                        String pImage = f3.getName();
                        quetionaryAdp = new QuetionaryAdp(dataArrayList, MainActivity.this, MainActivity.this, language);
                        quetionary_recyler.setAdapter(quetionaryAdp);
                        Log.d("KKKKKKKKKKKK", "onActivityResult: " + Uri.parse(path3));
                    } else if (resultCode == RESULT_CANCELED) {
                        Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        }
    }

    public String Base64ImageEncode(String path64){
        //encode image to base64 string
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(""+path64);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byte[] imageBytes = baos.toByteArray();
            path64 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return path64;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public File saveBitmapToFile(File file){

        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=200;        // x............

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            File outPutFile = File.createTempFile("abc","image");
            FileOutputStream outputStream = new FileOutputStream(outPutFile);
            // y.......
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 50 , outputStream);

            return outPutFile;
        } catch (Exception e) {
            return null;
        }
    }

    public void buttonCheck(String yes_or_no,int position) {
//        dataArrayList.get(position).setQ_yes_1_no_0(yes_or_no);
        androidRoom.roomDao().setYesNo(yes_or_no,position);


    }
}
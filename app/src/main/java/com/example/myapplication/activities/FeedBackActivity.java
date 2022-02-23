package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapers.FeedBackAdp;
import com.example.myapplication.api.ApiClient;
import com.example.myapplication.model.QuestionaryWrapper;
import com.example.myapplication.model.QuetionData;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackActivity extends AppCompatActivity {
    private RatingBar rating_feedback;
    private RadioButton male_rb,female_rb,other_rb;
    private RadioGroup gender_rg;
    private RecyclerView feedback_quetionary_recyler;
    private AutoCompleteTextView select_language_actv;
    private TextView ratting_txt,gender_txt;
    private Toolbar toolbar;
    String language ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        startProcess();
        continueProcess();
    }



    private void startProcess() {
        toolbar = findViewById(R.id.toolbar);
        feedback_quetionary_recyler = findViewById(R.id.feedback_quetionary_recyler);
        rating_feedback = findViewById(R.id.rating_feedback);
        gender_rg = findViewById(R.id.gender_rg);
        select_language_actv = findViewById(R.id.select_language_actv);
        ratting_txt = findViewById(R.id.ratting_txt);
        gender_txt = findViewById(R.id.gender_txt);
        male_rb = findViewById(R.id.male_rb);
        female_rb = findViewById(R.id.female_rb);
        other_rb = findViewById(R.id.other_rb);

    }

    private void continueProcess() {
        language = "0";
        fatchDataServer("2","0");
        select_language_actv.setAdapter(new ArrayAdapter<>(FeedBackActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.language)));

        toolbar.setTitle(" Give FeedBack  ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbar.setNavigationOnClickListener(v -> finish());
//        rating_feedback.getRating();


        select_language_actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Objects.equals(select_language_actv.getText().toString(), "English")){
                    fatchDataServer("2","0");
                    language = "0";
                    other_rb.setText(R.string.other_E);
                    female_rb.setText(R.string.female_E);
                    male_rb.setText(R.string.male_E);
                    ratting_txt.setText(R.string.ratting_E);
                    gender_txt.setText(R.string.gender_E);
                    Log.d("RRRRRRRRRRRRRR true", "afterTextChanged: "+select_language_actv.getText().toString());
                }else {
                    Log.d("RRRRRRRRRRRRRR", "afterTextChanged:false "+select_language_actv.getText().toString());
                    fatchDataServer("2","1");
                    language = "1";
                    other_rb.setText(R.string.other_h);
                    female_rb.setText(R.string.female_h);
                    male_rb.setText(R.string.male_h);
                    ratting_txt.setText(R.string.ratting_h);
                    gender_txt.setText(R.string.gender_h);

                }
            }
        });

    }

    private void fatchDataServer(String type, String lang_type) {
        Log.d("LLLLLLLLLLLLLLLL", "fatchDataServer: ?type"+type+"&lang_type="+lang_type);
        retrofit2.Call<QuestionaryWrapper> call = ApiClient.getClient().getQuetinary(type, lang_type);
        call.enqueue(new Callback<QuestionaryWrapper>() {
            @Override
            public void onResponse(retrofit2.Call<QuestionaryWrapper> call, Response<QuestionaryWrapper> response) {
                QuestionaryWrapper questionaryWrapper = response.body();
                feedbackQuationary(questionaryWrapper.getData());
                Log.d("LLLLLLLLLLLLLLLL", "success: " + questionaryWrapper.getData());

            }

            @Override
            public void onFailure(retrofit2.Call<QuestionaryWrapper> call, Throwable t) {
                Log.d("LLLLLLLLLLLLLLLL", "onFailure: " + t);

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void feedbackQuationary(ArrayList<QuetionData> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        feedback_quetionary_recyler.setLayoutManager(layoutManager);
        feedback_quetionary_recyler.setItemAnimator(new DefaultItemAnimator());
        FeedBackAdp feedAdp = new FeedBackAdp(data,FeedBackActivity.this,FeedBackActivity.this,language);
        feedback_quetionary_recyler.setAdapter(feedAdp);
        feedAdp.notifyDataSetChanged();

    }

}
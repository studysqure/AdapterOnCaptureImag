package com.example.myapplication.adapers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MyDBRoom.RoomQuetionData;
import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.model.QuetionData;
import com.example.myapplication.model.QuetionaryData;
import com.github.dhaval2404.imagepicker.ImagePicker;


import java.util.ArrayList;
import java.util.Base64;


public class QuetionaryAdp extends RecyclerView.Adapter<QuetionaryAdp.ViewHoler> {
    private static final int MY_CAMERA_PERMISSION_CODE = 101;
    private ArrayList<RoomQuetionData> quetionData;
     private Context context;
     private RadioButton select_rg_btn,no_radio_btn,yes_radio_btn;
    private MainActivity activity;
    private String language;
    private int mSelected = -1;
//    private static final String TAG=QuetionaryAdp.class.getSimpleName();




    public QuetionaryAdp(ArrayList<RoomQuetionData> quetionData, Context context, MainActivity activity, String lang) {
        this.quetionData = quetionData;
        this.context = context;
        this.activity = activity;
        this.language = lang;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoler( LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_list_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, @SuppressLint("RecyclerView") int position) {
        if (quetionData!=null){
            final  RoomQuetionData hh = quetionData.get(position);
            holder.quetion_text.setText(hh.getQues());

//     Toast.makeText(context, "Hello Item Here", Toast.LENGTH_SHORT).show();

            if (language.equals("0")){
                no_radio_btn.setText(R.string.no_E);
                yes_radio_btn.setText(R.string.yes_E);
                holder.img_one_txt_btn.setText(R.string.upload1_E);
                holder.img_two_txt_btn.setText(R.string.upload2_E);
                holder.img_three_txt_btn.setText(R.string.upload3_E);
                holder.img_one.setText(R.string.image1_E);
                holder.img_two.setText(R.string.image2_E);
                holder.img_three.setText(R.string.image3_E);
            }else {
                no_radio_btn.setText(R.string.no_h);
                yes_radio_btn.setText(R.string.yes_h);
                holder.img_one_txt_btn.setText(R.string.upload1_h);
                holder.img_two_txt_btn.setText(R.string.upload2_h);
                holder.img_three_txt_btn.setText(R.string.upload3_h);
                holder.img_one.setText(R.string.image1_h);
                holder.img_two.setText(R.string.image2_h);
                holder.img_three.setText(R.string.image3_h);
            }

            Log.d("LLLLLLLLLLLLLLLLLLL", "point=: "+hh.getQ_yes_1_no_0()+"position="+position);
            if (hh.getQ_yes_1_no_0()!=null && !hh.getQ_yes_1_no_0().equals("")){
                if (hh.getQ_yes_1_no_0().equals("1")){
                    yes_radio_btn.setChecked(true);
                    no_radio_btn.setChecked(false);
                    holder.ll_one_lyt.setVisibility(View.VISIBLE);
                }else if (hh.getQ_yes_1_no_0().equals("2")){
                    yes_radio_btn.setChecked(false);
                    no_radio_btn.setChecked(true);
                    holder.ll_one_lyt.setVisibility(View.GONE);
                }
            }else {
                yes_radio_btn.setChecked(false);
                no_radio_btn.setChecked(false);
                holder.ll_one_lyt.setVisibility(View.GONE);
            }

            Log.d("TTTTTTTTTTTTTT", "onBindViewHolder: img"+hh.getImage());

            if (hh.getImage()!=null){
                holder.img_one_lyt1.setImageURI(Uri.parse(hh.getImage()));
            }
            if (hh.getImage2()!=null){
                Log.d("TTTTTTTTTTTTTT", "onBindViewHolder: img2"+hh.getImage2());
                holder.img_one_lyt2.setImageURI(Uri.parse(hh.getImage2()));
            }
            if (hh.getImage3()!=null){
                Log.d("TTTTTTTTTTTTTT", "onBindViewHolder: img3"+hh.getImage3());
                holder.img_one_lyt3.setImageURI(Uri.parse(hh.getImage3()));
            }

            /* yes_radio_btn.setOnClickListener(view -> {
                yes_radio_btn.setChecked(true);
                no_radio_btn.setChecked(false);
                 quetionData.get(position).setQ_yes_1_no_0("1");
                 holder.ll_one_lyt.setVisibility(View.VISIBLE);
             });

            no_radio_btn.setOnClickListener(view -> {
                holder.ll_one_lyt.setVisibility(View.GONE);
                quetionData.get(position).setQ_yes_1_no_0("0");
                no_radio_btn.setChecked(false);
            });*/

            holder.q_one_RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                    quetionData.get(position);
                    View radioButton = holder.q_one_RG.findViewById(checkedId);
                    int index = holder.q_one_RG.indexOfChild(radioButton);
                    Log.d("JJJJJJJJJJJJJJJ", "onCheckedChanged: "+index);
                    switch (index) {
                        case 0:
                            activity.buttonCheck("1",hh.getId());
                            holder.ll_one_lyt.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            activity.buttonCheck("2",hh.getId());
                            holder.ll_one_lyt.setVisibility(View.GONE);
                            break;
                    }
                }
            });


/*
             holder.q_one_RG.setOnCheckedChangeListener((radioGroup, i) -> {
                 select_rg_btn = radioGroup.findViewById(i);
                 Log.d("KKKKKKKKKKKKKKKKK", "onBindViewHolder: "+i);

                 if (select_rg_btn.getText().toString().equals("YES") || select_rg_btn.getText().toString().equals("हां") ){
                     yes_radio_btn.setChecked(true);
                     no_radio_btn.setChecked(false);
                     holder.ll_one_lyt.setVisibility(View.VISIBLE);
                 }else {
                     yes_radio_btn.setChecked(false);
                     no_radio_btn.setChecked(true);
                     holder.ll_one_lyt.setVisibility(View.GONE);
                 }
             });
*/

             holder.img_three_txt_btn.setOnClickListener(view -> activity.captureImage(103,hh.getId()));

            holder.img_two_txt_btn.setOnClickListener(view -> activity.captureImage(102,hh.getId()));

            holder.img_one_txt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
               activity.captureImage(101,hh.getId());
//                 holder.img_one_lyt1.setImageURI(U);
//                    ChaptureImage(102);
//                    Log.d(TAG, "onClick:image uri "+gg);

                }
            });
        }
    }

  /*  private void ChaptureImage( int request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                activity.requestPermissions(new String[]{CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {
                ImagePicker.with(activity)
                        .cameraOnly()	//User can only capture image using Camera
                        .start(request);
            }
        }
    }*/


    

    @Override
    public int getItemCount() {
        return quetionData.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView img_one_lyt1,img_one_lyt2,img_one_lyt3;
        LinearLayout ll_one_lyt;
        RadioGroup q_one_RG;
        TextView quetion_text,img_three_txt_btn,img_two_txt_btn,img_one_txt_btn,img_three,img_two,img_one;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            q_one_RG = itemView.findViewById(R.id.q_one_RG);
            no_radio_btn = itemView.findViewById(R.id.no_radio_btn);
            yes_radio_btn = itemView.findViewById(R.id.yes_radio_btn);
            quetion_text = itemView.findViewById(R.id.quetion_text);
            img_three_txt_btn = itemView.findViewById(R.id.img_three_txt_btn);
            img_two_txt_btn = itemView.findViewById(R.id.img_two_txt_btn);
            img_one_txt_btn = itemView.findViewById(R.id.img_one_txt_btn);
            ll_one_lyt = itemView.findViewById(R.id.ll_one_lyt);
            img_three = itemView.findViewById(R.id.img_three);
            img_two = itemView.findViewById(R.id.img_two);
            img_one = itemView.findViewById(R.id.img_one);
            img_one_lyt1 = itemView.findViewById(R.id.img_one_lyt1);
            img_one_lyt2 = itemView.findViewById(R.id.img_one_lyt2);
            img_one_lyt3 = itemView.findViewById(R.id.img_one_lyt3);
        }
    }
}
// error soliution are offline build in gradle
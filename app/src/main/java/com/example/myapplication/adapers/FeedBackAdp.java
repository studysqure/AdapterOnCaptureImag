package com.example.myapplication.adapers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.QuetionData;

import java.util.ArrayList;

public class FeedBackAdp  extends RecyclerView.Adapter<FeedBackAdp.ViewHoler> {
    private ArrayList<QuetionData> quetionData;
    private Context context;
    RadioButton no_radio_btn,yes_radio_btn;
    Activity activity;
    private String language;

    public FeedBackAdp(ArrayList<QuetionData> quetionData, Context context, Activity activity, String language) {
        this.quetionData = quetionData;
        this.context = context;
        this.activity = activity;
        this.language = language;
    }

    @NonNull
    @Override
    public FeedBackAdp.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedBackAdp.ViewHoler( LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_quationary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedBackAdp.ViewHoler holder, int position) {
        if (quetionData!=null){
            final  QuetionData hh = quetionData.get(position);
            holder.quetion_text.setText(hh.getQues());
            if (language.equals("0")){
                yes_radio_btn.setText(R.string.yes_E);
                no_radio_btn.setText(R.string.no_E);
            }else {
                yes_radio_btn.setText(R.string.yes_h);
                no_radio_btn.setText(R.string.no_h);
            }
            holder.q_one_RG.setOnCheckedChangeListener((radioGroup, i) -> {
                no_radio_btn = radioGroup.findViewById(i);
//                Toast.makeText(context, ""+no_radio_btn.getText(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return quetionData.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        RadioGroup q_one_RG;
        TextView quetion_text;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            q_one_RG = itemView.findViewById(R.id.q_one_RG);
            no_radio_btn = itemView.findViewById(R.id.no_radio_btn);
            quetion_text = itemView.findViewById(R.id.quetion_text);
            yes_radio_btn = itemView.findViewById(R.id.yes_radio_btn);

        }
    }
}

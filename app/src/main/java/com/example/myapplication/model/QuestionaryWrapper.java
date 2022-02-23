package com.example.myapplication.model;

import java.util.ArrayList;

import lombok.ToString;

@ToString
public class QuestionaryWrapper extends BaseResponse {

    ArrayList<QuetionData> data;

    public ArrayList<QuetionData> getData() {
        return data;
    }

    public void setData(ArrayList<QuetionData> data) {
        this.data = data;
    }
}

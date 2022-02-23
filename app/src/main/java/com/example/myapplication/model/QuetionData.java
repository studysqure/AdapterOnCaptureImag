package com.example.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuetionData {

    String id;
    String emp;
    String ques;
    String q_yes_1_no_0;
    String bs64Img1;
    String bs64Img2;
    String bs64Img3;
    String image;
    String image2;
    String image3;


}

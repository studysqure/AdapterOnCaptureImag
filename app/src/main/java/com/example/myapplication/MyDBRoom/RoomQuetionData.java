package com.example.myapplication.MyDBRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(tableName = "quetion_data")
public class RoomQuetionData {
    @PrimaryKey()
    int id;
    @ColumnInfo(name = "ques")
    String ques;
    @ColumnInfo(name = "q_yes_1_no_0")
    String q_yes_1_no_0;
    @ColumnInfo(name = "bs64Img1")
    String bs64Img1;
    @ColumnInfo(name = "bs64Img2")
    String bs64Img2;
    @ColumnInfo(name = "bs64Img3")
    String bs64Img3;
    @ColumnInfo(name = "image")
    String image;
    @ColumnInfo(name = "image2")
    String image2;
    @ColumnInfo(name = "image3")
    String image3;

}

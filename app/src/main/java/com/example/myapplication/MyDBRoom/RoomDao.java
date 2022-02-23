package com.example.myapplication.MyDBRoom;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface RoomDao {

    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(RoomQuetionData roomQuetionData);

    //delete query
    @Delete
    void delete(RoomQuetionData roomQuetionData);

    //delete all query
    @Delete
    void reset(List<RoomQuetionData> roomQuetionData);

    //update query
    @Query("UPDATE quetion_data SET q_yes_1_no_0 = :sText WHERE id = :sID")
    void setYesNo(String sText,int sID);

    //update query ,bs64Img1 = :bs64Img
    @Query("UPDATE quetion_data SET image = :simpleImg  WHERE id = :sID")
    void imgSet(int sID,String simpleImg);

    //update query ,bs64Img2 = :bs64Img
    @Query("UPDATE quetion_data SET image2 = :simpleImg  WHERE id = :sID")
    void img2Set(int sID,String simpleImg);

    //update query ,String bs64Img ,bs64Img3 = :bs64Img
    @Query("UPDATE quetion_data SET image3 = :simpleImg  WHERE id = :sID")
    void img3Set(int sID,String simpleImg);
    //Get all data query
    @Query("select *FROM quetion_data")
    List<RoomQuetionData> getAll();

}

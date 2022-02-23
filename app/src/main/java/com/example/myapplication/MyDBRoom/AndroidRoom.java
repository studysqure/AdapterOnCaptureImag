package com.example.myapplication.MyDBRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomQuetionData.class},version = 1,exportSchema = false)
public abstract class AndroidRoom extends RoomDatabase{
        //create database instance
        private static AndroidRoom database;
        //define database name
        private static String DATABASE_NAME = "database";

        public synchronized static AndroidRoom getInstance(Context context){
            //check condition
            if (database == null){
                //when database is null
                //initialize database
                database = Room.databaseBuilder(context.getApplicationContext(),
                        AndroidRoom.class,DATABASE_NAME)
                        .allowMainThreadQueries().
                                fallbackToDestructiveMigration().build();

            }
            //return database
            return database;
        }
        //    create dao
        public abstract RoomDao roomDao();

}

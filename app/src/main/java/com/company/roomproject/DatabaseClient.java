package com.company.roomproject;


import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private AppDatabase db;
    private final String DB_NAME="MyContacts";

           public DatabaseClient(Context context){

               db= Room.databaseBuilder(context,AppDatabase.class,DB_NAME).build();
    }

    public AppDatabase getAppDatabase(){
               return db;
    }


}

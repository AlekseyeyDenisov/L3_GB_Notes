package ru.dw.gbnotes;

import android.app.Application;

import androidx.room.Room;

import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.data.room.AppRoomDatabase;



public class App extends Application {
    private static App instance;
    private  AppRoomDatabase roomDatabase;
    private final Repository repository = new Repository();



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        roomDatabase = Room.databaseBuilder(this, AppRoomDatabase.class, "database")
                .build();
    }

    public Repository getRepository() {
        return repository;
    }

    public static App getInstance() {
        return instance;
    }

    public AppRoomDatabase getDatabase() {
        return roomDatabase;
    }

}

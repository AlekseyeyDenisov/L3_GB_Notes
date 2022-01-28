package ru.dw.gbnotes;

import android.app.Application;

import androidx.room.Room;

import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.data.SharedPreferencesCounter;
import ru.dw.gbnotes.data.room.AppRoomDatabase;



public class App extends Application {
    private static App instance;
    private  AppRoomDatabase roomDatabase;
    private  Repository repository;
    private SharedPreferencesCounter counter;



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        roomDatabase = Room.databaseBuilder(this, AppRoomDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        repository =  new Repository();
        counter = new SharedPreferencesCounter(this);
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

    public SharedPreferencesCounter getCounter(){
        return counter;
    }

}

package ru.dw.gbnotes;

import android.app.Application;

import ru.dw.gbnotes.data.Repository;


public class App extends Application {
    private static App sInstance = null;
    private final Repository repository = new Repository();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


    public static App get() {
        return sInstance;
    }

    public Repository getRepository() {
        return repository;
    }
}

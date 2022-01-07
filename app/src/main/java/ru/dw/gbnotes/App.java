package ru.dw.gbnotes;

import android.app.Application;
import android.content.Context;

import ru.dw.gbnotes.domain.Repository;

public class App extends Application {
    private Repository repository = new Repository();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    public Repository getRepository() {
        return repository;
    }
}

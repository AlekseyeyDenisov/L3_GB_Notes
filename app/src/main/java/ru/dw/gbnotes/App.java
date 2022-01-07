package ru.dw.gbnotes;

import android.app.Application;
import android.content.Context;

import ru.dw.gbnotes.domain.Repository;

public class App extends Application {
    private final Repository repository = new Repository();


    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    public Repository getRepository() {
        return repository;
    }
}

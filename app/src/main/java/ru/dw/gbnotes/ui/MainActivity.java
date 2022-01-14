package ru.dw.gbnotes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.R;
import ru.dw.gbnotes.ui.fragment.NoteListFragment;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment noteListFragment = new NoteListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_fragment_container,noteListFragment)
                .commit();

    }


}
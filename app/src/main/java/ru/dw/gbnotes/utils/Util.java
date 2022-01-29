package ru.dw.gbnotes.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;

import ru.dw.gbnotes.domain.model.NotesEntity;

public class Util {

    public static void systemToast(Context context, int id) {
        Toast.makeText(
                context,
                context.getString(id),
                Toast.LENGTH_SHORT).show();
    }

    public static NotesEntity newNote() {
        final Random random = new Random();
        NotesEntity notesEntity = new NotesEntity(
                random.nextLong(),
                "",
                "",
                ""
        );
        return notesEntity;
    }
}

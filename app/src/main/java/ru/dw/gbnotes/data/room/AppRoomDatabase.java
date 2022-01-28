package ru.dw.gbnotes.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.dw.gbnotes.domain.model.NotesEntity;

@Database(entities = {NotesEntity.class}, version = 1,exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract NotesDao notesEntity();
}

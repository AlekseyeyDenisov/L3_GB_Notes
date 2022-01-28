package ru.dw.gbnotes.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.dw.gbnotes.domain.model.NotesEntity;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM notesEntity")
    List<NotesEntity> getAll();

    @Query("SELECT * FROM notesentity WHERE id = :id")
    NotesEntity getById(long id);

    @Insert
    void insert(NotesEntity  notesEntity);

    @Update
    void update(NotesEntity notesEntity);

    @Delete
    void delete(NotesEntity notesEntity);
}

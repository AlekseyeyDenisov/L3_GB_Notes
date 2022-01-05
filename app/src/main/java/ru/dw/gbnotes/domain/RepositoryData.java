package ru.dw.gbnotes.domain;


import androidx.collection.ArrayMap;
import ru.dw.gbnotes.domain.model.NotesEntity;

public interface RepositoryData {

    ArrayMap<String,NotesEntity> getNoteData();
    void setItemNotes(String id, NotesEntity notesEntity);
    void deleteItemNotes(String id);
    void upDataItemNote(String id,NotesEntity notesEntity);
}

package ru.dw.gbnotes.data;

import java.util.ArrayList;

import ru.dw.gbnotes.domain.model.NotesEntity;

public class Repository implements RepositoryData {
    RepositoryData data = new CacheDataEntity();


    @Override
    public ArrayList<NotesEntity> getNoteData() {
        return data.getNoteData();
    }

    @Override
    public Boolean setItemNotes(NotesEntity notesEntity) {
        return data.setItemNotes(notesEntity);
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
       return data.deleteItemNotes(notesEntity);
    }

    @Override
    public Boolean upDataItemNote(NotesEntity notesEntity) {
       return data.upDataItemNote(notesEntity);
    }
}

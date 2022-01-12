package ru.dw.gbnotes.data;


import java.util.List;

import ru.dw.gbnotes.data.cache.CacheDataEntity;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.domain.model.RepositoryData;

public class Repository implements RepositoryData {
    RepositoryData data = new CacheDataEntity();

    @Override
    public List<NotesEntity> getNoteData() {
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

package ru.dw.gbnotes.domain;



import androidx.collection.ArrayMap;
import ru.dw.gbnotes.data.CacheDataEntity;
import ru.dw.gbnotes.domain.model.NotesEntity;

public class Repository implements RepositoryData {
    RepositoryData data = new CacheDataEntity();


    @Override
    public ArrayMap<String, NotesEntity> getNoteData() {
        return data.getNoteData();
    }

    @Override
    public void setItemNotes(String id, NotesEntity notesEntity) {
        data.setItemNotes(id, notesEntity);
    }

    @Override
    public void deleteItemNotes(String id) {
        data.deleteItemNotes(id);
    }

    @Override
    public void upDataItemNote(String id, NotesEntity notesEntity) {
        data.upDataItemNote(id, notesEntity);
    }
}

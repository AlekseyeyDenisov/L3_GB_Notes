package ru.dw.gbnotes.data;


import java.util.List;

import ru.dw.gbnotes.data.room.DBSQLRoomHelper;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.domain.model.RepositoryData;

public class Repository implements RepositoryData {

    RepositoryData data = new DBSQLRoomHelper();
    //RepositoryData data = new CacheDataEntity();

    @Override
    public List<NotesEntity> getAllNotes() {
        return data.getAllNotes();
    }

    @Override
    public Boolean saveItemNotes(NotesEntity notesEntity) {
        return data.saveItemNotes(notesEntity);
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

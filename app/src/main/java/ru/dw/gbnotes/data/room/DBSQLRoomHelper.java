package ru.dw.gbnotes.data.room;


import java.util.List;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.domain.model.RepositoryData;

public class DBSQLRoomHelper implements RepositoryData {


    private final AppRoomDatabase database = App.getInstance().getDatabase();


    @Override
    public List<NotesEntity> getAllNotes() {
        return database.notesEntity().getAll();
    }

    @Override
    public Boolean saveItemNotes(NotesEntity notesEntity) {
        database.notesEntity().insert(notesEntity);
        return true;
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
        database.notesEntity().delete(notesEntity);
        return true;
    }

    @Override
    public Boolean upDataItemNote(NotesEntity notesEntity) {
        database.notesEntity().update(notesEntity);
        return true;
    }
}

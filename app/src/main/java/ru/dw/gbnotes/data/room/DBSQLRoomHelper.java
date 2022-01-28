package ru.dw.gbnotes.data.room;


import java.util.ArrayList;
import java.util.List;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.domain.model.RepositoryData;

public class DBSQLRoomHelper implements RepositoryData {


    private final AppRoomDatabase database = App.getInstance().getDatabase();


    @Override
    public List<NotesEntity> getAllNotes() {
        List<NotesEntity> list = new ArrayList<>();
//
//        if (list != null){
//            return list;
//        }
        return list;
    }

    @Override
    public Boolean saveItemNotes(NotesEntity notesEntity) {
        //database.notesEntity().insert(notesEntity);
        return true;
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
        return null;
    }

    @Override
    public Boolean upDataItemNote(NotesEntity notesEntity) {
        return null;
    }
}

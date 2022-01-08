package ru.dw.gbnotes.data;


import java.util.ArrayList;

import ru.dw.gbnotes.domain.model.NotesEntity;

public interface RepositoryData {

    ArrayList<NotesEntity> getNoteData();
    Boolean setItemNotes(NotesEntity notesEntity);
    Boolean deleteItemNotes(NotesEntity notesEntity);
    Boolean upDataItemNote(NotesEntity notesEntity);
}

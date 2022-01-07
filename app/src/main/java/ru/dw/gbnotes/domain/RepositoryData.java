package ru.dw.gbnotes.domain;


import java.util.ArrayList;

import ru.dw.gbnotes.domain.model.NotesEntity;

public interface RepositoryData {

    ArrayList<NotesEntity> getNoteData();
    Boolean setItemNotes(NotesEntity notesEntity);
    Boolean deleteItemNotes(NotesEntity notesEntity);
    void upDataItemNote(NotesEntity notesEntity);
}

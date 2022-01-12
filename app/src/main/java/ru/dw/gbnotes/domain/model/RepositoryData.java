package ru.dw.gbnotes.domain.model;


import java.util.List;

public interface RepositoryData {

    List<NotesEntity> getNoteData();
    Boolean setItemNotes(NotesEntity notesEntity);
    Boolean deleteItemNotes(NotesEntity notesEntity);
    Boolean upDataItemNote(NotesEntity notesEntity);
}

package ru.dw.gbnotes.domain.model;


import java.util.List;

public interface RepositoryData {

    List<NotesEntity> getAllNotes();
    Boolean saveItemNotes(NotesEntity notesEntity);
    Boolean deleteItemNotes(NotesEntity notesEntity);
    Boolean upDataItemNote(NotesEntity notesEntity);
}

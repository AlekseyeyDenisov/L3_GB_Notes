package ru.dw.gbnotes.data.cache;

import java.util.ArrayList;
import java.util.List;

import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.domain.model.RepositoryData;

public class CacheDataEntity implements RepositoryData {
    private final List<NotesEntity> cache = new ArrayList<>();

    public CacheDataEntity() {
        this.cache.addAll(createDummyEmployeesData());
    }

    @Override
    public List<NotesEntity> getNoteData() {
        return cache;
    }

    @Override
    public Boolean setItemNotes(NotesEntity notesEntity) {
        return cache.add(notesEntity);
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
        return  cache.remove(notesEntity);
    }

    @Override
    public Boolean upDataItemNote(NotesEntity notesEntity) {return cache.add(notesEntity);}

    private static List<NotesEntity> createDummyEmployeesData() {
        final List<NotesEntity> listNote = new ArrayList<>();

        listNote.add(new NotesEntity(
                1L,
                "Заметка 1",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "06.01.2022"
        ));
        listNote.add(new NotesEntity(
                2L,
                "Заметка 2",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "07.01.2022"
        ));
        listNote.add(new NotesEntity(
                3L,
                "Заметка 3",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "08.01.2022"
        ));
        listNote.add(new NotesEntity(
                4L,
                "Заметка 4",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "09.01.2022"
        ));
        listNote.add(new NotesEntity(
                5L,
                "Заметка 5",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "10.01.2022"
        ));

        return listNote;

    }


}

package ru.dw.gbnotes.data;

import java.util.ArrayList;

import ru.dw.gbnotes.domain.model.NotesEntity;

public class CacheDataEntity implements RepositoryData {
    private final ArrayList<NotesEntity> cacheMap;

    public CacheDataEntity() {
        this.cacheMap = createDummyEmployeesData();
    }

    @Override
    public ArrayList<NotesEntity> getNoteData() {
        return cacheMap;
    }

    @Override
    public Boolean setItemNotes(NotesEntity notesEntity) {
        return cacheMap.add(notesEntity);
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
        try {
            return cacheMap.remove(notesEntity);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        return false;
    }

    @Override
    public void upDataItemNote(NotesEntity notesEntity) {

    }


    private static ArrayList<NotesEntity> createDummyEmployeesData() {
        final ArrayList<NotesEntity> arrayMap = new ArrayList<>();

        arrayMap.add(new NotesEntity(
                "1",
                "Заметка 1",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "06.01.2022"
        ));
        arrayMap.add(new NotesEntity(
                "2",
                "Заметка 2",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "07.01.2022"
        ));
        arrayMap.add(new NotesEntity(
                "3",
                "Заметка 3",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "08.01.2022"
        ));
        arrayMap.add(new NotesEntity(
                "4",
                "Заметка 4",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "09.01.2022"
        ));
        arrayMap.add(new NotesEntity(
                "5",
                "Заметка 5",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "10.01.2022"
        ));

        return arrayMap;

    }


}

package ru.dw.gbnotes.data;

import androidx.collection.ArrayMap;

import ru.dw.gbnotes.domain.RepositoryData;
import ru.dw.gbnotes.domain.model.NotesEntity;

public class CacheDataEntity implements RepositoryData {
    private ArrayMap<String, NotesEntity> cacheMap;

    @Override
    public ArrayMap<String, NotesEntity> getNoteData() {
        return cacheMap = createDummyEmployeesData();
    }

    @Override
    public void setItemNotes(String id, NotesEntity notesEntity) {
        cacheMap.put(id, notesEntity);
    }

    @Override
    public void deleteItemNotes(String id) {
        cacheMap.remove(id);
    }

    @Override
    public void upDataItemNote(String id, NotesEntity notesEntity) {
        cacheMap.replace(id, notesEntity);
    }

    private static ArrayMap<String, NotesEntity> createDummyEmployeesData() {
        final ArrayMap<String, NotesEntity> arrayMap = new ArrayMap<>();

        arrayMap.put("1", new NotesEntity(
                "1",
                "Заметка 1",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "06.01.2022"
        ));
        arrayMap.put("2", new NotesEntity(
                "2",
                "Заметка 2",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "07.01.2022"
        ));
        arrayMap.put("3", new NotesEntity(
                "3",
                "Заметка 3",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "08.01.2022"
        ));
        arrayMap.put("4", new NotesEntity(
                "4",
                "Заметка 4",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "09.01.2022"
        ));
        arrayMap.put("5", new NotesEntity(
                "5",
                "Заметка 5",
                " Значимость этих проблем настолько очевидна, что консультация ",
                "10.01.2022"
        ));

        return arrayMap;

    }


}

package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.OnNoteListener;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.ui.adapter.NoteAdapter;

public class NoteListFragment extends Fragment implements OnNoteListener {
    NoteAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    Repository repository;

    private Controller controller;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement  NoteListFragment.Controller");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        fab = view.findViewById(R.id.fragment_note_list_fab);
        repository = App.get().getRepository();
        initRecycler(view);
        newNote();

    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.setData(repository.getNoteData());
    }

    private void initRecycler(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_note_list_ecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoteAdapter();
        adapter.setOnDeleteClickListener(this);
        getItemTouchHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

    }

    private void newNote() {
        fab.setOnClickListener(v -> {
            final Random random = new Random();
            NotesEntity notesEntity = new NotesEntity(
                    random.nextLong(),
                    "",
                    "",
                    ""
            );
            controller.showNoteDetails(notesEntity);
        });
    }


    @Override
    public void onDeleteNoteItem(NotesEntity notesEntity) {
        List<NotesEntity> repoData = repository.getNoteData();
        for (int i = 0; i < repoData.size(); i++) {
            if (repoData.get(i).equals(notesEntity)) {
                repository.getNoteData().remove(repoData.get(i));
                adapter.deleteItem(i);
            }
        }
    }

    @Override
    public void onUpDataNoteItem(NotesEntity notesEntity) {
//        Intent intent = new Intent(getContext(), NoteActivity.class);
//        intent.putExtra(NoteActivity.NOTE_EXTRA_KEY, notesEntity);
//        startActivity(intent);
        controller.showNoteDetails(notesEntity);
    }


    private ItemTouchHelper getItemTouchHelper() {
        return new ItemTouchHelper((new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                List<NotesEntity> list = repository.getNoteData();
                repository.getNoteData().remove(list.get(viewHolder.getAdapterPosition()));
                adapter.deleteItem(viewHolder.getAdapterPosition());


            }
        }));
    }

    public interface Controller {
        void showNoteDetails(NotesEntity notesEntity);
    }
}

package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import ru.dw.gbnotes.utils.Util;

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
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        fab = view.findViewById(R.id.fragment_note_list_fab);
        fab.setOnClickListener(v -> newNote());
        repository = App.getInstance().getRepository();
        initRecycler(view);


    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.setData(repository.getAllNotes());
    }

    private void initRecycler(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_note_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoteAdapter();
        adapter.setOnDeleteClickListener(this);
        getItemTouchHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

    }

    private void newNote() {
        final Random random = new Random();
        NotesEntity notesEntity = new NotesEntity(
                random.nextLong(),
                "",
                "",
                ""
        );
        controller.showNoteDetails(notesEntity);
    }


    @Override
    public void onDeleteNoteItem(NotesEntity notesEntity) {
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.attention_title_alert_dialog)
                .setMessage(R.string.message_delete_item_note_alert_dialog)
                .setPositiveButton(R.string.yes, (dialog, which) -> {

                    List<NotesEntity> repoData = repository.getAllNotes();
                    for (int i = 0; i < repoData.size(); i++) {
                        if (repoData.get(i).getId().equals(notesEntity.getId())) {
                            adapter.deleteItem(i);
                            repository.deleteItemNotes(notesEntity);
                            Util.systemToast(requireContext(), R.string.message_delete_data_item);
                        }
                    }

                })
                .setNegativeButton(R.string.no, ((dialog, which) -> {

                }))
                .show();

    }

    @Override
    public void onUpDataNoteItem(NotesEntity notesEntity) {
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
                List<NotesEntity> list = repository.getAllNotes();
                repository.deleteItemNotes(list.get(viewHolder.getAdapterPosition()));
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }));
    }

    public interface Controller {
        void showNoteDetails(NotesEntity notesEntity);
    }

}

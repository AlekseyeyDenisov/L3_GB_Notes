package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;


public class CounterFragment extends Fragment {
    private Controller controller;
    TextView counterResult;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement  NoteFragmentDetail.Controller");
        }

    }

    public static CounterFragment newInstance() {
        CounterFragment counterFragment = new CounterFragment();
        Bundle bundle = new Bundle();
        counterFragment.setArguments(bundle);
        return counterFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_counter,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        counterResult = view.findViewById(R.id.fragment_counter_counter_result);
        String resultCounter =  App.getInstance().getCounter().getCount().toString();
        counterResult.setText(resultCounter);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_update_note_menu_detail: {controller.detailFinish();};
                return true;
            case R.id.delete_note_menu_detail: {controller.detailFinish();};
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

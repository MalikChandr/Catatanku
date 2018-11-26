package project.com.notes.notes.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import project.com.notes.notes.adapters.NotesAdapter;
import project.com.notes.notes.database.DatabaseAdapter;
import project.com.notes.notes.models.Notes;
import project.com.notes.notes.R;

public class NotesList extends Fragment {

    GridView gridList;
    DatabaseAdapter dbAdpater;
    NotesAdapter notesAdapter;

    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_list, container, false);

        dbAdpater = new DatabaseAdapter(getActivity());

        ArrayList<Notes> arrayList;
        arrayList       = dbAdpater.getNotesData();
        notesAdapter    = new NotesAdapter(getActivity(), arrayList);
        gridList        = view.findViewById(R.id.gridList);
        gridList.setAdapter(notesAdapter);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Catatan");
        setHasOptionsMenu(true);

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notes_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_addNotes){
            Intent intent = new Intent(getActivity(), NotesAdd.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package project.com.notes.notes.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import project.com.notes.notes.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new NotesList());

        bottomNavigationView = findViewById(R.id.btn_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.catatan:
                        fragment = new NotesList();
                        break;
                    case R.id.recorder :
                        fragment = new RecordList();
                        break;
                    case R.id.diary :
                        fragment = new DiaryList();
                        break;

                    case R.id.account_menu :
                        fragment = new DrawingList();
                        break;
                }
                return loadFragment(fragment);
            }
        });

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

package project.com.notes.notes.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import io.paperdb.Paper;
import project.com.notes.notes.database.DatabaseAdapter;
import project.com.notes.notes.R;

public class NotesAdd extends AppCompatActivity {

    EditText edtTitle, edtDescription;
    DatabaseAdapter dbAdapter;
    String currentTime, currentDate;
    LinearLayout layout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_add);

        dbAdapter = new DatabaseAdapter(getApplicationContext());

        Paper.init(this);

        edtTitle        = findViewById(R.id.edtJudul);
        edtDescription  = findViewById(R.id.edtContent);


        toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);

    }

    public String getCurrentTime(){
        Calendar calendar       = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00"));
        Date currentLocalTime   = calendar.getTime();
        SimpleDateFormat date   = new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        currentTime             = date.format(currentLocalTime);
        return currentTime;
    }

    public String getCurrentDate(){
        Date date               = Calendar.getInstance().getTime();
        SimpleDateFormat df     = new SimpleDateFormat("dd-MM-yyyy");
        currentDate             = df.format(date);
        return currentDate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add){
            final String notesTitle, notesDesc, notesDate;

            notesTitle      = edtTitle.getText().toString();
            notesDesc       = edtDescription.getText().toString();
            notesDate       = getCurrentDate()+" "+getCurrentTime();

            if(notesTitle.equals("")){
                Toast.makeText(this, "Judul catatan belum diisi", Toast.LENGTH_SHORT).show();
            }else if (notesDesc.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Catatan masih kosong, lanjutkan menyimpan ?");
                builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            Paper.book().write("title", notesTitle);
                            Paper.book().write("content", notesDesc);

                            dbAdapter.insertNotesData(notesTitle, notesDesc, notesDate);
                            Toast.makeText(NotesAdd.this, "Catatan tersimpan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }catch(Exception e){
                            Toast.makeText(NotesAdd.this, "Gagagl tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else{
                try{
                    Paper.book().write("title", notesTitle);
                    Paper.book().write("content", notesDesc);

                    dbAdapter.insertNotesData(notesTitle, notesDesc, notesDate);
                    Toast.makeText(this, "Catatan tersimpan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }



            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
    }
}

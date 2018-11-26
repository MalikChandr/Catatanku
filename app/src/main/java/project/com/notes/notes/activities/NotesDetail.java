package project.com.notes.notes.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import project.com.notes.notes.R;
import project.com.notes.notes.database.DatabaseAdapter;

public class NotesDetail extends AppCompatActivity {

    EditText edtTitle, edtDescription;
    String title, description, dateCreate, dateUpdate, currentDate, currentTime;
    int id;
    FloatingActionButton fab;
    boolean statusEdit = true;
    ScrollView layout;
    LinearLayout layout_dtl;
    DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_detail);

        dbAdapter       = new DatabaseAdapter(getApplicationContext());

        edtTitle        = findViewById(R.id.edtJudulUpdate);
        edtDescription  = findViewById(R.id.edtContentUpdate);
        fab             = findViewById(R.id.fab);

        layout          = findViewById(R.id.scroll);
        layout_dtl      = findViewById(R.id.layout_detail);

        id              = getIntent().getExtras().getInt("id");
        title           = getIntent().getExtras().getString("title");
        description     = getIntent().getExtras().getString("description");
        dateCreate      = getIntent().getExtras().getString("date_create");
        dateUpdate      = getIntent().getExtras().getString("date_update");

        edtTitle.setText(title);
        edtDescription.setText(description);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statusEdit){
                    edtTitle.setBackgroundColor(Color.parseColor("#e9e9e9"));
                    layout.setBackgroundColor(Color.parseColor("#e9e9e9"));
                    layout_dtl.setBackgroundColor(Color.parseColor("#e9e9e9"));
                    edtTitle.setEnabled(true);
                    edtDescription.setEnabled(true);
                    edtTitle.setFocusableInTouchMode(true);
                    edtDescription.setFocusableInTouchMode(true);
                    fab.setImageResource(R.drawable.ic_send_black_24dp);
                    statusEdit = false;
                }else{
                    functionUpdate();
                }
            }
        });
    }

    public void functionUpdate(){
        String title        = edtTitle.getText().toString();
        String desc         = edtDescription.getText().toString();
        String dateUpdate   = getCurrentDate()+" "+getCurrentTime();

        try {
            dbAdapter.updateNotes(String.valueOf(id), title, desc, dateUpdate);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(NotesDetail.this, "Notes diperbarui", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(NotesDetail.this, "Notes gagal di perbarui", Toast.LENGTH_SHORT).show();
        }
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
    public void onBackPressed() {
        if (description.equals(edtDescription.getText().toString()) && title.equals(edtTitle.getText().toString())){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            Log.d("MASUK", "SINI");
        }else{
            Log.d("MASUK", "SINI 2");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Perubahan belum disimpan ?");
            alertDialogBuilder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    functionUpdate();
                }
            });

            alertDialogBuilder.setNegativeButton("Buang", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

}

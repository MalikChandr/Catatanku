package project.com.notes.notes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import project.com.notes.notes.database.DatabaseContract.*;
import project.com.notes.notes.models.Notes;

public class DatabaseAdapter {
    private final Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DatabaseAdapter(Context context) {
        this.context = context;

    }

    public void insertNotesData(String title, String description, String date){
        databaseHelper = new DatabaseHelper(context);
        db             = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_NAME_TITLE, title);
        values.put(NotesTable.COLUMN_NAME_DESCRIPTION, description);
        values.put(NotesTable.COLUMN_NAME_DATE_CREATE, date);
        values.put(NotesTable.COLUMN_NAME_DATE_UPDATE, 0);

        db.insert(NotesTable.TABLE_NAME, null, values);
        db.close();
        databaseHelper.close();
    }

    public void insertDiaryData(String title, String description, String date, String password){
        databaseHelper = new DatabaseHelper(context);
        db             = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesTable.TITLE_DIARY, title);
        values.put(NotesTable.DESC_DIARY, description);
        values.put(NotesTable.CREATE_DIARY, date);
        values.put(NotesTable.UPDATE_DIARY, 0);
        values.put(NotesTable.PASSWORD, password);

        db.insert(NotesTable.TABLE_NAME2, null, values);
        db.close();
        databaseHelper.close();
    }

    public ArrayList<Notes> getNotesData(){
        databaseHelper = new DatabaseHelper(context);
        db             = databaseHelper.getReadableDatabase();

        ArrayList<Notes> notes = new ArrayList<>();
        int notesId;
        String notesTitle, notesDescription, notesDateCreate, notesDateUpdate;

        String orderById =
                NotesTable._ID+ " DESC";

        Cursor cursor  = db.query(NotesTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                orderById);

        while (cursor.moveToNext()){
            notesId             = cursor.getInt(cursor.getColumnIndex(NotesTable._ID));
            notesTitle          = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_TITLE));
            notesDescription    = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_DESCRIPTION));
            notesDateCreate     = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_DATE_CREATE));
            notesDateUpdate     = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_DATE_UPDATE));

            notes.add(new Notes(notesId, notesTitle, notesDescription, notesDateCreate, notesDateUpdate));
        }
        cursor.close();
        db.close();
        databaseHelper.close();

        return notes;
    }

//    public ArrayList<Notes> getDiaryData(){
//        databaseHelper = new DatabaseHelper(context);
//        db             = databaseHelper.getReadableDatabase();
//
//        ArrayList<Notes> notes = new ArrayList<>();
//        int notesId;
//        String notesTitle, notesDescription, notesDateCreate, notesDateUpdate;
//
//        String orderById =
//                NotesTable._ID+ " DESC";
//
//        Cursor cursor  = db.query(NotesTable.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                orderById);
//
//        while (cursor.moveToNext()){
//            notesId             = cursor.getInt(cursor.getColumnIndex(NotesTable._ID));
//            notesTitle          = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_TITLE));
//            notesDescription    = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_DESCRIPTION));
//            notesDateCreate     = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_DATE_CREATE));
//            notesDateUpdate     = cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_NAME_DATE_UPDATE));
//
//            notes.add(new Notes(notesId, notesTitle, notesDescription, notesDateCreate, notesDateUpdate));
//        }
//        cursor.close();
//        db.close();
//        databaseHelper.close();
//
//        return notes;
//    }


    public void updateNotes(String id, String title, String description, String dateUpdate){
        databaseHelper  = new DatabaseHelper(context);
        db              = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_NAME_TITLE, title);
        values.put(NotesTable.COLUMN_NAME_DESCRIPTION, description);
        values.put(NotesTable.COLUMN_NAME_DATE_UPDATE, dateUpdate);

        String selection = NotesTable._ID + " LIKE ?";
        String[] selectionArgs = {id};

        db.update(NotesTable.TABLE_NAME,values,selection,selectionArgs);
        db.close();
        databaseHelper.close();
    }
}

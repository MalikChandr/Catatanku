package project.com.notes.notes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import project.com.notes.notes.database.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes.db";

    private static final String SQL_CREATE_TABLE_NOTES =
            "CREATE TABLE IF NOT EXISTS " + NotesTable.TABLE_NAME + " (" +
                    NotesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    NotesTable.COLUMN_NAME_TITLE + " TEXT," +
                    NotesTable.COLUMN_NAME_DESCRIPTION+ " TEXT," +
                    NotesTable.COLUMN_NAME_DATE_CREATE+ " TEXT, " +
                    NotesTable.COLUMN_NAME_DATE_UPDATE+ " TEXT)";

    private static final String SQL_DELETE_TABLE_NOTES =
            "DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE_NOTES);
    }
}

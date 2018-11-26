package project.com.notes.notes.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    private DatabaseContract(){}

    public static class NotesTable implements BaseColumns{
        public static final String TABLE_NAME               = "tb_notes";
        public static final String COLUMN_NAME_TITLE        = "title";
        public static final String COLUMN_NAME_DESCRIPTION  = "description";
        public static final String COLUMN_NAME_DATE_CREATE  = "date_create";
        public static final String COLUMN_NAME_DATE_UPDATE  = "date_update";

    }
}

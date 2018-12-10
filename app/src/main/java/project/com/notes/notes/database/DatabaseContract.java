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


        public static final String TABLE_NAME2              = "tb_diary";
        public static final String TITLE_DIARY              = "title";
        public static final String DESC_DIARY               = "description";
        public static final String CREATE_DIARY             = "create";
        public static final String UPDATE_DIARY             = "update";
        public static final String PASSWORD                 = "password";

    }
}

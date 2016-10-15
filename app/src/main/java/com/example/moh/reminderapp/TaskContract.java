package com.example.moh.reminderapp;

/**
 * Created by moh on 15/10/2016.
 */
import android.provider.BaseColumns;

/**
 * Created by moh on 14/09/2016.
 */
public class TaskContract {
    public static final String DB_NAME = "com.example.moh.reminderapp.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "mumber";

        public static final String COL_TASK_TITLE = "nom";
        public static final String COL_TASK_TITLE2 = "prenom";
        public static final String COL_TASK_TITLE3 = "email";
        public static final String COL_TASK_TITLE4 = "password";


    }
}

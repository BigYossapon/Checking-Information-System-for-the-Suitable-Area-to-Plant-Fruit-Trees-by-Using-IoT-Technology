package com.example.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "moviestore";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "movies";
    public static final String COL_NAME = "title";
    public static final String COL_DESC = "description";
    public static final String COL_COVER = "cover";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " VARCHAR, " + COL_DESC + " TEXT, "
                + COL_COVER + " VARCHAR);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_NAME + ", " + COL_DESC
                + ", " + COL_COVER + ") VALUES ('American Horror Story Season 1', 'The first season of American Horror Story takes place in a haunted house', '1.png');");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

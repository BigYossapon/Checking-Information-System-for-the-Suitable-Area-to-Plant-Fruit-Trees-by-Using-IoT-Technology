package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//DB
import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class historyvalueActivity extends AppCompatActivity {


    SQLiteDatabase sqliteMyDB;
    MyDbHelper myDataHelper;
    Cursor myDBCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);
        final Button addNew = (Button)findViewById(R.id.Addbtn);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showPage = new Intent(historyvalueActivity.this,historyvalueActivity.class);
                startActivity(showPage);
            }
        });
        //DB
        ListView listViewMovies = (ListView)findViewById(R.id.listView);
        myDataHelper = new MyDbHelper(this);

        sqliteMyDB = myDataHelper.getWritableDatabase();

        myDBCursor = sqliteMyDB.rawQuery("SELECT " + MyDbHelper.COL_NAME + ", "  + MyDbHelper.COL_DESC
                + ", " + MyDbHelper.COL_COVER + " FROM " + MyDbHelper.TABLE_NAME, null);

        ArrayList<String> dirArray = new ArrayList<String>();
        myDBCursor.moveToFirst();

        while ( !myDBCursor.isAfterLast() ){
            dirArray.add(myDBCursor.getString(myDBCursor.getColumnIndex(MyDbHelper.COL_NAME)) + "\n"
                    + "Detail : " + myDBCursor.getString(myDBCursor.getColumnIndex(MyDbHelper.COL_DESC)));
            myDBCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dirArray);
        listViewMovies.setAdapter(adapterDir);
    }
    @Override
    public void onPause() {
        super.onPause();
        myDataHelper.close();
        sqliteMyDB.close();
    }


}

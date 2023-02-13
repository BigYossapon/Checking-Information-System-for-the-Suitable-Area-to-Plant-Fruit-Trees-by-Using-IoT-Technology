package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.widget.Toast;

public class realtimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);

        this.setTitle("Add new data");
        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showPage = new Intent(realtimeActivity.this, MainActivity.class);
                startActivity(showPage);
            }
        });

        Button addData = (Button) findViewById(R.id.submitBtn);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AddNew()) {
                    Intent showPage = new Intent(realtimeActivity.this, MainActivity.class);
                    startActivity(showPage);
                }
            }
        });
    }

    private boolean AddNew() {
        final EditText editTextTitle = (EditText) findViewById(R.id.editText);
        final EditText editTextDetail = (EditText) findViewById(R.id.editText2);

        final AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
        AlertDialog alertMsg = alertBox.create();

        //Check Filter
        if (editTextTitle.getText().length() == 0) {
            alertMsg.setMessage("Please insert Movie's Title");
            alertMsg.show();
            editTextTitle.requestFocus();
            return false;
        }
        if (editTextDetail.getText().length() == 0) {
            alertMsg.setMessage("Please insert Movie's Detail");
            alertMsg.show();
            editTextDetail.requestFocus();
            return false;
        }
        MyDbHelper myDataHelper = new MyDbHelper(this);

        SQLiteDatabase sqliteMyDB = myDataHelper.getWritableDatabase();

        ContentValues Val = new ContentValues();
        Val.put("title", editTextTitle.getText().toString());
        Val.put("description", editTextDetail.getText().toString());

        long rows = sqliteMyDB.insert(myDataHelper.TABLE_NAME, null, Val);

        sqliteMyDB.close();
        Toast.makeText(realtimeActivity.this,"Success!",Toast.LENGTH_SHORT).show();

        return false;

    }

}



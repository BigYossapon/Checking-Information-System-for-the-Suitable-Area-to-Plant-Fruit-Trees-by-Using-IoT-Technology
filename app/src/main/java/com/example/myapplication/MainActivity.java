package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.REALTIME);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gorealtimeActivity();
            }
        });
        button = (Button) findViewById(R.id.HISTORY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gohistoryvalueActivity();
            }
        });
        button = (Button) findViewById(R.id.CALCURATOR);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gocalcurateActivity();
            }
        });
        button = (Button) findViewById(R.id.HOWTOPLANT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gohowtoplantActivity();
            }
        });
    }

    public void gorealtimeActivity() {
        Intent intent = new Intent(this, realtimeActivity.class);
        startActivity(intent);
    }
    public void gohistoryvalueActivity() {
        Intent intent = new Intent(this, historyvalueActivity.class);
        startActivity(intent);
    }
    public void gocalcurateActivity() {
        Intent intent = new Intent(this, calcurateActivity.class);
        startActivity(intent);
    }
    public void gohowtoplantActivity() {
        Intent intent = new Intent(this, howtoplantActivity.class);
        startActivity(intent);
    }
}

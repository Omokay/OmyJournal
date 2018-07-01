package com.example.david.omy_journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SimpleCursorAdapter adapter;
    ListView viewall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, EntryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void allEntries(){
        Cursor res = databaseHelper.getAllEntries();
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(Home.this,"Error, Nothing found", Toast.LENGTH_LONG).show();
            return;
        } else{
            String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.TITLE};
            int [] to = new int[]{R.id.idText, R.id.titleText};
            adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.custom_listview, res, from, to, 0);
            viewall = (ListView)findViewById(R.id.entryList);
            viewall.setAdapter(adapter);
        }

    }

    public void onStart() {
        super.onStart();
        allEntries();

    }

}

package com.example.david.omy_journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EntryActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText titleEdit, bodyEdit;
    Button addentry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        databaseHelper = new DatabaseHelper(this);

        titleEdit = (EditText)findViewById(R.id.titleInput);
        bodyEdit = (EditText)findViewById(R.id.bodyInput);

        addentry = (Button)findViewById(R.id.saveButton);
        saveData();
    }

    public void saveData(){
        addentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted = databaseHelper.addEntry(titleEdit.getText().toString(),
                       bodyEdit.getText().toString());

               if (isInserted = true ){
                   Toast.makeText(EntryActivity.this, "Data has been saved", Toast.LENGTH_LONG).show();
               } else{
                   Toast.makeText(EntryActivity.this, "Data did not save", Toast.LENGTH_LONG).show();
               }

            }
        });
    }
}

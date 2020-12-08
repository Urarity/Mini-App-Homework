package com.example.editablelistview;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        ListView listView = findViewById(R.id.ListView);
        myDB = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0){
            String text = "The Database was empty  :(";
            Toast.makeText(ViewListContents.this, text, Toast.LENGTH_LONG).show();
        }else {
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}

package com.example.editablelistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// CodingWithMitch = youtube.com/watch?v=SK98ayjhk1E

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd, btnView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();

                // If their is nothing in the text box it will throw an error
                if(editText.length() != 0){
                    AddData(newEntry);
                    editText.setText("");
                }else {
                    String text = "You must put something in the text field!";
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void AddData(String newEntry){
        boolean insertData = myDB.addData(newEntry);

        if(insertData==true){
            String text = "Successfully Entered Data!";
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
        } else{
            String text = "Something went wrong";
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
        }
    }
}

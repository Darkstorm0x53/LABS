package com.example.lab10_sql_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name, location, designation;
    Button save;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        designation = findViewById(R.id.designation);
        save = findViewById(R.id.btnsve);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n,d,l;
                n = name.getText().toString() + "\n";
                l = location.getText().toString() + "\n";
                d = designation.getText().toString();

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(n, l, d);

                intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
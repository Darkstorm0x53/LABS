package com.example.lab10_sql_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    ListView listView;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        DbHandler db = new DbHandler(this);

        ArrayList<HashMap<String, String>> userList = db.getUsers();
        listView = findViewById(R.id.user_list);
        back = findViewById(R.id.btnBack);

        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this,
                userList, R.layout.list_row,
                new String[]{"name", "designation", "location" },
                new int[]{R.id.name, R.id.designation, R.id.location});
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
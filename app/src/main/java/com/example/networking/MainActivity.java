package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String[] mountainNames = {"Katt","Hund","Kanin"};

    private ArrayList<String> listData = new ArrayList<>(Arrays.asList(mountainNames));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_item_textview,R.id.list_item_textview, listData);

        ListView my_listView=(ListView) findViewById(R.id.list_view);

        my_listView.setAdapter(adapter);

        my_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Det fungerade!", Toast.LENGTH_SHORT).show();
            }
        });



    }
}

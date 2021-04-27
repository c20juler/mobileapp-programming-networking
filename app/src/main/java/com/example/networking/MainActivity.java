package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    /*To do:
    Add a `ListView` to your layout //DONE
    Add a `ArrayList<Mountain>` as a member variable in your activity //Convert from String to Mountain (so it refers to the mountain class.../webservice?)
    Add a `ArrayAdapter<Mountain>` as a member variable in your activity //Convert to a Mountain adapter from String...
    Use `JsonTask` to fetch data from our json web service
    Add items to your list of mountains by parsing the json data Hint: use `adapter.notifyDataSetChanged();` from within `onPostExecute(String json)` to notify the adapter that the content of the ArrayList has been updated.
    Display the names of the mountains in the `ListView` Hint: override `toString()` in your Mountain class
    When tapping a Mountain name one of three things should happen
    Display Mountain name and 2 other properties as a Toast View
    Display Mountain name and 2 other properties as a Snackbar View
    Display Mountain name and 2 other properties in a separate activity as three separate views
    Write a short report where you explain the things that you have done
    */

    /*Remove this (replaced by webservice)*/
    private String[] mountainNames = {"Matterhorn","Mont Blanc","Denali"};

    /*Convert ArrayList<String> to ArrayList<Mountain>...?*/
    private ArrayList<String> listData = new ArrayList<>(Arrays.asList(mountainNames));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

        /*Followed dugga "adapter" example tutorial... Must replace ArrayAdapter<String> with ArrayAdapter<Mountain> for dugga 6...- använda json med en type adapter*/
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
    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            Log.d("TAG ==>", json);
            /*Continiue writing code here...*/
            Gson gson = new Gson();

        }
    }
}
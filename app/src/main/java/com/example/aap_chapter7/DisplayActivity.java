package com.example.aap_chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    //referencing
    private ListView lstDictionary;

    private Map<String, String> dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lstDictionary = findViewById(R.id.lstDictionary);
        dictionary = new HashMap<>();

        //read all words from word.txt file
        readFromFile();

        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new ArrayList<String>(dictionary.keySet()));

        lstDictionary.setAdapter(adapter);

        lstDictionary.setOnItemClickListener(this);

    }

    private void readFromFile()
    {
        try{
            FileInputStream fileInputStream = openFileInput("words.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String line="";

            while ((line=br.readLine()) != null)
            {
                String[] parts = line.split("->");
                dictionary.put(parts[0],parts[1]);
            }


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Getting the current position
        String key = parent.getItemAtPosition(position).toString();
       //Getting the meaning of curremnt position key
        String meaning = dictionary.get(key);

        //Intent will call meaning from dictionaryactivity
        Intent intent = new Intent(DisplayActivity.this, MainActivity.class);

        //we have to pass the
        intent.putExtra("meaning",meaning);
        startActivity(intent);
    }
}

package com.example.noah.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner dropdownmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdownmenu = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<>();
        list.add("Liberal");
        list.add("Socialist");
        list.add("Conservative");
        list.add("Libertarian");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        dropdownmenu.setAdapter(adapter);
        dropdownmenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = parent.getItemAtPosition(position).toString();
                TextView mTextView = (TextView) findViewById(R.id.newsResults);
                if (itemValue.equals("Liberal")) {
                    mTextView.setText("Liberal news goes here from API");
                }
                if (itemValue.equals("Conservative")) {
                    mTextView.setText("Conservative news goes here from API");
                }
                if (itemValue.equals("Socialist")) {
                    mTextView.setText("Socialist news goes here from API");
                }
                if (itemValue.equals("Libertarian")) {
                    mTextView.setText("Libertarian news goes here from API");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}



package com.example.noah.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    Spinner dropdownmenu;
    List<String> toReturn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        dropdownmenu = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<>();
        //Add choices to spinner
        list.add("<Choose a bias>");
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
                    //toReturn.clear();
                    getNews("https://newsapi.org/v2/everything?domains=thinkprogress.org,vox.com,huffingtonpost.com,slate.com,dailykos.com,msnbc.com,cnn.com&language=en&apiKey=df8268ee20df4c1b948e47bcaf26df49", "Liberal");
                    /*String result = "";
                    for (int i = 0; i < toReturn.size(); i++) {
                        result += toReturn.get(i) + "\n\n";
                    }
                    mTextView.setText(result);*/
                }
                if (itemValue.equals("Conservative")) {

                    //toReturn.clear();
                    getNews("https://newsapi.org/v2/everything?domains=nationalreview.com,hotair.com,dailycaller.com,foxnews.com,theblaze.com,dailywire.com&apiKey=df8268ee20df4c1b948e47bcaf26df49", "Conservative");
                    /*String result = "";
                    for (int i = 0; i < toReturn.size(); i++) {
                        result += toReturn.get(i) + "\n\n";
                    }
                    mTextView.setText(result);*/
                }
                if (itemValue.equals("Socialist")) {

                    //toReturn.clear();
                    getNews("https://newsapi.org/v2/everything?domains=morningstar.uk.co,wsws.org&apiKey=df8268ee20df4c1b948e47bcaf26df49", "Socialist");
                    /*String result = "";
                    for (int i = 0; i < toReturn.size(); i++) {
                        result += toReturn.get(i) + "\n\n";
                    }
                    mTextView.setText(result);*/
                }
                if (itemValue.equals("Libertarian")) {
                    //toReturn.clear();
                    getNews("https://newsapi.org/v2/everything?domains=reason.com,cato.org&apiKey=df8268ee20df4c1b948e47bcaf26df49", "Libertarian");
                    /*String result = "";
                    for (int i = 0; i < toReturn.size(); i++) {
                        result += toReturn.get(i) + "\n\n";
                    }
                    mTextView.setText(result);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getNews(final String url,final String type) {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                JSONArray articles = response.getJSONArray("articles");
                                List<String> articleList = new ArrayList<>();
                                for (int i = 0; i < articles.length(); i++) {
                                    JSONObject jsonObject = articles.getJSONObject(i);
                                    articleList.add(jsonObject.getString("url"));
                                }
                                TextView mTextView = (TextView) findViewById(R.id.newsResults);
                                String result = "";
                                for (int i = 0; i < articleList.size(); i++) {
                                    result += articleList.get(i) + "\n\n";
                                }
                                mTextView.setText(result);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    //Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.support.v7.widget.SearchView.SearchAutoComplete;
public class MainActivity extends Activity {

    private ImageView sofwerxLogoIV;
    private TextView searchViewTextDescriptTV;
    private SearchView searchViewSV;
    private SearchAutoComplete searchAutoCompleteSAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //handle MainActivity objects
        sofwerxLogoIV = (ImageView) findViewById(R.id.sofwerxLogo);
        searchViewTextDescriptTV = (TextView) findViewById(R.id.textViewSVDescript);
        searchViewSV = (SearchView) findViewById(R.id.searchView);
        searchAutoCompleteSAC = (SearchAutoComplete) findViewById(R.id.searchView);

        getSearchIntent();


    }

    //Source:
    //  https://developer.android.com/guide/topics/search/search-dialog#TheBasics
    //get intent and verify action
    public void getSearchIntent(){
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String queryString = intent.getStringExtra(SearchManager.QUERY);

            //This is where we can include a function that does a query:
            //For Example:
                //searchPDFDirectory(query);
        }

    }

}

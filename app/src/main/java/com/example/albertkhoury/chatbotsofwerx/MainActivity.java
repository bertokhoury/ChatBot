package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;


public class MainActivity extends Activity{
    PDFView pdfView;
    ImageView sofwerxLogoIV;
    Button goToHelpB;
    Button tmpButton;
    Button searcherB;
    EditText searchInputET;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        searchInputET = (EditText) findViewById(R.id.editText);
        sofwerxLogoIV = (ImageView) findViewById(R.id.sofwerxLogo);
        goToHelpB = (Button)findViewById(R.id.goToHelpButton);
        tmpButton = (Button)findViewById(R.id.tmpButtonB);
        searcherB = (Button) findViewById(R.id.searcher);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        //editor.putString("search_key", String.valueOf(sharedPreferences));

    }

    public void saveEditTextString(SharedPreferences sharedPreferences){

        //save Edit Text into SharedPreferences
        this.sharedPreferences = getSharedPreferences(String.valueOf(searchInputET), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("search_key", String.valueOf(this.sharedPreferences));
        editor.commit();
        editor.apply();

        Toast.makeText(getApplicationContext(),"String saved...",Toast.LENGTH_LONG).show();


        //return this.sharedPreferences;
    }

    //get string from Shared Preferences
    public String retrieveString(){
        String searchInputted = sharedPreferences.getString("search_key", "");
        Toast.makeText(getApplicationContext(),"String stored retrieved...",Toast.LENGTH_LONG).show();

        return searchInputted;
    }

    //this is a dummy button for searching from EditText String...
    //NOTE: The Search Implementation is not functional, yet.
    public void goToPDFViewerDummy(View view){
        if (view == searcherB){
            //saveEditTextString(sharedPreferences);
            //retrieveString();

            Intent intent = new Intent(this, pdfViewerActivity.class);
            startActivity(intent);
        }


    }

    public void goToPDFViewer(View view){
        if (view == tmpButton){
            Intent intent = new Intent(this, pdfViewerActivity.class);
            startActivity(intent);

        }


    }

    public void goToHelpActivity(View view){
        if(view == goToHelpB){
            Intent intentMainActivity = new Intent(MainActivity.this, HelpPageActivity.class);
            MainActivity.this.startActivity(intentMainActivity);
        }

    }







}

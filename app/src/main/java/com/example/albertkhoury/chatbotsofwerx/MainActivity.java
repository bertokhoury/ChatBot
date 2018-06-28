package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;





public class MainActivity extends Activity{
    PDFView pdfView;
    ImageView sofwerxLogoIV;
    Button goToHelpB;
    Button tmpButton;
    Button searcherB;
    EditText searchInputET;
    SharedPreferences sharedPreferences;
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
    }

    public SharedPreferences saveEditTextString(){
        View view = null;
        if (view == searchInputET){
            //save Edit Text into SharedPreferences
            sharedPreferences = getSharedPreferences(String.valueOf(searchInputET), Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

            editor.putString("search_key", String.valueOf(sharedPreferences));
            editor.commit();

        }
        return sharedPreferences;
    }

    //get string from Shared Preferences
    public String retrieveString(){
        String searchInputted = sharedPreferences.getString("search_key", "");
        return searchInputted;
    }

    //this is a dummy button for searching from EditText String...
    public void goToPDFViewerDummy(View view){
        if (view == searcherB){
            //saveEditTextString();
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

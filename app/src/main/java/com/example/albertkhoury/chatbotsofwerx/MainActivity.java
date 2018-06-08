package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;




public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "field_manual.pdf";
     PDFView pdfView;
     Integer pageNumberPDF = 0;
     String fileNamePDF;


     ImageView sofwerxLogoIV;
     TextView searchViewTextDescriptTV;
     SearchView searchViewSV;
     SearchAutoComplete searchAutoCompleteSAC;
     Button goToHelpB = (Button)findViewById(R.id.goToHelpButton);
     Button tmpButton = (Button)findViewById(R.id.tmpButton);


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

    //this is being implemented for the fact of the matter being we do not have a search yet.
    //once a search is implementable we will remove this function and use SearchView
    public void goToPDFViewer(View view){
        if (view == tmpButton){
            Intent intentMainActivity = new Intent(MainActivity.this, pdfViewerActivity.class);
            MainActivity.this.startActivity(intentMainActivity);

        }


    }

    //onClick take user to HelpActivity
    public void goToHelpActivity(View view){
        if(view == goToHelpB){
            Intent intentMainActivity = new Intent(MainActivity.this, HelpPageActivity.class);
            MainActivity.this.startActivity(intentMainActivity);
        }

    }



}

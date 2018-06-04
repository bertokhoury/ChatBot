package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView sofwerxLogoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //handle image
        sofwerxLogoIV = (ImageView) findViewById(R.id.sofwerxLogo);
    }

}

package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;


public class HelpPageActivity extends Activity {

    Button returnToMainActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_page_activity);

        returnToMainActivity = (Button) findViewById(R.id.helpToMainButton);
    }

    public void goToMainActivityFromHelp(View view) {
        if (view == returnToMainActivity) {
            Intent intentHelpActivity = new Intent(HelpPageActivity.this, MainActivity.class);
            HelpPageActivity.this.startActivity(intentHelpActivity);
        }
    }

}

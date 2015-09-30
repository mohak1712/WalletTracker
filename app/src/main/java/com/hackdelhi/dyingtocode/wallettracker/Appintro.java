package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.paolorotolo.appintro.AppIntro;

public class Appintro extends AppIntro implements Intro1.OnFragmentInteractionListener{


    @Override
    public void init(Bundle bundle) {

       SharedPreferences sharedPreferences = getSharedPreferences("PREF", MODE_PRIVATE);
                if (sharedPreferences.getBoolean("KEY", true)) {
            // Add your slide's fragments here
            // AppIntro will automatically generate the dots indicator and buttons.

            addSlide(Intro1.newInstance("", ""), getApplicationContext());
            addSlide(Intro2.newInstance("", ""), getApplicationContext());
            addSlide(Intro3.newInstance("", ""), getApplicationContext());


            // OPTIONAL METHODS
            // Override bar/separator color
            //  setBarColor(Color.parseColor("#3F51B5"));
            //  setSeparatorColor(Color.parseColor("#2196F3"));

            // Hide Skip button
            showSkipButton(true);

            // Turn vibration on and set intensity
            // NOTE: you will probably need to ask VIBRATE permesssion in Manifest
            setVibrate(true);
            setVibrateIntensity(30);
            setFadeAnimation();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("KEY",false);
            editor.apply();

        }else{

           Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
           finish();
       }
    }
    @Override
    public void onSkipPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onDonePressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appintro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class FifthActivity extends ActionBarActivity {

    int a;
    ImageView imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        imageView3= (ImageView) findViewById(R.id.imageView3);
        Intent intent = getIntent();
        a=intent.getIntExtra("KEY6",-1);
        if(a==R.drawable.qr1)
        {
            Picasso.with(this).load(R.drawable.qr1).into(imageView3);
            //Log.d("ishaan", "" + a);
        }
        else if(a==R.drawable.qr2)
        {
            Picasso.with(this).load(R.drawable.qr2).into(imageView3);
        }
        else if(a==R.drawable.qr3)
        {
            Picasso.with(this).load(R.drawable.qr3).into(imageView3);
        }

        else if(a==R.drawable.qr4)
        {
            Picasso.with(this).load(R.drawable.qr4).into(imageView3);
        }
        else if(a==R.drawable.qr5)
        {
            Picasso.with(this).load(R.drawable.qr5).into(imageView3);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fifth, menu);
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
}

package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class ThirdAcivity extends AppCompatActivity {


    EditText editText;
    Button button;
    String no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_acivity);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
       // webView= (WebView) findViewById(R.id.WebView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editText.getText().toString().length()!=10)
                {
                    Toast.makeText(ThirdAcivity.this,"Invalid Phone no!",Toast.LENGTH_LONG).show();
                }else
                {
                     no = editText.getText().toString();
                    if(no.equalsIgnoreCase("8587921259"))
                    {
                        int id = (R.drawable.qr1);
                        //Intent intent = new Intent(ThirdAcivity.class)
                        sharedpref(id);
                        //ishaan

                    }
                    else if(no.equalsIgnoreCase("9999823913"))
                    {
                        int id=R.drawable.qr2;

                        sharedpref(id);
                        //mohak
                    }
                    else if(no.equalsIgnoreCase("9560291882"))
                    {
                        int id=R.drawable.qr3;
                        sharedpref(id);
                        //nikhil
                    }
                    else if(no.equalsIgnoreCase("9716142747"))
                    {
                        int id=R.drawable.qr4;
                        sharedpref(id);
                        //aman
                    }
                    else if(no.equalsIgnoreCase("8447681777"))
                    {
                        int id=R.drawable.qr5;
                        sharedpref(id);
                        //aman

                    }
                    else
                    {
                        Toast.makeText(ThirdAcivity.this, "Not Yet Given QR code for Transaction Service,Sorry!!",Toast.LENGTH_SHORT).show();
                    }

//                WebSettings webSettings = webView.getSettings();
//                webSettings.setJavaScriptEnabled(true);
//                webView.loadUrl("http://www.the-qrcode-generator.com");
                    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.the-qrcode-generator.com"));
                  //  startActivity(intent);
                }

            }
        });
    }

    private void sharedpref(int id) {

        SharedPreferences sharedPreferences2 = getSharedPreferences("PREF2", MODE_PRIVATE);
        if(sharedPreferences2.getBoolean("KEY5",true))
        {
            SharedPreferences.Editor editor2= sharedPreferences2.edit();
            editor2.putBoolean("KEY5",false);
            editor2.apply();
            Toast.makeText(ThirdAcivity.this,"Number added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ThirdAcivity.this,"Number already added",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ThirdAcivity.this,FifthActivity.class);
            if(no.equalsIgnoreCase("8587921259")) {
                intent.putExtra("KEY6", id);
                startActivity(intent);
            }
            else if(no.equalsIgnoreCase("9999823913")) {
                intent.putExtra("KEY6", id);
                startActivity(intent);
            }
            else if(no.equalsIgnoreCase("9560291882")) {
                intent.putExtra("KEY6", id);
                startActivity(intent);
            }
            else if(no.equalsIgnoreCase("9716142747")) {
                intent.putExtra("KEY6", id);
                startActivity(intent);
            }
            else if(no.equalsIgnoreCase("8447681777")) {
                intent.putExtra("KEY6", id);
                startActivity(intent);
            }





        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_third_acivity, menu);
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

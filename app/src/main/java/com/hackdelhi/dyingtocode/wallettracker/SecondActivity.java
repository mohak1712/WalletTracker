package com.hackdelhi.dyingtocode.wallettracker;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class SecondActivity extends AppCompatActivity {

    int ans;
    private VolleySingelton volleySingelton;
    private RequestQueue requestQueue;
    public static final String url = "http://xourse.herokuapp.com/wallet";
    Button button3;
    String mynumber="9999823913";
    //String adder;
    int ab=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        IntentIntegrator integrator = new IntentIntegrator(SecondActivity.this);
        integrator.initiateScan();
        //EditText ed= (EditText) findViewById(R.id.editText7);
        //EditText ed1=(EditText)findViewById(R.id.editText8);
        //mynumber=ed.getText().toString();
        //adder= ed1.getText().toString();
        //ab=Integer.parseInt(adder);
        //button3=(Button)findViewById(R.id.button3);













     /*   ImageView imageView = new ImageView(this); // Create an icon
        imageView.setImageResource(R.drawable.ic_launcher);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.drawable.contact);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon2 = new ImageView(this);
        itemIcon.setImageResource(R.drawable.pencil);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();

        SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon3 = new ImageView(this);
        itemIcon.setImageResource(R.drawable.calender);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                        // ...
                .attachTo(actionButton)
                .build();
*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                String toast = "Cancelled from fragment";
            } else {
                Toast.makeText(SecondActivity.this, "Scanned from fragment: " + result.getContents(),Toast.LENGTH_SHORT).show();

                Transcation(result.getContents(),50);

            }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.navigate) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
]
        return super.onOptionsItemSelected(item);
    }*/


        }
    }

    void Transcation(final String string,final int ab)
    {
        volleySingelton = VolleySingelton.getInstance();
        requestQueue = VolleySingelton.getrequestQueue();
        JsonArrayRequest request =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0 ; i < response.length() ; i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(object.has("phone_no"))
                        {
                            String num=object.getString("phone_no");
                            if(num.equalsIgnoreCase(string))
                            {
                                ans= object.getInt("balance");
                                if(ans>=50)
                                {
                                    //int a=Integer.parseInt(ab);
                                    ans=ans-ab;
                                    String urll="http://xourse.herokuapp.com/wallet/update/"+string+"/"+""+ans;
                                    new DownloadWebpageTask().execute(urll);
                                }
                                Toast.makeText(SecondActivity.this," response:"+ans,Toast.LENGTH_LONG).show();}
                        }
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        Log.d("ishaan"," "+e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("ishaan"," "+error);

            }
        });

        requestQueue.add(request);

    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(SecondActivity.this,""+ans,Toast.LENGTH_SHORT).show();
            //int a=Integer.parseInt(adder);
            int a=-50;
            Transcation(mynumber,a);
        }

        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {

                java.net.URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d("ishaan", "The response is: " + response);

                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }



    }
}




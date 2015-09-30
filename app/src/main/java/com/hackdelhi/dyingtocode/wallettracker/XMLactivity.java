package com.hackdelhi.dyingtocode.wallettracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


public class XMLactivity extends AppCompatActivity {

    EditText uid,pass;
    String suid,spass;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlactivity);
        tx=(TextView)findViewById(R.id.textView1);
        uid =(EditText)findViewById(R.id.editText);
        pass =(EditText)findViewById(R.id.editText2);


        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                suid = uid.getText().toString();
                spass = pass.getText().toString();
                final String URL = "https://www.mobikwik.com/getBalance.do?uid=" + suid + "&pwd=" + spass;
                new DownloadWebpageTask().execute(URL);
            }
        });
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

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            int a=0;
            JSONObject jsonObj = null;
            try {
                jsonObj = XML.toJSONObject(result);
                JSONObject object = jsonObj.getJSONObject("balancecheck");
                a = object.getInt("balance");
            } catch (JSONException e) {
                Log.e("JSON exception", e.getMessage());
                e.printStackTrace();
            }

            Log.d("XML", result);
            String usingiam=jsonObj.toString();
            Log.d("JSON", usingiam);
            tx.setText("Rs."+ a);
        }
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

    InputStream is = null;

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_xmlactivity, menu);
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

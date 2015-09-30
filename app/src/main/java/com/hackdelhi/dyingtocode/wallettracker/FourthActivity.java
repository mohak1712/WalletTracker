package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONStringer;


import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FourthActivity extends AppCompatActivity {

    Person person;

    StringEntity se;
    String json;
    HttpPost httpPost;

    int pos1, pos2, pos3;
    EditText fo1, fo2, fo3, fo4;
    Button fob;
    String urlu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Intent intent = getIntent();
        pos1 = intent.getIntExtra("KEY1", -1);
        pos2 = intent.getIntExtra("KEY2", -1);
        pos3 = intent.getIntExtra("KEY3", -1);

        fo1 = (EditText) findViewById(R.id.editText3);
        fo2 = (EditText) findViewById(R.id.editText4);
        fo3 = (EditText) findViewById(R.id.editText5);
        fo4 = (EditText) findViewById(R.id.editText6);

        fob = (Button) findViewById(R.id.button2);

        fob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pos1 != -1) {
                    new HttpAsyncTask().execute("http://bookmark-it.herokuapp.com/restful/mecash/add");
                } else if (pos2 != -1) {
                    new HttpAsyncTask().execute("http://bookmark-it.herokuapp.com/restful/indpay/add");
                } else {
                    Log.d("ishaan", "nothing now");
                    //new HttpAsyncTask().execute("http://bookmark-it.herokuapp.com/bookmarks/addNew");
                    urlu1="http://xourse.herokuapp.com/wallet/create/"+fo1.getText().toString()+"/"+fo2.getText().toString()+"/"+fo3.getText().toString()+"/"+fo4.getText().toString();
                    new DownloadWebpageTask().execute(urlu1);
                }

            }
        });


    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {


            person = new Person();
            person.setPhone_no(fo1.getText().toString());
            person.setPass(fo2.getText().toString());
            person.setName(fo3.getText().toString());
            person.setMailid(fo4.getText().toString());


            return POST(urls[0], person);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    public String POST(String url, Person person) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            httpPost = new HttpPost(url);

            JSONObject jsonObject = new JSONObject();
            //json = "";

            // 3. build jsonObject

            jsonObject.accumulate("phone_no", person.getPhone_no());
            jsonObject.accumulate("password", person.getPass());
            jsonObject.accumulate("name", person.getName());
            jsonObject.accumulate("email", person.getMailid());


            // 4. convert JSONObject to JSON to String

            json = jsonObject.toString();


            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            //httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


            // 10. convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);

            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

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

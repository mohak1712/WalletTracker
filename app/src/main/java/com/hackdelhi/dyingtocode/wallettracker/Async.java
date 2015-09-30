package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sarthak on 17-07-2015.
 */
public class Async extends AsyncTask<String, Void, Boolean> {
    String uri;
    Context context;
    Boolean download;


    Async(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Boolean doInBackground(String... params) {
        download = false;
        URL downloadUrl = null;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        FileOutputStream fileOutputStream = null;

        try {
            downloadUrl = new URL(params[0]);
            try {
                httpURLConnection = (HttpURLConnection) downloadUrl.openConnection();
                inputStream = httpURLConnection.getInputStream();
                uri = Uri.parse(params[0]).getLastPathSegment();
                File folder = new File(String.valueOf(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()));

                File file = new File(folder, uri);

                fileOutputStream = new FileOutputStream(file);
                int read = -1;
                while ((read = inputStream.read()) != -1) {

                    fileOutputStream.write((char) read);
                }
                download = true;


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return download;

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

        Toast.makeText(context, "Image downloaded " + uri, Toast.LENGTH_LONG).show();

    }
}

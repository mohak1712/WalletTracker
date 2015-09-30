package com.hackdelhi.dyingtocode.wallettracker;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Sarthak on 05-07-2015.
 */
public class MyFragment extends android.support.v4.app.Fragment {

    public static MyFragment getInstance(int pos)
    {

        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key",pos);
        fragment.setArguments(bundle);
        return fragment;
    }



    TextView textView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);

        textView = (TextView) view.findViewById(R.id.textView);
        Bundle bundle = getArguments();

        if (bundle != null) {
            textView.setText("Page selected is" + bundle.getInt("key"));
        }

        VolleySingelton volleySingelton = new VolleySingelton();


        RequestQueue requestQueue = volleySingelton.getrequestQueue();
        //Static method means that it can be called without creating the object of the class with the claass name
        StringRequest request = new StringRequest(Request.Method.GET,"https://stackoverflow.com/questions/17528826/google-volley-underscore-in-hostname#", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"Response"+ response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error"+ error,Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);

        return view;


    }

}

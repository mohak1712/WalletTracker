package com.hackdelhi.dyingtocode.wallettracker;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoxOffice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBoxOffice extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private VolleySingelton volleySingelton;
    private RequestQueue requestQueue;
    AdapterforIshaan adapterforIshaan;
    ArrayList<Ishaandata> data = new ArrayList<Ishaandata>();
    RecyclerView recyclerView2;
    SwipeRefreshLayout swipeRefreshLayout;
    String toast;
    Communicator communicator;

    //   public static final String API_KEY = "txfxq9c5q2pqvmbewt2uekhy";

    //   public static final String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";

    public static final String url = "http://ish1995.5gbfree.com/ishaan1995/image.json";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBoxOffice.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBoxOffice newInstance(String param1, String param2) {
        FragmentBoxOffice fragment = new FragmentBoxOffice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // public static String getUrl(int limit) {
    //     return url + "?apikey=" + API_KEY + "&limit" + limit;

    //}

    public FragmentBoxOffice() {

        // Required empty public constructor


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("part", "oncreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private boolean NetworkisAvailable() {


        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        Boolean isAvail = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvail = true;
        }
        return isAvail;
    }

    public void Jsonreq() {

        volleySingelton = VolleySingelton.getInstance();
        requestQueue = VolleySingelton.getrequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("part", "getJson");
                data = getjson(response);
              //  adapterforIshaan.adap(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error" + error, Toast.LENGTH_LONG).show();
            }
        });
        Log.d("part", "request");
        requestQueue.add(request);

    }

    public ArrayList<Ishaandata> getjson(JSONObject response) {

        ArrayList<Ishaandata> listy = new ArrayList<Ishaandata>();

        try {
            Log.d("part", "try");
            JSONArray array = response.getJSONArray("items");
            for (int i = 0; i < array.length(); i++) {
                Log.d("part", "loop");
                JSONObject object1 = array.getJSONObject(i);
                String content = object1.getString("caption");
                String url = object1.getString("url");
                // ImageView image = process(url,imageView);
                Ishaandata I = new Ishaandata(content, url);


                listy.add(I);
                //ABOVE VHF

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error" + e, Toast.LENGTH_LONG).show();
        }
        Log.d("part", "" + response.length());

        return listy;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("KEY@", data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("part", "oncreateview");

        View view = inflater.inflate(R.layout.fragment_box_office, container, false);


        recyclerView2 = (RecyclerView) view.findViewById(R.id.RecylerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        final View actionB = view.findViewById(R.id.action_b);

        com.getbase.floatingactionbutton.FloatingActionButton actionC = new com.getbase.floatingactionbutton
                .FloatingActionButton(getActivity().getBaseContext());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionC);


        final com.getbase.floatingactionbutton.FloatingActionButton actionA = (com.getbase.floatingactionbutton.FloatingActionButton)
                view.findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator.forSupportFragment(FragmentBoxOffice.this).initiateScan();

            }
        });
     ;

        if (NetworkisAvailable() && savedInstanceState == null) {

            Log.d("part", "Jsonreq");
            Jsonreq();

        } else if (savedInstanceState != null) {

            data = savedInstanceState.getParcelableArrayList("KEY@");
        //    adapterforIshaan.adap(data);

        } else {
            Toast.makeText(getActivity(), "Network Unavailable Please Try Again", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
                displayToast();
                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra("isbn", result.getContents());
                getActivity().startActivityForResult(i, 1);
            }

        }
        if (requestCode == 22) {
//            isbn = data.getStringExtra("isbn")

        }
    }

    private void displayToast() {
        if (getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            Jsonreq();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}

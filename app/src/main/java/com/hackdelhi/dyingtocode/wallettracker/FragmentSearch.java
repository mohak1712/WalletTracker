package com.hackdelhi.dyingtocode.wallettracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment {

    RecyclerView recyclerView2;
    AdapterforIshaan adapterforIshaan;
    ArrayList<ClassSearch> arrayList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private VolleySingelton volleySingelton;
    private RequestQueue requestQueue;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentSearch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView2 = (RecyclerView) view.findViewById(R.id.RecylerView3);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterforIshaan = new AdapterforIshaan(getActivity());
        //ClassSearch classSearch = new ClassSearch();
        adapterforIshaan.adap(getArrayList());
        recyclerView2.setAdapter(adapterforIshaan);

        recyclerView2.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(view.getContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                Intent intent = null;
                switch (position) {
                    case 0:

                        
                        break;
                    case 1:

                        break;

                    case 2:

                        break;
                    case 3:

                        break;
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        return view;
    }

    private ArrayList<ClassSearch> getArrayList() {
        ArrayList<ClassSearch> data = new ArrayList<ClassSearch>();
        String[] titles = {"Item1", "Item2", "Item3", "Item4"};
        int[] iconid = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
        for (int i = 0; i < titles.length; i++) {
            ClassSearch current = new ClassSearch();
            current.string = titles[i];
            current.iconId = iconid[i];
            data.add(current);
        }

        return data;
    }


}

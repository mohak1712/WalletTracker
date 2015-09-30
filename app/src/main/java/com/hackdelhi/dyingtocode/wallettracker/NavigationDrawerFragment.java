package com.hackdelhi.dyingtocode.wallettracker;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
/*
Steps to handle recylcler view click

1. Create a inner class that implements RecyclerView.OnitemtouchListener
2. Create an interface inside that class that supports click and long click and indicates the view
that was clicked and the position where it was clicked
3. Create a GestureDetector to detect ACTION_UP single tap and long press event
4. Return true from the singleTap to indicate that your gesture motion has consumed the event
5. Find the child view containing the coordinates specified by the motion event and if the child view is not null
and listener is not null either then fire a long click event
 */
public class NavigationDrawerFragment extends Fragment {

    RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY = "user learned";
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    View containerView;
    Adapter adapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readtoPreferences(getActivity(), KEY, "false"));
        if (savedInstanceState != null)
        //coming back from rotation else activity started for the very first time
        {
            mFromSavedInstanceState = true;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recylerview);

        adapter = new Adapter(getActivity(), getdata());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new Recyclertouchlistener(getActivity(), recyclerView, new Recyclertouchlistener.Clicklistener() {
            @Override
            public void onClick(View view, int position) {
                //     Toast.makeText(getActivity(),"Click"+position,Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        int BlueColorValue = Color.BLUE;
                        view.setBackgroundColor(BlueColorValue);
                        //int greenColorValue = Color.parseColor("#00ff00")

                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), SecondActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        //Toast.makeText(getActivity(), "Click 2", Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(getActivity(),ThirdAcivity.class);
                                startActivity(intent1);

                        break;
                    case 3:
                        Intent intent3=new
                                Intent(getActivity(),SixthActivity.class);
                                startActivity(intent3);
                        break;
                }
                //Work here to go to another activity by clicking recycler view item or anything else
            }

            @Override
            public void onLongClick(View view, int position) {
            //    Toast.makeText(getActivity(), "Long Click" + position, Toast.LENGTH_LONG).show();
            }
        }));
        return layout;


    }

    public List<SingleRow> getdata() {
        List<SingleRow> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_action_image_filter_1, R.drawable.ic_action_image_filter_2, R.drawable.ic_action_image_filter_3, R.drawable.ic_action_image_filter_4};
        String[] titles = {"Item1", "Item2", "Item3", "Item4"};
        for (int i = 0; i < titles.length && i < icons.length; i++) {
            SingleRow current = new SingleRow();
            current.Iconid = icons[i];
            current.text = titles[i];
            data.add(current);

        }
        return data;
    }


    public void setup(int FragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(FragmentId);
        DrawerLayout mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedtoPreferences(getActivity(), KEY, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                mDrawerToggle.syncState();
            }
        });

    }

    public void savedtoPreferences(Context context, String preferenceName, String preferenceValue) {

        //String preferenceName represents what we want to save
        //String preferenceValue represents true or false
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }

    public String readtoPreferences(Context context, String preferenceName, String defaultValue) {

        //String preferenceName represents what we want to read
        //String defaultValue
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(preferenceName, defaultValue);

    }

    static class Recyclertouchlistener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Clicklistener clicklistener;

        Recyclertouchlistener(Context context, final RecyclerView recyclerView, final Clicklistener clicklistener) {
            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recyclerView.getChildAdapterPosition(child));

                    }
                }


            });


        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(motionEvent)) {
                clicklistener.onClick(child, recyclerView.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface Clicklistener {
            public void onClick(View view, int position);

            public void onLongClick(View view, int position);
        }
    }

}
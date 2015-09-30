package com.hackdelhi.dyingtocode.wallettracker;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends AppCompatActivity {
    // implements MaterialTabListener for the new dependency
    android.support.v7.widget.Toolbar toolbar;

    RecyclerView recyclerView2;
    AdapterforIshaan adapterforIshaan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        // setting the navigation drawer for swipe
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Adding fragment statically
        //when we add dynamically we call fragment manager and all..
      NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
            .findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        recyclerView2 = (RecyclerView)findViewById(R.id.RecylerView3);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapterforIshaan = new AdapterforIshaan(this);
        //ClassSearch classSearch = new ClassSearch();
        adapterforIshaan.adap(getArrayList());
        recyclerView2.setAdapter(adapterforIshaan);
        recyclerView2.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(view.getContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (position) {
                    case 0:
                         intent= new Intent(MainActivity.this,XMLactivity.class);
                        //intent.putExtra("KEY",0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent= new Intent(MainActivity.this,FourthActivity.class);
                        intent.putExtra("KEY1",1);
                        startActivity(intent);


                        break;

                    case 2:
                        intent= new Intent(MainActivity.this,FourthActivity.class);
                        intent.putExtra("KEY2",2);
                        startActivity(intent);

                        break;
                    case 3:
                        intent= new Intent(MainActivity.this,FourthActivity.class);
                        intent.putExtra("KEY3",3);
                        startActivity(intent);

                        break;
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));



/*
        materialTab = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.pager);
        MypagerAdapter adapter = new MypagerAdapter(getSupportFragmentManager());

        //viewPager.setAdapter(new MypagerAdapter(getSupportFragmentManager()));
        // materialTab

        // viewPager.setAdapter(new MyAdapter(manager));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                materialTab.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            materialTab.addTab(
                    materialTab.newTab()
                            .setIcon(adapter.getPageIcon(i))
                            .setTabListener(this)
            );
        }




*/



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

    private ArrayList<ClassSearch> getArrayList() {
        ArrayList<ClassSearch> data = new ArrayList<ClassSearch>();
        String[] titles = {"Mobikwik", "Payme", "WalletCash", "MyWallet"};
        int[] iconid = {R.drawable.signage5000x5000, R.drawable.wallet_icon, R.drawable.icn3, R.drawable.icon2};
        for (int i = 0; i < titles.length&&i<iconid.length; i++) {
            ClassSearch current = new ClassSearch();
            current.string = titles[i];
            current.iconId = iconid[i];
            data.add(current);
        }

        return data;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }*/
//
//    @Override
//    public void onTabSelected(MaterialTab materialTab) {
//        viewPager.setCurrentItem(materialTab.getPosition());
//    }
//
//    @Override
//    public void onTabReselected(MaterialTab materialTab) {
//
//    }
//
//    @Override
//    public void onTabUnselected(MaterialTab materialTab) {
//
//    }
//
//    @Override
//    public void respond(String data) {
//
//    }


//    public class MypagerAdapter extends FragmentStatePagerAdapter {
//
//
//        public static final int Search = 0;
//        public static final int Box_office = 1;
//        public static final int Upcoming = 2;
//
//        // String [] tabs;
//        int[] icon = {R.drawable.pencil, R.drawable.contact,R. drawable.calender};
//
//
//        public MypagerAdapter(FragmentManager fm) {
//
//            super(fm);
//
//
//        }   //  tabs=getResources().getStringArray(R.array.TITLE);
//
//
//
//        public android.graphics.drawable.Drawable getPageIcon(int position) {
//            return ResourcesCompat.getDrawable(getResources(), icon[position], null);
//        }
//
//        @Override
//        public android.support.v4.app.Fragment getItem(int position) {
//            android.support.v4.app.Fragment fragment = null;
//            switch (position) {
//
//                case Search:
//                    fragment = FragmentSearch.newInstance("", "");
//                    break;
//                case Box_office:
//                    fragment = FragmentBoxOffice2.newInstance("", "");
//                    break;
//                case Upcoming:
//                    fragment = FragmentUpcoming.newInstance("", "");
//                    break;
//            }
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }


}



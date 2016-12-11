package com.angryziber.android.dtuguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    SimpleFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(6);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        setTitle(adapter.getPageTitle(0));

        // Set the adapter onto the view pager
                viewPager.setAdapter(adapter);



//        new Thread(new Runnable() {
//            public void run() {
//              viewPager.setPageTransformer(true,new CubeOutTransformer());;
//            }
//        }).start();


//        viewPager.setPageTransformer(true,new CubeOutTransformer());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Find the tab layout that shows the tabs




        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
      tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText("");
        }


        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_action_dtucclogogray);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_note_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_action_timetable);
        tabLayout.getTabAt(3).setIcon(R.mipmap.ic_library_books_black_24dp);
        tabLayout.getTabAt(4).setIcon(R.mipmap.ic_border_color_black_24dp);
        tabLayout.getTabAt(5).setIcon(R.mipmap.ic_school_black_24dp);


//..........................................................................................

        int flag = checkNetwork();

        if(flag==0){
            Snackbar snackbar = Snackbar
                    .make(drawer, "No Internet connection!", Snackbar.LENGTH_LONG);

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }


    }
        //..........................................................................................


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_maps) {
            // Handle the camera action
            Intent maps = getPackageManager().getLaunchIntentForPackage("dtu.dtumaps");
            if (maps != null) {
                startActivity(maps);
            } else {
                Intent nmaps = new Intent(Intent.ACTION_VIEW);
                nmaps.setData(Uri.parse("https://play.google.com/store/apps/details?id=dtu.dtumaps"));
                startActivity(nmaps);
            }
        } else if (id == R.id.nav_library) {
            Intent lib = getPackageManager().getLaunchIntentForPackage("commandoengineer.dtulibrary");
            if (lib != null) {
                startActivity(lib);
            } else {
                Intent nmaps = new Intent(Intent.ACTION_VIEW);
                nmaps.setData(Uri.parse("https://play.google.com/store/apps/details?id=commandoengineer.dtulibrary"));
                startActivity(nmaps);
            }
        } else if (id == R.id.nav_contacts) {

            Intent intent = new Intent(this,Contacts.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out DTUdirect app at: https://play.google.com/store/apps/details?id=com.example.android.dtuguide");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);


        } else if (id == R.id.nav_feedback) {

            Intent intent = new Intent(this,feedbackActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_about) {
                    Intent intent = new Intent(this,About_Actitvity.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int checkNetwork(){

        // Checking internet Connection ............................................................
        ConnectivityManager check = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();
        int flag=0;
        for (int i = 0; i<info.length; i++){
            if (info[i].getState() == NetworkInfo.State.CONNECTED){
                flag=1;
                break;
            }
        }
        return flag;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

}





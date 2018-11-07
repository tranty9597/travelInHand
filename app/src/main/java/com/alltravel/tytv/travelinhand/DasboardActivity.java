package com.alltravel.tytv.travelinhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class DasboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CardView onTrvelCard, historyCard, newTravelCard, profileCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponent();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dasboard, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_onTravel:
                onGoToOnTravel();
                break;
            case R.id.nav_newTravel:
                onGoToNewTravel();
                break;
            case R.id.nav_history:
                onGoToHistory();
                break;
            case R.id.nav_profile:
                onGoToOnTravel();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initComponent(){
        onTrvelCard = findViewById(R.id.onTravelCard);
        newTravelCard = findViewById(R.id.newtravelCard);
        historyCard = findViewById(R.id.onHistoryCard);
        profileCard = findViewById(R.id.profileCard);

        onTrvelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoToOnTravel();
            }
        });

        newTravelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoToNewTravel();
            }
        });

        historyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoToHistory();
            }
        });
    }
    private void onGoToOnTravel(){
        Intent i = new Intent(this, OnTravelActitvity.class);
        startActivity(i);
    }
    private void onGoToHistory(){
        Intent i = new Intent(this, HistoryAcivity.class);
        startActivity(i);
    }

    private void onGoToNewTravel(){
        Intent i = new Intent(this, ListAllStepForTravel.class);
        startActivity(i);
    }
}

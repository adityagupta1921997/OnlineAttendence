package com.apkglobal.onlineattendence.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apkglobal.onlineattendence.R;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "G", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(NavigationActivity.this, "Give Your feedback after using this app!!", Toast.LENGTH_LONG).show();
                Intent feedback=new Intent(Intent.ACTION_VIEW);
                feedback.setData(Uri.parse("https://play.google.com"));
                startActivity(feedback);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent share=new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com");
            share.setType("text/plain");
            startActivity(Intent.createChooser(share,"Share App Via"));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent home=new Intent(NavigationActivity.this,HomeActivity.class);
            startActivity(home);
        } else if (id == R.id.nav_login) {
            Intent login=new Intent(NavigationActivity.this,MainActivity.class);
            startActivity(login);

        } else if (id == R.id.nav_about) {
            Intent about=new Intent(NavigationActivity.this,AboutActivity.class);
            startActivity(about);

        } else if (id == R.id.nav_share) {
            Intent share=new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com");
            share.setType("text/plain");
            startActivity(Intent.createChooser(share,"Share App Via"));

        } else if (id == R.id.nav_contact) {
            Intent contact=new Intent(NavigationActivity.this,ContactActivity.class);
            startActivity(contact);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

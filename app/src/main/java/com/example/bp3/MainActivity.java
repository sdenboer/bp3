package com.example.bp3;

import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.bp3.databinding.ActivityMainBinding;
import com.example.bp3.service.repository.RestApiHelper;
import com.example.bp3.views.fragments.ChallengeView;
import com.example.bp3.views.fragments.Event;
import com.example.bp3.views.fragments.Event_Aanvragen;
import com.example.bp3.views.fragments.Opdracht.MijnActiviteiten;
import com.example.bp3.views.fragments.Opdracht.OpdrachtLesvak;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestApiHelper.initRestApiHelper(MainActivity.this);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.activity_nav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //DIT WORDT HET HOMESCREEN
//        displaySelectedScreen(R.id.navigation_assignments);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.activity_nav);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.navigation_profile:
                break;
            case R.id.navigation_work:
                break;
            case R.id.navigation_events:
                fragment = new Event();
                break;
            case R.id.navigation_event_aanvragen:
                fragment = new Event_Aanvragen();
                break;
            case R.id.navigation_opdrachten:
                fragment = new OpdrachtLesvak();
                break;
            case R.id.navigation_challenges:
                fragment = new ChallengeView();
                break;
            case R.id.navigation_mystuff:
                fragment = new MijnActiviteiten();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.activity_nav);
        drawer.closeDrawer(GravityCompat.START);
    }

}

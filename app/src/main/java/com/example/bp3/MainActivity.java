package com.example.bp3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.bp3.views.fragments.Challenge.ChallengeView;
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.repository.RestApiHelper;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.views.LoginActivity;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;
import com.example.bp3.views.fragments.Event;
import com.example.bp3.views.fragments.Event_Aanvragen;
import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenDocent;
import com.example.bp3.views.fragments.Opdracht.Docent.IDataSendDeadline;
import com.example.bp3.views.fragments.Opdracht.Docent.OpdrachtVraagToevoegen;

import java.util.Map;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IDataSendDeadline {

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

        //naar de inlogpagina
        if (Account.currentUser == null)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }


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
                fragment = new OpdrachtVraagToevoegen();
                break;
            case R.id.navigation_challenges:
                fragment = new ChallengeView();
                break;
            case R.id.navigation_mystuff:
                fragment = new MijnActiviteitenDocent();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_container, fragment, "fragment");
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.activity_nav);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void sendData(Map<String, Integer> data) {
        FragmentManager manager = getSupportFragmentManager();
        OpdrachtVraagToevoegen fragment= (OpdrachtVraagToevoegen) manager.findFragmentByTag("fragment");
        assert fragment != null;
        fragment.updateDeadline(data);
    }
}

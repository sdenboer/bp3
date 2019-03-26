package com.example.bp3;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bp3.models.Student;
import com.example.bp3.models.Tag;
import com.example.bp3.utils.helpers.RestApiHelper;
import com.example.bp3.views.fragments.ChallengeView;
import com.example.bp3.views.fragments.MyStuffView;
import com.example.bp3.views.fragments.OpdrachtView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestApiHelper.initRestApiHelper(MainActivity.this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Voor meerdere objecten
        RestApiHelper tagJSON = RestApiHelper
                .prepareQuery("tag")
                .klasse(Tag[].class)
                .build();
        tagJSON.returnJSONArray(ja -> {
           List<Tag> tags = Arrays.asList((Tag[]) tagJSON.toPojo(ja));
            //      In deze array zitten alle tag objecten.
            //      voeg hieronder code toe om iets met de objecten te doen. Bijvoorbeeld:
                  tags.forEach(tag -> Log.d("HELLO", tag.getTag()));
        });

        //Voor een enkel object (1 resultaat)
        RestApiHelper studentJSON = RestApiHelper
                .prepareQuery("student")
                .klasse(Student.class)
                .parameters(Arrays.asList("jklaas@student.avans.nl"))
                .build();
        studentJSON.returnJSONObject(jo -> {
            Student student = (Student) studentJSON.toPojo(jo);
            //      In dit object zit een Student object.
            //      voeg hieronder code toe om iets met het object te doen. Bijvoorbeeld:
                  Log.d("HELLO", student.getNaam());
        });

        //POST POJO
        Tag t = new Tag("test11111");
        RestApiHelper.prepareQuery("tag").build().postJSON(t);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                break;
            case R.id.navigation_assignments:
                fragment = new OpdrachtView();
                break;
            case R.id.navigation_challenges:
                fragment = new ChallengeView();
                break;
            case R.id.navigation_mystuff:
                fragment = new MyStuffView();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}

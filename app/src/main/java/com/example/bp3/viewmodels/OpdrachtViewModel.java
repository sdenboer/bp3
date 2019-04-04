package com.example.bp3.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bp3.R;
import com.example.bp3.databinding.ActivityMainBinding;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.repository.OpdrachtRepository;
import com.example.bp3.service.repository.RestApiHelper;
import com.example.bp3.views.fragments.Challenge.ChallengeView;
import com.example.bp3.views.fragments.Event;
import com.example.bp3.views.fragments.Event_Aanvragen;
import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenDocent;
import com.example.bp3.views.fragments.Opdracht.Docent.IDataSendDeadline;
import com.example.bp3.views.fragments.Opdracht.Docent.OpdrachtVraagToevoegen;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OpdrachtViewModel extends AndroidViewModel implements Serializable {
    private Opdracht opdracht;
    private LiveData<Opdracht> liveOpdracht;
    private Toast onSuccess, onError;

    public OpdrachtViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("ShowToast")
    public void prepareCreate(Map<String, String> values, Docent docent, String onSuccess, String onError) { //VERWIJDER DOCENT
        this.onSuccess = Toast.makeText(this.getApplication().getApplicationContext(),onSuccess,Toast.LENGTH_SHORT);
        this.onError = Toast.makeText(this.getApplication().getApplicationContext(), onError,Toast.LENGTH_SHORT);
        int leerjaar = Integer.valueOf(Objects.requireNonNull(values.get("leerjaar")));
        String opdrachtNaam = values.get("opdrachtnaam");
        String lesvak = values.get("lesvak");
        String eisen = values.get("eisen");
        int aantStudMin =  Integer.valueOf(Objects.requireNonNull(values.get("minStud")));
        int aantStudMax =  Integer.valueOf(Objects.requireNonNull(values.get("maxStud")));
        String deadline = getUglyDeadline(values.get("deadline"));
        this.opdracht = new Opdracht(leerjaar,opdrachtNaam, lesvak, eisen, aantStudMin, aantStudMax, docent, deadline);
        this.create();
    }

    public LiveData<List<Opdracht>> getMyPosted(Docent docent) {
       return OpdrachtRepository.getInstance().getMyPostedOpdrachten(docent); //VERWIJDER DOCENT
    }

//    public LiveData<List>

    public void delete(Opdracht opdracht, Toast onSuccess, Toast onError) {
        OpdrachtRepository.getInstance().delete(opdracht, onSuccess, onError);
    }

    public void getOpdrachtById(int id, LifecycleOwner fragment){
        this.liveOpdracht =  OpdrachtRepository.getInstance().getOpdrachtById(id);
        OpdrachtViewModel self  = this;
        this.liveOpdracht.observe(fragment, opdracht -> {
            self.deadline = self.getPrettyDeadline(opdracht.getDeadline());
            self.opdrachtNaam = opdracht.getOpdrachtNaam();
            self.leerjaar = opdracht.getLeerjaar();
            self.lesvak = opdracht.getLesvak();
            self.eisen = opdracht.getEisen();
            self.aantStudMax = opdracht.getAantStudMax();
            self.aantStudMin = opdracht.getAantStudMin();
            self.docent = opdracht.getDocent();
        });
    }

    private String getUglyDeadline(String deadline){
            DateTimeFormatter dlold = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
            DateTimeFormatter dlnew = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime time =  LocalDateTime.parse(deadline, dlold);
            return dlnew.format(time);
    }

    private void create(){
        OpdrachtRepository.getInstance().create(this.opdracht, onSuccess, onError);
    }



    //getters and setters
    public String opdrachtNaam;
    public Integer leerjaar;
    public String lesvak;
    public String eisen;
    public Integer aantStudMax;
    public Integer aantStudMin;
    public Docent docent;
    public String deadline;
//    public String prettyDeadline;

    private String getPrettyDeadline(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        LocalDateTime time =  LocalDateTime.parse (deadline.replaceAll("\\+(.*)$", ""));
        return formatter.format(time);
    }
}

package com.example.bp3;

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
        import com.example.bp3.views.fragments.Event;
        import com.example.bp3.views.fragments.Event_Aanvragen;
        import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenDocent;
        import com.example.bp3.views.fragments.Opdracht.Docent.IDataSendDeadline;
        import com.example.bp3.views.fragments.Opdracht.Docent.OpdrachtVraagToevoegen;

        import java.util.Map;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IDataSendDeadline {

    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestApiHelper.initRestApiHelper(com.example.bp3.MainActivity.this);
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


package com.example.bp3.views.fragments.Event;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.EventSoort;
import com.example.bp3.service.repository.EventRepository;
import com.example.bp3.service.repository.RestApiHelper;
import com.example.bp3.views.adapters.EventRecyclerViewAdapter;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Event extends ViewFragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList results = new ArrayList<AanbodEvent>();
    private List data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        results.clear();
        getDataSet();
        loop();


        return view;
    }

    public void loop(){
        if(data != null){
            mAdapter = new EventRecyclerViewAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
        } else{
            loop();
        }
    }

    @Override
    public int title() {
        return R.string.events_event;
    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Test");
        EventRepository ep = new EventRepository();
        ep.aanbodevent();


        ((EventRecyclerViewAdapter) mAdapter).setOnItemClickListener((position, v)
                -> eventPage(position,v));
    }

    public void eventPage(int position, View v){
        AanbodEvent aanbodEvent = (AanbodEvent)data.get(position);

        FragmentTransaction t = this.getFragmentManager().beginTransaction();
        Fragment frag = new EventPage();
        Bundle bundle = new Bundle();
        AanbodEvent event = aanbodEvent;
        bundle.putSerializable("event", event);
        frag.setArguments(bundle);
        t.addToBackStack(null);
        t.replace(R.id.fragment_container, frag);
        t.commit();
    }

    private boolean getDataSet() {
        Bedrijf b = new Bedrijf("em","ww","nm","tel","emp","telp");
        EventSoort s = new EventSoort("test");
        String datum = "19-03-2019";
        AanbodEvent eventtest = new AanbodEvent(100,"naam","locatie",datum,1,"omschrijving",s);
        results.add(eventtest);

        RestApiHelper eventJSON = RestApiHelper
                .prepareQuery("aanbodevent")
                .klasse(AanbodEvent[].class)
                .build();
        eventJSON.getArray(ja -> {
            List<AanbodEvent> events = Arrays.asList((AanbodEvent[]) eventJSON.toPOJO(ja));
            data = events;
        });

        if(data!= null){
            return true;
        }

        return false;
    }
}
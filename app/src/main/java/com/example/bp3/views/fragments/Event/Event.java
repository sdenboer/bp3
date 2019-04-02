package com.example.bp3.views.fragments.Event;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.EventSoort;
import com.example.bp3.service.repository.EventRepository;
import com.example.bp3.views.adapters.EventRecyclerViewAdapter;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event extends ViewFragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList results = new ArrayList<AanbodEvent>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public int title() {
        return R.string.events_event;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((EventRecyclerViewAdapter) mAdapter).setOnItemClickListener((position, v)
                -> eventPage(position,v));
    }

    public void eventPage(int position, View v){
        AanbodEvent aanbodEvent = (AanbodEvent)results.get(position);

        FragmentTransaction t = this.getFragmentManager().beginTransaction();
        Fragment frag = new EventPage();
        Bundle bundle = new Bundle();
        AanbodEvent event = aanbodEvent;
        bundle.putSerializable("event", event);
        frag.setArguments(bundle);

        t.replace(R.id.fragment_container, frag);
        t.commit();
    }

    private ArrayList<AanbodEvent> getDataSet() {

        Bedrijf bedrijf = new Bedrijf();
        EventSoort ev = new EventSoort("workshop");


        LiveData<List<EventSoort>> data = new MutableLiveData<>();
        //EventRepository re = new EventRepository();
        //data = re.getSoorten();
        //data = re.getInstance().getSoorten();
        String x = data.toString();
        //String x (EventSoort.;
        System.out.println("tostring:          " + x);





        String Date1="29/03/2019";
        Date datum = null;
        try {
            datum = new SimpleDateFormat("dd/MM/yyyy").parse(Date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AanbodEvent obj2 = new AanbodEvent(88, "Naam" , "Loatie", datum,
                5, "Omschrijving",bedrijf, ev);
        results.add(obj2);

        for (int index = 0; index < 20; index++) {
            AanbodEvent obj = new AanbodEvent(index, "Naam" , "Loatie", datum,
                    5, "Omschrijving",bedrijf, ev);
            results.add(obj);
        }


        return results;
    }
}
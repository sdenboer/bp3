package com.example.bp3.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bp3.R;
import com.example.bp3.views.adapters.EventRecyclerViewAdapter;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event extends ViewFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "Event";

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
                -> Log.i(LOG_TAG, " Clicked on Item " + position));
    }

    private ArrayList<AanbodEvent> getDataSet() {
        ArrayList results = new ArrayList<AanbodEvent>();
        String Date1="29/03/2019";
        Date datum = null;
        try {
            datum = new SimpleDateFormat("dd/MM/yyyy").parse(Date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int index = 0; index < 20; index++) {
            AanbodEvent obj = new AanbodEvent(index, "Naam" , "Loatie", datum,
                    5, "Omschrijving","Email");
            results.add(index, obj);
        }
        return results;
    }
}
package com.example.bp3.views.fragments.Event;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bp3.R;
import com.example.bp3.viewmodels.AanbodEventViewModel;
import com.example.bp3.views.adapters.EventRecyclerViewAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author Koen Franken
 */

public class Event extends ViewFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        final EventRecyclerViewAdapter adapter = new EventRecyclerViewAdapter();

        AanbodEventViewModel vmEvent = ViewModelProviders.of(this).get(AanbodEventViewModel.class);
        vmEvent.getAllAanbodevent().observe(this, adapter::setAanbodEvent);
        adapter.setOnItemClickListener(aanbodEvent -> {

            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            Fragment frag = new EventPage();
            Bundle bundle = new Bundle();
            bundle.putSerializable("event", aanbodEvent);
            frag.setArguments(bundle);
            t.addToBackStack(null);
            t.replace(R.id.fragment_container, frag);
            t.commit();

        });

        FloatingActionButton btnadd = (FloatingActionButton) view.findViewById(R.id.button_addEvent);
        btnadd.setOnClickListener(v -> {
            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            Fragment frag = new EventAdd();
            t.addToBackStack(null);
            t.replace(R.id.fragment_container, frag);
            t.commit();
        });


        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public int title() {
        return R.string.events_event;
    }

}
package com.example.bp3.views.fragments.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;


public class EventPage extends ViewFragment{
    AanbodEvent event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventpage, container, false);

        Button btnTerug = (Button) view.findViewById(R.id.EventBtnTerug);
        Button btnInschrijf = (Button) view.findViewById(R.id.EventbtnInschrijf);
        event = (AanbodEvent) getArguments().getSerializable("event");

        //code

        //code

        btnTerug.setOnClickListener(v -> {
            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            Fragment frag = new Event();
            t.replace(R.id.fragment_container, frag);
            t.commit();
        });

        btnInschrijf.setOnClickListener(v -> {
            //code
            System.out.println(event.getNaam());
        });
        return view;
    }

    @Override
    public int title() {
        return R.string.event_page;
    }
}
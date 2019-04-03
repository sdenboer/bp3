package com.example.bp3.views.fragments.Event;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.bp3.R;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author Koen Franken
 */

public class EventAdd extends ViewFragment {
    private EditText txtnaam, txtlocatie, txtdatumentijd, txtaantalp, txtomschrijving;
    private NumberPicker numberAantalp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event_add, container, false);

        txtnaam = (EditText) view.findViewById(R.id.editTextEventNaam);
        txtlocatie = (EditText) view.findViewById(R.id.editTextEventLocatie);
        txtdatumentijd = (EditText) view.findViewById(R.id.editTextEventDatumtijd);
        txtomschrijving = (EditText) view.findViewById(R.id.editTextEventOmschrijving);
        txtaantalp = (EditText) view.findViewById(R.id.editTextEventAantalp);
        FloatingActionButton btnclose = (FloatingActionButton) view.findViewById(R.id.button_CloseEvent);
        FloatingActionButton btnsave = (FloatingActionButton) view.findViewById(R.id.button_SaveEvent);

        btnclose.setOnClickListener(v -> {
            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            Fragment frag = new Event();
            t.addToBackStack(null);
            t.replace(R.id.fragment_container, frag);
            t.commit();
        });
        btnsave.setOnClickListener(v -> {
            //insert event
            System.out.println("opslaan");
        });
        return view;
    }

    @Override
    public int title() {
        return R.string.event_add;
    }
}
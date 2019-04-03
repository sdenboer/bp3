package com.example.bp3.views.fragments.Event;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.EventSoort;
import com.example.bp3.service.repository.RestApiHelper;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Koen Franken
 */

public class EventAdd extends ViewFragment {
    private EditText txtnaam, txtlocatie, txtdatumentijd, txtaantalp, txtomschrijving, txtsoort;
    private List<String> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event_add, container, false);

        txtnaam = (EditText) view.findViewById(R.id.editTextEventNaam);
        txtlocatie = (EditText) view.findViewById(R.id.editTextEventLocatie);
        txtdatumentijd = (EditText) view.findViewById(R.id.editTextEventDatumtijd);
        txtomschrijving = (EditText) view.findViewById(R.id.editTextEventOmschrijving);
        txtaantalp = (EditText) view.findViewById(R.id.editTextEventAantalp);

        RestApiHelper soortJSON = RestApiHelper
                .prepareQuery("eventsoort")
                .klasse(EventSoort[].class)
                .build();
        soortJSON.getArray(ja -> {
            List<EventSoort> eventSoort = Arrays.asList((EventSoort[]) soortJSON.toPOJO(ja));
                  eventSoort.forEach(soort -> items.add(soort.getSoort()));
        });

        Spinner dropdownSoort = (Spinner) view.findViewById(R.id.spinnerSoort);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,items);
        dropdownSoort.setAdapter(adapter);

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

            Editable txt=(Editable)txtnaam.getText();
            String naam = txt.toString();
            Editable txt2=(Editable)txtlocatie.getText();
            String locatie = txt2.toString();
            Editable txt3=(Editable)txtdatumentijd.getText();
            String date = txt3.toString();
            Editable txt4=(Editable)txtaantalp.getText();
            int personen = Integer.parseInt(txt4.toString());
            Editable txt5=(Editable)txtomschrijving.getText();
            String omsch = txt5.toString();

            String x = "soort";
            EventSoort soort = new EventSoort(x);
            AanbodEvent aanbodEvent = new AanbodEvent(0,naam ,locatie,date,personen,omsch,soort);


            System.out.println(aanbodEvent.getNaam());
            System.out.println(aanbodEvent.getLocatie());
            System.out.println(aanbodEvent.getDatumentijd());
            System.out.println(aanbodEvent.getAantalPersonen());
            System.out.println(aanbodEvent.getOmschrijving());
            System.out.println(aanbodEvent.getSoort());

        });
        return view;
    }

    @Override
    public int title() {
        return R.string.event_add;
    }

    public void getSoorten(){

    }
}
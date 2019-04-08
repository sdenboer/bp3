package com.example.bp3.views.fragments.Event;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.EventSoort;
import com.example.bp3.service.repository.RestApiHelper;
import com.example.bp3.viewmodels.AanbodEventViewModel;
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
    private String keuze = "Workshop";

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
                },
            error -> Log.d("Error", error.toString()));

        Spinner dropdownSoort = (Spinner) view.findViewById(R.id.spinnerSoort);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_spinner_item,items);
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
            try {
                Editable txt = (Editable) txtnaam.getText();
                String naam = txt.toString();
                Editable txt2 = (Editable) txtlocatie.getText();
                String locatie = txt2.toString();
                Editable txt3 = (Editable) txtdatumentijd.getText();
                String date = "2019-06-06T09:30:00+02:00";
                //Kreeg het niet voor elkaar met de date
                Editable txt4 = (Editable) txtaantalp.getText();
                int personen = Integer.parseInt(txt4.toString());
                Editable txt5 = (Editable) txtomschrijving.getText();
                String omsch = txt5.toString();

                if (Account.currentUser instanceof Docent) {
                    EventSoort soort = new EventSoort(keuze);
                    Docent docent = (Docent) Account.currentUser;
                    AanbodEvent aanbodEvent = new AanbodEvent(naam, locatie, date, personen, omsch, soort);
                    aanbodEvent.setDocent(docent);
                    AanbodEventViewModel vmEvent = ViewModelProviders.of(this).get(AanbodEventViewModel.class);
                    vmEvent.create(aanbodEvent);
                } else if (Account.currentUser instanceof Bedrijf) {
                    EventSoort soort = new EventSoort(keuze);
                    Bedrijf bedrijf = (Bedrijf) Account.currentUser;
                    AanbodEvent aanbodEvent = new AanbodEvent(naam, locatie, date, personen, omsch, soort);
                    aanbodEvent.setBedrijf(bedrijf);
                    AanbodEventViewModel vmEvent = ViewModelProviders.of(this).get(AanbodEventViewModel.class);
                    vmEvent.create(aanbodEvent);
                }
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(view.getContext());
                dlgAlert.setMessage("Evenement is aangemaakt");
                dlgAlert.setTitle("Toegevoegd");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack();
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }catch (Exception e){
                System.out.println(e);
            }
        });
        return view;
    }

    @Override
    public int title() {
        return R.string.event_add;
    }
}
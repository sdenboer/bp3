package com.example.bp3.views.fragments.Event;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Inschrijvingsnummer;
import com.example.bp3.viewmodels.InschrijvingEventViewModel;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author Koen Franken
 */

public class EventInschrijven extends ViewFragment {
    private TextView txtEventNaam;
    private EditText aantalP;
    private Button buttoninschrijven;
    AanbodEvent event;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_eventinschrijven, container, false);
        event = (AanbodEvent) getArguments().getSerializable("eventinschrijf");
        aantalP = (EditText) view.findViewById(R.id.editTextInschrijvenpersonen);
        txtEventNaam = (TextView) view.findViewById(R.id.textViewInschrijveneventNaam);
        buttoninschrijven = (Button) view.findViewById(R.id.buttonInschrijven);

        try{txtEventNaam.setText("" + event.getNaam());}catch (Exception e){System.out.println(e);
        }

        buttoninschrijven.setOnClickListener(v -> {
            try{
                Bedrijf bedrijf = new Bedrijf("contact@heineken","wachtwoord","Heineken","061234584","hr@heineken.nl","0612392182");
                Inschrijvingsnummer inschrijvingsnummer = new Inschrijvingsnummer(1,event);
                inschrijvingsnummer.setBedrijf(bedrijf);
                InschrijvingEventViewModel vmInsch = ViewModelProviders.of(this).get(InschrijvingEventViewModel.class);
                vmInsch.create(inschrijvingsnummer);

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(view.getContext());
                dlgAlert.setMessage("U bent ingeschreven voor " + event.getNaam());
                dlgAlert.setTitle("Ingeschreven");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(false);
                dlgAlert.create().show();

            }catch (Exception e){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(view.getContext());
                dlgAlert.setMessage("Probeer opnieuw");
                dlgAlert.setTitle("Er is een fout opgetreden");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        });
        return view;
    }

    @Override
    public int title() {
        return R.string.event_inschrijven;
    }
}
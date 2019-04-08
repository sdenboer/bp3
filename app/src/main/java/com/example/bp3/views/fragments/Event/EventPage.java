package com.example.bp3.views.fragments.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author Koen Franken
 */

public class EventPage extends ViewFragment{
    AanbodEvent event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventpage, container, false);
        event = (AanbodEvent) getArguments().getSerializable("event");
        Button btnInschrijf = (Button) view.findViewById(R.id.EventbtnInschrijf);

        TextView txtNaam = (TextView) view.findViewById(R.id.textNaamevent);
        txtNaam.setText("Naam: " +  event.getNaam());
        TextView txtSoort = (TextView) view.findViewById(R.id.textSoortevent);
        txtSoort.setText("Soort event: " +  event.getSoort().getSoort());
        TextView txtDatum = (TextView) view.findViewById(R.id.textDatumevent);
        txtDatum.setText("Datum: " +  event.getDatumentijd());
        TextView txtLocatie = (TextView) view.findViewById(R.id.textLocatieevent);
        txtLocatie.setText("Locatie: " +  event.getLocatie());
        TextView txtAantalp = (TextView) view.findViewById(R.id.textAantalpevent);
        txtAantalp.setText("Aantal plekken over: " +  event.getAantalPersonen());
        TextView txtContact = (TextView) view.findViewById(R.id.textContactPevent);

        if(event.getAantalPersonen() < 1){
            btnInschrijf.setEnabled(false);
            txtAantalp.setText("Sorry dit evenement is VOL");
        }

        try {
            txtContact.setText("Contactpersoon: " + event.getBedrijf().getEmail());
        }catch (Exception e){}
        try {
            txtContact.setText("Contactpersoon: " + event.getDocent().getEmail());
        }catch (Exception e){}
        TextView txtOmschrijf = (TextView) view.findViewById(R.id.textOmschrijvingevent);
        txtOmschrijf.setText("Omschrijving: " +  event.getOmschrijving());

        btnInschrijf.setOnClickListener(v -> {
            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            Fragment frag = new EventInschrijven();
            Bundle bundle = new Bundle();
            bundle.putSerializable("eventinschrijf", event);
            frag.setArguments(bundle);
            t.addToBackStack(null);
            t.replace(R.id.fragment_container, frag);
            t.commit();

            System.out.println(event.getNaam());
        });
        return view;
    }

    @Override
    public int title() {
        return R.string.event_page;
    }
}
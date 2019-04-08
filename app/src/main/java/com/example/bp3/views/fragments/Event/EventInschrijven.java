package com.example.bp3.views.fragments.Event;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Inschrijvingsnummer;
import com.example.bp3.service.models.Student;
import com.example.bp3.viewmodels.AanbodEventViewModel;
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
                int aantal = Integer.parseInt(aantalP.getText().toString());
                if(aantal > event.getAantalPersonen()) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(view.getContext());
                    dlgAlert.setMessage("U kunt maar voor " + event.getAantalPersonen() + " personen inschrijven");
                    dlgAlert.setTitle("Fout");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }else{
                    if (Account.currentUser instanceof Docent) {
                        Docent docent = (Docent) Account.currentUser;
                        Inschrijvingsnummer inschrijvingsnummer = new Inschrijvingsnummer(aantal,event);
                        inschrijvingsnummer.setDocent(docent);
                        InschrijvingEventViewModel vmInsch = ViewModelProviders.of(this).get(InschrijvingEventViewModel.class);
                        vmInsch.create(inschrijvingsnummer);

                    } else if (Account.currentUser instanceof Student) {
                        Student student = (Student) Account.currentUser;
                        Inschrijvingsnummer inschrijvingsnummer = new Inschrijvingsnummer(aantal,event);
                        inschrijvingsnummer.setStudent(student);
                        InschrijvingEventViewModel vmInsch = ViewModelProviders.of(this).get(InschrijvingEventViewModel.class);
                        vmInsch.create(inschrijvingsnummer);

                    } else if (Account.currentUser instanceof Bedrijf) {
                        Bedrijf bedrijf = (Bedrijf) Account.currentUser;
                        Inschrijvingsnummer inschrijvingsnummer = new Inschrijvingsnummer(aantal,event);
                        inschrijvingsnummer.setBedrijf(bedrijf);
                        InschrijvingEventViewModel vmInsch = ViewModelProviders.of(this).get(InschrijvingEventViewModel.class);
                        vmInsch.create(inschrijvingsnummer);
                    }
                    AanbodEventViewModel vmaan = ViewModelProviders.of(this).get(AanbodEventViewModel.class);
                    event.setAantalPersonen(event.getAantalPersonen()- aantal);
                    vmaan.update(event);

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(view.getContext());
                    dlgAlert.setMessage("U hebt zich ingrschreven voor " + event.getNaam());
                    dlgAlert.setTitle("Ingeschreven");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.popBackStack();
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
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
package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.bp3.service.models.Inschrijvingsnummer;
import com.example.bp3.service.repository.EventRepository;

import java.io.Serializable;

public class InschrijvingEventViewModel extends AndroidViewModel implements Serializable {

    public Inschrijvingsnummer inschrijvingsnummer;

    public InschrijvingEventViewModel(Application application) {
        super(application);
    }

    public void create(Inschrijvingsnummer inschrijvingsnummer) {
        EventRepository.getInstance().createInschrijving(inschrijvingsnummer);
    }

}

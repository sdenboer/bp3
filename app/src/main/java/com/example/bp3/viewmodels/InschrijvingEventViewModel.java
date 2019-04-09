package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.example.bp3.service.models.Inschrijvingsnummer;
import com.example.bp3.service.repository.EventInschrijfRepository;
import java.io.Serializable;

/**
 * @author Koen Franken
 */

public class InschrijvingEventViewModel extends AndroidViewModel implements Serializable {

    public Inschrijvingsnummer inschrijvingsnummer;

    public InschrijvingEventViewModel(Application application) {
        super(application);
    }

    public Inschrijvingsnummer getInschrijvingsnummer() {
        return inschrijvingsnummer;
    }

    public void create(Inschrijvingsnummer inschrijvingsnummer) {
        EventInschrijfRepository.getInstance().create(inschrijvingsnummer);
    }
}

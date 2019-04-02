package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.repository.OpdrachtRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OpdrachtViewModel extends AndroidViewModel {
    private Opdracht opdracht;

    public OpdrachtViewModel(@NonNull Application application) {
        super(application);
    }

    public void prepareCreate(Map<String, String> values, Docent docent) {
        int leerjaar = Integer.valueOf(Objects.requireNonNull(values.get("leerjaar")));
        String opdrachtNaam = values.get("opdrachtNaam");
        String lesvak = values.get("lesvak");
        String eisen = values.get("eisen");
        int aantStudMin =  Integer.valueOf(Objects.requireNonNull(values.get("maxStud")));
        int aantStudMax =  Integer.valueOf(Objects.requireNonNull(values.get("minStud")));
        String deadline = getUglyDeadline(values.get("deadline"));
        this.opdracht = new Opdracht(leerjaar,opdrachtNaam, lesvak, eisen, aantStudMin, aantStudMax, docent, deadline);
        this.create();
    }

    private String getUglyDeadline(String deadline){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
            LocalDateTime time =  LocalDateTime.parse(deadline);
            return formatter.format(time);
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy hh:mm");
//            Date date = df.parse(deadline);
    }

    private void create(){
        OpdrachtRepository.getInstance().create(this.opdracht);
    }
}

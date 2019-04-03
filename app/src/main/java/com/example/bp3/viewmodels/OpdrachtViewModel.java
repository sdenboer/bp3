package com.example.bp3.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.repository.OpdrachtRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OpdrachtViewModel extends AndroidViewModel {
    private Opdracht opdracht;
    private Toast onSuccess, onError;

    public OpdrachtViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("ShowToast")
    public void prepareCreate(Map<String, String> values, Docent docent, String onSuccess, String onError) { //VERWIJDER DOCENT
        this.onSuccess = Toast.makeText(this.getApplication().getApplicationContext(),onSuccess,Toast.LENGTH_SHORT);
        this.onError = Toast.makeText(this.getApplication().getApplicationContext(), onError,Toast.LENGTH_SHORT);
        int leerjaar = Integer.valueOf(Objects.requireNonNull(values.get("leerjaar")));
        String opdrachtNaam = values.get("opdrachtnaam");
        String lesvak = values.get("lesvak");
        String eisen = values.get("eisen");
        int aantStudMin =  Integer.valueOf(Objects.requireNonNull(values.get("minStud")));
        int aantStudMax =  Integer.valueOf(Objects.requireNonNull(values.get("maxStud")));
        String deadline = getUglyDeadline(values.get("deadline"));
        this.opdracht = new Opdracht(leerjaar,opdrachtNaam, lesvak, eisen, aantStudMin, aantStudMax, docent, deadline);
        this.create();
    }

    public LiveData<List<Opdracht>> getMyPosted(Docent docent) {
       return OpdrachtRepository.getInstance().getMyPostedOpdrachten(docent); //VERWIJDER DOCENT
    }

    public void delete(Opdracht opdracht, Toast onSuccess, Toast onError) {
        OpdrachtRepository.getInstance().delete(opdracht, onSuccess, onError);
    }

    private String getUglyDeadline(String deadline){
            DateTimeFormatter dlold = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
            DateTimeFormatter dlnew = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime time =  LocalDateTime.parse(deadline, dlold);
            return dlnew.format(time);
    }

    private void create(){
        OpdrachtRepository.getInstance().create(this.opdracht, onSuccess, onError);
    }
}

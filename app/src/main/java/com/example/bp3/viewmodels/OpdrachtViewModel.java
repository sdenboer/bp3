package com.example.bp3.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.Tag;
import com.example.bp3.service.repository.OpdrachtRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author sven
 */
public class OpdrachtViewModel extends AndroidViewModel implements Serializable {
    private Opdracht opdracht;
    public LiveData<Opdracht> liveOpdracht;
    private Toast onSuccess, onError;

    public OpdrachtViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("ShowToast")
    public void create(String opdrachtnaam, String minStud, String maxStud, String eisen, String leerjaar, String lesvak, String deadline, List<String> stTags, String onSuccess, String onError) {
        this.onSuccess = Toast.makeText(this.getApplication().getApplicationContext(),onSuccess,Toast.LENGTH_SHORT);
        this.onError = Toast.makeText(this.getApplication().getApplicationContext(), onError,Toast.LENGTH_SHORT);
        Docent docent = (Docent) Account.currentUser;
        this.opdracht = new Opdracht(Integer.valueOf(leerjaar),
                opdrachtnaam, lesvak, eisen, Integer.valueOf(minStud),
                Integer.valueOf(maxStud), docent, getUglyDeadline(deadline));
        ArrayList<Tag> tags = stTags.stream().map(Tag::new).collect(Collectors.toCollection(ArrayList::new));
        this.opdracht.setTags(tags);
        this.sendCreateToRepo();
    }

    public LiveData<List<Opdracht>> getMyPosted() {
       return OpdrachtRepository.getInstance().getMyPostedOpdrachten();
    }


    public void delete(Opdracht opdracht) {
        OpdrachtRepository.getInstance().delete(opdracht);
    }

    @SuppressLint("ShowToast")
    public void update(String opdrachtnaam, String minStud, String maxStud, String eisen, String leerjaar, String lesvak, String deadline, List<String> stTags, String onSuccess, String onError) {
        this.onSuccess = Toast.makeText(this.getApplication().getApplicationContext(),onSuccess,Toast.LENGTH_SHORT);
        this.onError = Toast.makeText(this.getApplication().getApplicationContext(), onError,Toast.LENGTH_SHORT);
        this.opdracht.setOpdrachtNaam(opdrachtnaam);
        this.opdracht.setAantStudMin(Integer.valueOf(minStud));
        this.opdracht.setAantStudMax(Integer.valueOf(maxStud));
        this.opdracht.setLeerjaar(Integer.valueOf(leerjaar));
        this.opdracht.setEisen(eisen);
        this.opdracht.setDeadline(getUglyDeadline(deadline));
        this.opdracht.setLesvak(lesvak);
        ArrayList<Tag> tags = stTags.stream().map(Tag::new).collect(Collectors.toCollection(ArrayList::new));
        this.opdracht.setTags(tags);
        sendUpdateToRepo(this.opdracht);
    }

    private void sendUpdateToRepo(Opdracht opdracht) {
        OpdrachtRepository.getInstance().update(opdracht);
    }

    public LiveData<List<Opdracht>> getByTags(Tag tag) {
       return OpdrachtRepository.getInstance().getByTags(tag);
    }


    public LiveData<Opdracht> getOpdrachtById(int id, Fragment fragment){
        this.liveOpdracht = OpdrachtRepository.getInstance().getOpdrachtById(id);
        this.liveOpdracht.observe(fragment, opdracht-> this.opdracht =  opdracht);
        return this.liveOpdracht;
    }

    private String getUglyDeadline(String deadline){
            DateTimeFormatter dlold = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
            DateTimeFormatter dlnew = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime time =  LocalDateTime.parse(deadline, dlold);
            return dlnew.format(time);
    }



    private void sendCreateToRepo(){
        OpdrachtRepository.getInstance().create(this.opdracht, onSuccess, onError);
    }

    public String getPrettyDeadline(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        LocalDateTime time =  LocalDateTime.parse (deadline.replaceAll("\\+(.*)$", ""));
        return formatter.format(time);
    }
}


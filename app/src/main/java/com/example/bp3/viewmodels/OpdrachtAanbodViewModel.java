package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.bp3.service.models.Activiteit;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.repository.OpdrachtAanbodRepository;
import com.example.bp3.service.repository.RestApiHelper;

import java.io.Serializable;
import java.util.List;

public class OpdrachtAanbodViewModel extends AndroidViewModel implements Serializable {

    public OpdrachtAanbod opdrachtAanbod;

    public String bedrijfsNaam() {
        return opdrachtAanbod.getBedrijf().getNaam();
    }

    public LiveData<List<OpdrachtAanbod>> getStudentZietEigenOpdrachten(String email) {
        return OpdrachtAanbodRepository.getInstance().studentZietEigenOpdrachten(email);
    }

    public String deadline() {
        return opdrachtAanbod.getOpdracht().getDeadline();
    }

    public OpdrachtAanbodViewModel(Application application) {
        super(application);
    }

    public OpdrachtAanbod getOpdrachtAanbod() {
        return opdrachtAanbod;
    }

    public void setOpdrachtAanbod(OpdrachtAanbod opdrachtAanbod) {
        this.opdrachtAanbod = opdrachtAanbod;
    }

    public LiveData<List<OpdrachtAanbod>> getStudentZietOpdrachten(String instelling, String opleiding, Integer leerjaar) {
        return OpdrachtAanbodRepository.getInstance().studentZietOpdrachtenLeskvak(instelling, opleiding, leerjaar);
    }

    public LiveData<List<OpdrachtAanbod>> getOpdrachtAanbodByVraagId(int id) {
        return OpdrachtAanbodRepository.getInstance().opdrachtAanbodByVraagId(id);
    }

    public void create(OpdrachtAanbod opdrachtAanbod) {
        OpdrachtAanbodRepository.getInstance().create(opdrachtAanbod);
    }

    public void update(OpdrachtAanbod opdrachtAanbod) {
        OpdrachtAanbodRepository.getInstance().update(opdrachtAanbod);
    }

    public void delete(OpdrachtAanbod opdrachtAanbod) {
        OpdrachtAanbodRepository.getInstance().delete(opdrachtAanbod);
    }

}

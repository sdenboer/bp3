package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Activiteit;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.service.repository.OpdrachtAanbodRepository;
import com.example.bp3.service.repository.RestApiHelper;

import java.io.Serializable;
import java.util.List;
/**
 * @author sven
 */
public class OpdrachtAanbodViewModel extends AndroidViewModel implements Serializable {

    public LiveData<List<OpdrachtAanbod>> getStudentZietEigenOpdrachten() {
        Student student = (Student) Account.currentUser;
        return OpdrachtAanbodRepository.getInstance().studentZietEigenOpdrachten(student.getEmail());
    }

    public OpdrachtAanbodViewModel(Application application) {
        super(application);
    }


    public LiveData<List<OpdrachtAanbod>> getBedrijfZietOpdrachten() {
        Bedrijf bedrijf = (Bedrijf)Account.currentUser;
        return OpdrachtAanbodRepository.getInstance().bedrijfZietEigenOpdrachten(bedrijf.getEmail());
    }

    public LiveData<List<OpdrachtAanbod>> getStudentZietOpdrachten() {
        Student student = (Student) Account.currentUser;
        String instelling = student.getOpleiding().getOpleidingPK().getOnderwijsinstelling();
        String opleiding = student.getOpleiding().getOpleidingPK().getOpleidingsnaam();
        int leerjaar = student.getLeerjaar();
        return OpdrachtAanbodRepository.getInstance().studentZietOpdrachtenLeskvak(instelling, opleiding, leerjaar);
    }

    public LiveData<OpdrachtAanbod> getOpdrachtAanbodById(OpdrachtAanbod opdracht) {
        return OpdrachtAanbodRepository.getInstance().getById(opdracht);
    }

    public void create(String eisen, Opdracht opdracht) {
        Bedrijf bedrijf = (Bedrijf) Account.currentUser;
        OpdrachtAanbod opdrachtAanbod = new OpdrachtAanbod(opdracht, bedrijf, eisen);
        OpdrachtAanbodRepository.getInstance().create(opdrachtAanbod);
    }

    public void update(String eisen, OpdrachtAanbod opdrachtAanbod) {
        opdrachtAanbod.setBeschrijving(eisen);
        OpdrachtAanbodRepository.getInstance().update(opdrachtAanbod);
    }

    public void delete(OpdrachtAanbod opdrachtAanbod) {
        OpdrachtAanbodRepository.getInstance().delete(opdrachtAanbod);
    }

}

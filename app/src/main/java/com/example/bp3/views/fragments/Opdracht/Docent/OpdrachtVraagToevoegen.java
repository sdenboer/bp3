package com.example.bp3.views.fragments.Opdracht.Docent;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bp3.R;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Lesvak;
import com.example.bp3.service.models.Opleiding;
import com.example.bp3.viewmodels.OpdrachtViewModel;
import com.example.bp3.views.fragments.Opdracht.DateAndTimePicker;
import com.example.bp3.views.fragments.Opdracht.OpdrachtAanbodDetails;
import com.example.bp3.views.fragments.Opdracht.OpdrachtAanbodTeamMembersDialog;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class OpdrachtVraagToevoegen extends ViewFragment {
    private EditText mOpdrachtnaam, mStudMin, mStudMax, mEisen;
    private Button mDeadline, mToevoegen;
    private Spinner mLeerjaar, mLesvak;
    private int jaar, maand, dag, minuut, uur;
    private TextView mDisplayedDeadline;

    private Docent mDocent;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEst", "test");
        Lesvak info = new Lesvak("informatica");
        Lesvak program = new Lesvak("program");
        ArrayList<Lesvak> lesvaks = new ArrayList<>();
        lesvaks.add(info);
        lesvaks.add(program);
        Opleiding opleiding = new Opleiding("hbo", "Avans", "Ad Informatica", lesvaks);
        mDocent = new Docent("docent@avans.nl", "1234", "john", "045214213", opleiding, lesvaks);
        final View root = inflater.inflate(R.layout.fragment_opdrachtvraag_add, container, false);
        mLesvak = root.findViewById(R.id.opdrachtvraag_add_lesvak);
        getLayoutElements(root);
        setLesvakkenDropdown(root);
        mToevoegen.setOnClickListener(event -> {
            if (validateInput()) {
                Toast toast=Toast.makeText(getActivity(),"Gelukt",Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast=Toast.makeText(getActivity(),"Niet gelukt!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return root;
    }


    @Override
    public int title() {
        return R.string.projecten_opdrachten;
    }

    private void getLayoutElements(View root) {
        mDeadline = root.findViewById(R.id.opdrachtvraag_deadline);
        mLesvak = root.findViewById(R.id.opdrachtvraag_add_lesvak);
        mOpdrachtnaam = root.findViewById(R.id.opdrachtvraag_add_opdrachtnaam);
        mStudMin = root.findViewById(R.id.opdrachtvraag_add_min);
        mStudMax = root.findViewById(R.id.opdrachtvraag_add_max);
        mEisen = root.findViewById(R.id.opdrachtvraag_add_eisen);
        mDeadline = root.findViewById(R.id.opdrachtvraag_add_deadline) ;
        mToevoegen = root.findViewById(R.id.opdrachtvraag_toevoegen);
        mLeerjaar = root.findViewById(R.id.opdrachtvraag_add_leerjaar);
        mDeadline.setOnClickListener(event -> new DateAndTimePicker().show(getFragmentManager(), ""));
    }

    private void setLesvakkenDropdown(View root) {
        ArrayList<String> lesvakken = new ArrayList<>();
        mDocent.getLesvakken().forEach(lesvak -> lesvakken.add(lesvak.getLesvak()));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, lesvakken);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLesvak.setAdapter(adapter);
    }

    public void updateDeadline(Map<String, Integer> deadline) {
        deadline.forEach((k,v)->{
            switch (k) {
                case "jaar":
                    jaar = v;
                    break;
                case "maand":
                    maand = v;
                    break;
                case "dag":
                    dag = v;
                    break;
                case "uur":
                    uur = v;
                    break;
                case "minuut":
                    minuut = v;
                    break;
            }
        });
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        LocalDateTime time =  LocalDateTime.of(jaar, maand, dag, uur, minuut);
        this.mDisplayedDeadline.setText(formatter.format(time));
    }

    public boolean validateInput() {
        Map<String, String> data = new HashMap<>();
        data.put("opdrachtnaam", mOpdrachtnaam.getText().toString());
        data.put("minStud", mStudMin.getText().toString());
        data.put("maxStud", mStudMax.getText().toString());
        data.put("eisen", mEisen.getText().toString());
        data.put("leerjaar", mLeerjaar.getSelectedItem().toString());
        data.put("lesvak", mLesvak.getSelectedItem().toString());
        data.put("deadline", mDisplayedDeadline.getText().toString());
        if (data.values().stream().anyMatch(String::isEmpty)) {
            return false;
        }
        sendToViewModel(data);
        return true;
    }

    public void sendToViewModel(Map<String, String> data) {
        OpdrachtViewModel vm = ViewModelProviders.of(this).get(OpdrachtViewModel.class);
        vm.prepareCreate(data, mDocent);
    }
}
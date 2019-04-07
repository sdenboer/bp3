package com.example.bp3.views.fragments.Opdracht.Docent;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.viewmodels.OpdrachtViewModel;
import com.example.bp3.viewmodels.TagViewModel;
import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenDocent;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sven
 */
public class OpdrachtVraagAdd extends ViewFragment {
    private EditText mOpdrachtnaam, mStudMin, mStudMax, mEisen, mTags;
    private Button mDeadline, mToevoegen;
    private Spinner mLeerjaar, mLesvak;
    private int jaar, maand, dag, minuut, uur;
    private TextView mDisplayedDeadline;
    private View root;
    private OpdrachtViewModel ovm;
    private TagViewModel tvm;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_opdrachtvraag_add, container, false);
        this.ovm = ViewModelProviders.of(this).get(OpdrachtViewModel.class);
        this.tvm = ViewModelProviders.of(this).get(TagViewModel.class);
        getLayoutElements();
        if (getArguments() != null) {
            this.setLayoutElements();
        } else {
            mLesvak.setAdapter(setLesvakkenDropdown());
        }
        mLesvak = root.findViewById(R.id.opdrachtvraag_add_lesvak);
        mToevoegen.setOnClickListener(event -> validateInput());
        return root;
    }

    @Override
    public int title() {
        return R.string.projecten_opdrachten;
    }

    private void getLayoutElements() {
        mDeadline = root.findViewById(R.id.opdrachtvraag_add_deadline);
        mLesvak = root.findViewById(R.id.opdrachtvraag_add_lesvak);
        mOpdrachtnaam = root.findViewById(R.id.opdrachtvraag_add_opdrachtnaam);
        mStudMin = root.findViewById(R.id.opdrachtvraag_add_min);
        mStudMax = root.findViewById(R.id.opdrachtvraag_add_max);
        mEisen = root.findViewById(R.id.opdrachtvraag_add_eisen);
        mToevoegen = root.findViewById(R.id.opdrachtvraag_toevoegen);
        mLeerjaar = root.findViewById(R.id.opdrachtvraag_add_leerjaar);
        mDisplayedDeadline = root.findViewById(R.id.opdrachtvraag_deadline);
        mTags = root.findViewById(R.id.opdrachtvraag_tags);
        mDeadline.setOnClickListener(event -> new DateAndTimePicker().show(getFragmentManager(), ""));
    }

    private void setLayoutElements() {
        assert getArguments() != null;
        this.ovm = (OpdrachtViewModel) getArguments().getSerializable("opdracht");
        int id = (int) getArguments().getSerializable("opdrachtId");
        LiveData<Opdracht> data = ovm.getOpdrachtById(id, this);
        data.observe(this, opdracht -> {
            mDisplayedDeadline.setText(ovm.getPrettyDeadline(opdracht.getDeadline()));
            mOpdrachtnaam.setText(opdracht.getOpdrachtNaam());
//            mLeerjaar = opdracht.getLeerjaar();
            mEisen.setText(opdracht.getEisen());
            mStudMax.setText(String.valueOf(opdracht.getAantStudMax()));
            mStudMin.setText(String.valueOf(opdracht.getAantStudMin()));
            setLesvakkenDropdownValue(opdracht.getLesvak());
        });
    }

    private void setLesvakkenDropdownValue(String lesvak) {
        ArrayAdapter<String> adapter = setLesvakkenDropdown();
        mLesvak.setSelection(adapter.getPosition(lesvak));
        mLesvak.setAdapter(adapter);
    }

    private ArrayAdapter<String> setLesvakkenDropdown() {
        ArrayList<String> lesvakken = new ArrayList<>();
        Docent doc = (Docent) Account.currentUser;
        doc.getLesvakken().forEach(lesvak -> lesvakken.add(lesvak.getLesvak()));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, lesvakken);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public void updateDeadline(Map<String, Integer> deadline) {
        deadline.forEach((k, v) -> {
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
        LocalDateTime time = LocalDateTime.of(jaar, maand, dag, uur, minuut);
        this.mDisplayedDeadline.setText(formatter.format(time));
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (getArguments() != null) {
//            this.getArguments().clear();
////        }
//    }

    public void validateInput() {
        List<String> tags = Stream.of(mTags.getText().toString().split(",")).collect(Collectors.toList());
        tags.replaceAll(String::trim);
        String opdrachtnaam = mOpdrachtnaam.getText().toString();
        String minStud = mStudMin.getText().toString();
        String maxStud = mStudMax.getText().toString();
        String eisen = mEisen.getText().toString();
        String leerjaar = mLeerjaar.getSelectedItem().toString();
        String lesvak = mLesvak.getSelectedItem().toString();
        String deadline = mDisplayedDeadline.getText().toString();
        boolean valid = Stream.of(opdrachtnaam, minStud, maxStud, eisen, leerjaar, lesvak, deadline, mTags.getText().toString())
                .allMatch(StringUtils::isNotBlank);
        if (valid && getArguments() != null) {
            tvm.create(tags);
            ovm.update(opdrachtnaam, minStud, maxStud, eisen, leerjaar, lesvak, deadline, tags, "De opdracht is geupdate", "Er is iets fout gegaan");
            redirectOnSuccess();
        } else if (valid) {
            tvm.create(tags);
            ovm.create(opdrachtnaam, minStud, maxStud, eisen, leerjaar, lesvak, deadline, tags, "De opdracht is toegevoegd", "Er is iets fout gegaan");
            redirectOnSuccess();
        } else {
            Toast toast = Toast.makeText(getActivity(), "Vul alle velden in!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void redirectOnSuccess() {
        Fragment fragment = new MijnActiviteitenDocent();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment, "fragment");
        ft.commit();
    }
}

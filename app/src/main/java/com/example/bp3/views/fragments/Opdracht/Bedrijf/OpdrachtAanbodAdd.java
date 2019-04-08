package com.example.bp3.views.fragments.Opdracht.Bedrijf;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bp3.R;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenBedrijf;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

/**
 * @author sven
 */
public class OpdrachtAanbodAdd extends ViewFragment {
    private Button toevoegen;
    private EditText edEisen;
    private View root;
    private Opdracht opdracht;
    private OpdrachtAanbod opdrachtAanbod;
    private OpdrachtAanbodViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_opdrachtaanbod_add, container, false);
        assert getArguments() != null;
        opdracht = (Opdracht) getArguments().getSerializable("opdracht");
        opdrachtAanbod = (OpdrachtAanbod) getArguments().getSerializable("opdrachtAanbod");
        vm = ViewModelProviders.of(this).get(OpdrachtAanbodViewModel.class);
        getLayoutElements();
        if (getArguments().getSerializable("opdrachtAanbod") != null) {
            this.setLayoutElements();
        }
        toevoegen.setOnClickListener(e -> validateInput());
        return root;
    }

    private void getLayoutElements() {
        edEisen = root.findViewById(R.id.opdrachtaanbod_add_eisen);
        toevoegen = root.findViewById(R.id.opdrachtaanbod_add_btn);
    }

    private void setLayoutElements() {
        toevoegen.setText(getString(R.string.bewerken));
        LiveData<OpdrachtAanbod> data = vm.getOpdrachtAanbodById(opdrachtAanbod);
        data.observe(this, o -> edEisen.setText(opdrachtAanbod.getBeschrijving()));
    }

    public void validateInput() {
        String eisen = edEisen.getText().toString();
        boolean valid = Stream.of(eisen).allMatch(StringUtils::isNotBlank);
        if (valid && opdrachtAanbod != null) {
            vm.update(eisen, opdrachtAanbod);
            redirectOnSuccess();
        } else if (valid && opdracht != null) {
            vm.create(eisen, opdracht);
            redirectOnSuccess();
        } else {
            Toast toast = Toast.makeText(getActivity(), "Vul alle velden in!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void redirectOnSuccess() {
        Fragment fragment = new MijnActiviteitenBedrijf();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        assert getArguments() != null;
        getArguments().clear();
        ft.replace(R.id.fragment_container, fragment, "fragment");
        ft.commit();
    }


    @Override
    public int title() {
        return R.string.opdracht_toevoegen;
    }
}

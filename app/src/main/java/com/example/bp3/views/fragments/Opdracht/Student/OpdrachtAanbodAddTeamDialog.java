package com.example.bp3.views.fragments.Opdracht.Student;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.viewmodels.TeamViewModel;

public class OpdrachtAanbodAddTeamDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OpdrachtAanbod oa = (OpdrachtAanbod) getArguments().getSerializable("opdracht");
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_team, container, false);
        EditText mNaam = root.findViewById(R.id.dialog_team_add_naam);
        Button add = root.findViewById(R.id.dialog_team_add_button);
        add.setOnClickListener(e -> {
            TeamViewModel tvm = ViewModelProviders.of(this).get(TeamViewModel.class);
            String naam = createUniqueTeamNaam(mNaam.getText().toString(), oa);
            tvm.create(naam, oa);
            this.dismiss();
        });
        return root;
    }

    private String createUniqueTeamNaam(String naam, OpdrachtAanbod opdrachtAanbod) {
        StringBuilder sb = new StringBuilder();
        sb.append(opdrachtAanbod.getOpdracht().getLeerjaar());
        sb.append(opdrachtAanbod.getOpdracht().getOpdrachtNaam().replace(" ", ""));
        sb.append(" ");
        sb.append(naam);
        return sb.toString();
    }
}

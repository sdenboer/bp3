package com.example.bp3.views.fragments.MijnActiviteiten;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Lesvak;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.Opleiding;
import com.example.bp3.viewmodels.OpdrachtViewModel;
import com.example.bp3.views.adapters.OpdrachtAdapter;
import com.example.bp3.views.fragments.Opdracht.OpdrachtAanbodDetails;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.util.ArrayList;

public class MijnActiviteitenDocent extends ViewFragment {
    private Opdracht opdracht;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_mijn_activiteiten_docent, container, false);
        setRecyclerView(root);
        return root;
    }

    private void setRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.opdracht_docent_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        OpdrachtAdapter adapter = setAdapter();
        recyclerView.setAdapter(adapter);
    }

    private OpdrachtAdapter setAdapter() {
        OpdrachtAdapter adapter = new OpdrachtAdapter(this);
        OpdrachtViewModel vm = ViewModelProviders.of(this).get(OpdrachtViewModel.class);
        Lesvak info = new Lesvak("informatica");
        Lesvak program = new Lesvak("program");
        ArrayList<Lesvak> lesvaks = new ArrayList<>();
        lesvaks.add(info);
        lesvaks.add(program);
        Opleiding opleiding = new Opleiding("hbo", "Avans", "Ad Informatica", lesvaks);
        Docent docent = new Docent("BKatwijk@avans.nl", "wachtwoord", "Bregtje van Katwijk", "0699999999", opleiding, lesvaks);
        vm.getMyPosted(docent).observe(this, adapter::setOpdrachten);
//        adapter.setOnItemClickListener(this::onItemClick);
        return adapter;
    }

//    private void onItemClick(Opdracht opdracht) {
//
//    }


    @Override
    public int title() {
        return R.string.projecten_mystuff;
    }
}

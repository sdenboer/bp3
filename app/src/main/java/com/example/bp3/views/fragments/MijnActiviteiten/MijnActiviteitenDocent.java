package com.example.bp3.views.fragments.MijnActiviteiten;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.viewmodels.OpdrachtViewModel;
import com.example.bp3.views.adapters.DocentOpdrachtAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author sven
 */
public class MijnActiviteitenDocent extends ViewFragment {
    private OpdrachtViewModel vm;
    private DocentOpdrachtAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View root = inflater.inflate(R.layout.fragment_mijn_activiteiten, container, false);
        setRecyclerView(root);
        return root;
    }

    private void setRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.opdracht_docent_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        DocentOpdrachtAdapter adapter = setAdapter();
        recyclerView.setAdapter(adapter);
    }

    private DocentOpdrachtAdapter setAdapter() {
        adapter = new DocentOpdrachtAdapter(this);
        vm = ViewModelProviders.of(this).get(OpdrachtViewModel.class);
        vm.getMyPosted().observe(this, adapter::setOpdrachten);
        return adapter;
    }

    @Override
    public int title() {
        return R.string.projecten_mystuff;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        vm.getMyPosted().removeObserver(adapter::setOpdrachten);
    }
}

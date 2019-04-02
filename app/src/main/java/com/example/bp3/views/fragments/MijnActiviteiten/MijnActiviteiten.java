package com.example.bp3.views.fragments.MijnActiviteiten;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;
import com.example.bp3.views.fragments.Opdracht.OpdrachtAanbodDetails;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

public class MijnActiviteiten extends ViewFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_mijn_activiteiten, container, false);
        setRecyclerView(root);
        return root;
    }


    @Override
    public int title() {
        return R.string.projecten_mystuff;
    }

    private void setRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.opdracht_lesvak_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        OpdrachtAanbodAdapter adapter = setAdapter();
        recyclerView.setAdapter(adapter);
    }

    private OpdrachtAanbodAdapter setAdapter() {
        OpdrachtAanbodAdapter adapter = new OpdrachtAanbodAdapter();
        OpdrachtAanbodViewModel vm = ViewModelProviders.of(this).get(OpdrachtAanbodViewModel.class);
        vm.getStudentZietEigenOpdrachten("jklaas@student.avans.nl").observe(this, adapter::setOpdrachtAanbod);
        adapter.setOnItemClickListener(this::onItemClick);
        return adapter;
    }

    private void onItemClick(OpdrachtAanbod opdrachtAanbod) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("opdracht", opdrachtAanbod);
        Fragment fragment = new OpdrachtAanbodDetails();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}

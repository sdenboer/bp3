package com.example.bp3.views.fragments.Opdracht.Student;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.databinding.FragmentOpdrachtaanbodDetailsBinding;
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.viewmodels.TeamViewModel;
import com.example.bp3.views.adapters.TeamAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;


/**
 * @author sven
 */
public class OpdrachtAanbodDetails extends ViewFragment {
    FragmentOpdrachtaanbodDetailsBinding coadb;
    private TextView tvBedrijf, tvDeadline;
    private Button btnContact, btnToevoegen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OpdrachtAanbod oa = (OpdrachtAanbod) getArguments().getSerializable("opdracht");
        coadb = DataBindingUtil.inflate(inflater, R.layout.fragment_opdrachtaanbod_details, container, false);
        coadb.setOa(oa);
        View root = coadb.getRoot();

        tvBedrijf = root.findViewById(R.id.aanbod_bedrijf);
        tvBedrijf.setText(oa.getBedrijf().getNaam());
        tvDeadline = root.findViewById(R.id.aanbod_deadline);
        tvDeadline.setText(oa.getOpdracht().getPrettyDeadline());
        btnContact = root.findViewById(R.id.aanbod_contact);
        btnContact.setOnClickListener(e -> Log.d("Bedrijf", "Go to bedrijf"));
        btnToevoegen = root.findViewById(R.id.team_add_button);
        btnToevoegen.setOnClickListener(e -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("opdracht", oa);
            DialogFragment fragment = new OpdrachtAanbodAddTeamDialog();
            fragment.setArguments(bundle);
            fragment.show(getFragmentManager(), "");
        });

        if (Account.currentUser instanceof Bedrijf) {
            btnToevoegen.setVisibility(View.GONE);
            btnContact.setVisibility(View.GONE);
        }

        RecyclerView recyclerView = root.findViewById(R.id.opdrachtaanbod_teams_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        TeamViewModel vm = ViewModelProviders.of(this).get(TeamViewModel.class);
        final TeamAdapter adapter = new TeamAdapter();
        vm.beschikbareTeams(oa.getId()).observe(getActivity(), adapter::setTeams);
        adapter.setOnItemClickListener(t -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("opdracht", oa);
            bundle.putSerializable("team", t);
            DialogFragment fragment = new OpdrachtAanbodTeamMembersDialog();
            fragment.setArguments(bundle);
            fragment.show(getFragmentManager(), "");
        });
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public int title() {
        return R.string.projecten_opdrachten_details;
    }

}

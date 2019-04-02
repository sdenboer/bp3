package com.example.bp3.views.fragments.Opdracht;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.views.adapters.TeamMemberAdapter;

public class TeamMembersDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OpdrachtAanbod oa = (OpdrachtAanbod) getArguments().getSerializable("opdracht");
        Team t = (Team) getArguments().getSerializable("team");
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_team_members, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.opdrachtaanbod_members_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TeamMemberAdapter adapter = new TeamMemberAdapter(this, getActivity().getApplication(), oa);
        adapter.setTeams(t);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(s -> {
            try {
                Log.d("Check profiel student", s.getEmail());
            } catch (NullPointerException e) {
                Log.d("Add yourself", "FUNCIT");
            }

        });

        return root;
    }
}

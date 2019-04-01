//package com.example.bp3.views.adapters;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.bp3.R;
//import com.example.bp3.service.models.Student;
//import com.example.bp3.service.models.Team;
//import com.example.bp3.service.models.Team;
//import com.example.bp3.views.adapters.viewHolders.TeamHolder;
//import com.example.bp3.views.adapters.viewHolders.TeamMemberHolder;
//import com.example.bp3.libraries.ExpandableRecyclerViewAdapter;
//import com.example.bp3.libraries.models.ExpandableGroup;
//import com.example.bp3.libraries.viewholders.ChildViewHolder;
//import com.example.bp3.libraries.viewholders.GroupViewHolder;
//
//import java.util.ArrayList;
//import java.util.List;

package com.example.bp3.views.adapters;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Team;
import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {

    private List<Team> teams = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_teamnaam, viewGroup, false);
        return new TeamHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder teamHolder, int i) {
        Team t = this.teams.get(i);
        teamHolder.getTeamNaam().setText(t.getTeamNaam().replaceAll("^(.*? )", ""));
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> t) {
        this.teams = t;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Team t);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class TeamHolder extends RecyclerView.ViewHolder {
        private TextView teamNaam;

        public TeamHolder(@NonNull View itemView) {
            super(itemView);
            teamNaam = itemView.findViewById(R.id.opdrachtaanbod_team_naam);
            Log.d("asdf", String.valueOf(teamNaam));
            Log.d("asdf", String.valueOf(teamNaam));
            Log.d("asdf", String.valueOf(teamNaam));
            Log.d("asdf", String.valueOf(teamNaam));
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                if (listener != null && i != RecyclerView.NO_POSITION) {
                    listener.onItemClick(teams.get(i));
                }
            });
        }

        public TextView getTeamNaam() {
            return teamNaam;
        }
    }

}



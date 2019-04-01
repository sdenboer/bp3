package com.example.bp3.views.adapters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.service.models.Team;
import com.example.bp3.views.adapters.viewHolders.TeamHolder;
import com.example.bp3.views.adapters.viewHolders.TeamMemberHolder;
import com.example.bp3.libraries.ExpandableRecyclerViewAdapter;
import com.example.bp3.libraries.models.ExpandableGroup;
import com.example.bp3.libraries.viewholders.ChildViewHolder;
import com.example.bp3.libraries.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends ExpandableRecyclerViewAdapter<TeamHolder, TeamMemberHolder> {
    private TeamAdapter.OnItemClickListener listener;
    private List<Team> teams = new ArrayList<>();


    public TeamAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
        Log.d("Hello", String.valueOf(groups.size()));
    }

    @Override
    public TeamHolder onCreateGroupViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_teamnaam, viewGroup, false);
        return new TeamHolder(view);
    }

    @Override
    public TeamMemberHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_teamnaam, viewGroup, false);
        return new TeamMemberHolder(view);
    }

    @Override
    public void onBindChildViewHolder(TeamMemberHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Student s = (Student) group.getItems().get(childIndex);
        holder.onBind(s);
    }

    @Override
    public void onBindGroupViewHolder(TeamHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTeamNaam(group);
    }

    public void setTeams(List<Team> t) {
        this.teams = t;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }




        public interface OnItemClickListener {
            void onItemClick(Team opdrachtAanbod);
        }

            public void setOnItemClickListener(TeamAdapter.OnItemClickListener listener) {
                this.listener = listener;
            }


}


//    private List<Team> team = new ArrayList<>();
//    private List<Student> teammember = new ArrayList<>();
//    private TeamAdapter.OnItemClickListener listener;
//
//    @NonNull
//    @Override
//    public TeamAdapter.TeamHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.card_opdrachtaanbod_teamnaam, viewGroup, false);
//        return new TeamAdapter.TeamHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TeamAdapter.TeamHolder opdrachtHolder, int i) {
//        Team team = this.team.get(i);
////        opdrachtHolder.naam.setText(team.getTeamMembers()..);
////        opdrachtHolder.bedrijf.setText(oa.getBedrijf().getNaam());
////        opdrachtHolder.opdrachtnaam.setText(oa.getOpdracht().getOpdrachtNaam());
//    }
//
//    @Override
//    public int getItemCount() {
//        return team.size();
//    }
//
//    public void setTeam(List<Team> team) {
//        this.team = team;
//        notifyDataSetChanged();
//    }
//
//class TeamHolder extends RecyclerView.ViewHolder {
//    private TextView teamNaam;
//    private TextView memberNaam;
//    private TextView opdrachtnaam;
//
//    public TeamHolder(@NonNull View itemView) {
//        super(itemView);
//        teamNaam = itemView.findViewById(R.id.opdrachtaanbod_team_naam);
////            bedrijf = itemView.findViewById(R.id.opdracht_by_lesvak_bedrijf);
////            opdrachtnaam = itemView.findViewById(R.id.opdracht_by_lesvak_opdrachtnaam);
//        itemView.setOnClickListener(v -> {
//            int i = getAdapterPosition();
//            if (listener != null && i != RecyclerView.NO_POSITION) {
//                listener.onItemClick(team.get(i));
//            }
//        });
//    }
//}
//
//public interface OnItemClickListener {
//    void onItemClick(Team opdrachtAanbod);
//}
//
//    public void setOnItemClickListener(TeamAdapter.OnItemClickListener listener) {
//        this.listener = listener;
//    }
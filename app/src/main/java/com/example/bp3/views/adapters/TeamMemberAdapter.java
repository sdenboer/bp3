package com.example.bp3.views.adapters;

import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberHolder> {

    private List<Student> member = new ArrayList<>();
    private int maxStudents = 0;
    private int minStudents = 0;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TeamMemberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_membernaam, viewGroup, false);
        return new TeamMemberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamMemberHolder teamHolder, int i) {
        try {
            Student s = this.member.get(i);
            if (s.getEmail().equals("BWarringa@student.kw1c.nl")) {
                teamHolder.getMemberNaam().setText("verwijder jezelf");
            } else  {
                teamHolder.getMemberNaam().setText(s.getNaam());
            }
        } catch (IndexOutOfBoundsException e) {
            if (i < minStudents) {
                teamHolder.getMemberNaam().setBackgroundColor(Color.RED);
            } else {
                teamHolder.getMemberNaam().setBackgroundColor(Color.GREEN);
            }
            teamHolder.getMemberNaam().setText("ADD YOURSELF");
        }

    }

    @Override
    public int getItemCount() {
        return maxStudents;
    }

    public void setTeams(List<Student> s, int maxStudents, int minStudents) {
        this.member = s;
        this.maxStudents = maxStudents;
        this.minStudents = minStudents;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Student s);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class TeamMemberHolder extends RecyclerView.ViewHolder {
        private TextView memberNaam;

        public TeamMemberHolder(@NonNull View itemView) {
            super(itemView);
            memberNaam = itemView.findViewById(R.id.opdrachtaanbod_member_naam);
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                if (listener != null && i != RecyclerView.NO_POSITION) {
                    try {
                        listener.onItemClick(member.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        listener.onItemClick(new Student());
                    }

                }
            });
        }

        public TextView getMemberNaam() {
            return memberNaam;
        }
    }

}

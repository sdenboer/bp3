package com.example.bp3.views.adapters;

import android.app.Application;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.viewmodels.TeamViewModel;

import java.util.ArrayList;
import java.util.List;
/**
 * @author sven
 */
public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberHolder> {

    private List<Student> member = new ArrayList<>();
    private int maxStudents;
    private int minStudents;
    private Team team;
    private DialogFragment dialogFragment;
    private TeamViewModel tvm;
    private OnItemClickListener listener;
    private Student student;
    private Bedrijf bedrijf;

    public TeamMemberAdapter(DialogFragment dialogFragment, Application application, OpdrachtAanbod oa) {
        if (Account.currentUser instanceof Student) {
            this.student = (Student) Account.currentUser;
        } else if (Account.currentUser instanceof Bedrijf) {
            this.bedrijf = (Bedrijf) Account.currentUser;
        }
        this.dialogFragment = dialogFragment;
        this.tvm = new TeamViewModel(application);
        this.maxStudents = oa.getOpdracht().getAantStudMax();
        this.minStudents = oa.getOpdracht().getAantStudMin();
    }

    @NonNull
    @Override
    public TeamMemberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_membernaam, viewGroup, false);
        return new TeamMemberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamMemberHolder teamHolder, int i) {
        if (Account.currentUser instanceof Student) {
            try {
                Student s = this.member.get(i);
                teamHolder.delete.setOnClickListener(e -> {
                    this.team.removeTeamMember(s);
                    if (this.team.getTeamMembers().size() == 0) {
                        tvm.delete(team);
                    } else {
                        tvm.update(team);
                    }
                    dialogFragment.dismiss();
                });
                if (s.getEmail().equals(student.getEmail())) {
                    teamHolder.delete.setVisibility(View.VISIBLE);
                }
                teamHolder.getMemberNaam().setText(s.getNaam());
            } catch (IndexOutOfBoundsException e) {
                if (i < minStudents) {
                    teamHolder.getMemberNaam().setBackgroundColor(Color.RED);
                } else {
                    teamHolder.getMemberNaam().setBackgroundColor(Color.GREEN);
                }
                teamHolder.getMemberNaam().setText("Schrijf jezelf in");
            }
        } else if (Account.currentUser instanceof Bedrijf) {
            try {
                Student s = this.member.get(i);
                teamHolder.getMemberNaam().setText(s.getNaam());
            } catch (IndexOutOfBoundsException e) {
                if (i < minStudents) {
                    teamHolder.getMemberNaam().setBackgroundColor(Color.RED);
                } else {
                    teamHolder.getMemberNaam().setBackgroundColor(Color.GREEN);
                }
                teamHolder.getMemberNaam().setText("Deze plekken zijn nog leeg");
            }
        }

    }

    @Override
    public int getItemCount() {
        return maxStudents;
    }

    public void setTeams(Team t) {
        this.member = t.getTeamMembers();
        this.team = t;
    }

    public interface OnItemClickListener {
        void onItemClick(Student s);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class TeamMemberHolder extends RecyclerView.ViewHolder {
        private TextView memberNaam;
        private ImageButton delete;

        public TeamMemberHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.opdrachtaanbod_team_delete);
            memberNaam = itemView.findViewById(R.id.opdrachtaanbod_member_naam);
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                if (listener != null && i != RecyclerView.NO_POSITION) {
                    try {
                        listener.onItemClick(member.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        if (Account.currentUser instanceof Student) {
                            listener.onItemClick(student);
                        }
                    }

                }
            });
        }

        public TextView getMemberNaam() {
            return memberNaam;
        }
    }

}

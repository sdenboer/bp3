package com.example.bp3.views.adapters.viewHolders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.libraries.models.ExpandableGroup;
import com.example.bp3.libraries.viewholders.GroupViewHolder;


public class TeamHolder extends GroupViewHolder {
    private TextView teamNaam;

    public TeamHolder(@NonNull View itemView) {
        super(itemView);
        teamNaam = itemView.findViewById(R.id.opdrachtaanbod_team_naam);
    }

    public void setTeamNaam(ExpandableGroup group) {
        teamNaam.setText(group.getTitle());
    }
}
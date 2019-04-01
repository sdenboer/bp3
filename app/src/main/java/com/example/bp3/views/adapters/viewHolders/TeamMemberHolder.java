package com.example.bp3.views.adapters.viewHolders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Student;
import  com.example.bp3.libraries.viewholders.ChildViewHolder;

public class TeamMemberHolder extends ChildViewHolder {
    private TextView memberNaam;

    public TeamMemberHolder(@NonNull View itemView) {
        super(itemView);
        memberNaam = itemView.findViewById(R.id.opdrachtaanbod_member_naam);
    }

    public void onBind(Student student) {
        memberNaam.setText(student.getNaam());
    }
}
package com.example.bp3.views.fragments.Challenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

public class ChallengeView extends ViewFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_challenge, container, false);
    }

    @Override
    public int title() {
        return R.string.projecten_challenges;
    }
}

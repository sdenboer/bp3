package com.example.bp3.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.views.fragments.ViewFragment;

public class Challenge extends ViewFragment {
    @Override
    protected int layoutFragment() {
        return R.layout.fragment_challenge;
    }

    @Override
    protected int title() {
        return R.string.projecten_mystuff;
    }
}

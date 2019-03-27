package com.example.bp3.views.menus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

public class NavDrawer extends ViewFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_nav_drawer, container, false);
    }

    @Override
    public int title() {
        return 0;
    }
}

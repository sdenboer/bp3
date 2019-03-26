package com.example.bp3.views.menus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.views.fragments.ViewFragment;

public class NavDrawer extends ViewFragment {

    @Override
    protected int layoutFragment() {
        return R.layout.activity_nav_drawer;
    }

    @Override
    protected int title() {
        return 0;
    }
}

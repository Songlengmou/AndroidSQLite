package com.example.syp.addusersfour.controllers;

import android.support.v4.app.FragmentManager;

import com.example.syp.addusersfour.R;
import com.example.syp.addusersfour.activities.MainActivity;
import com.example.syp.addusersfour.databinding.ActivityMainBinding;
import com.example.syp.addusersfour.fragments.AbstractFragment;
import com.example.syp.addusersfour.fragments.UserGroupListFragment;

/**
 * Created by syp on 17-6-9.
 */

public class MainController {
    private ActivityMainBinding binding;
    private MainActivity mainActivity;
    private FragmentManager supportFragmentManager;
    private UserGroupListFragment userGroupListFragment;

    public MainController(ActivityMainBinding binding, MainActivity mainActivity) {
        this.binding = binding;
        this.mainActivity = mainActivity;
        this.supportFragmentManager = mainActivity.getSupportFragmentManager();

        addListeners();

        userGroupListFragment = new UserGroupListFragment();
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, userGroupListFragment)
                .commit();
    }

    private void addListeners() {
        supportFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (supportFragmentManager.getBackStackEntryCount() > 0) {
                    FragmentManager.BackStackEntry topSE = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.getBackStackEntryCount() - 1);
                    if (topSE instanceof AbstractFragment) {
                        ((AbstractFragment) topSE).onBackToFragment();
                    }
                } else {
                    userGroupListFragment.onBackToFragment();
                }
            }
        });
    }
}

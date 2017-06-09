package com.example.syp.addusersfour.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syp.addusersfour.controllers.UserGroupListFragmentController;
import com.example.syp.addusersfour.databinding.FragmentUserGroupListBinding;

/**
 * Created by syp on 17-6-9.
 */

public class UserGroupListFragment extends AbstractFragment {
    private FragmentUserGroupListBinding binding;
    private UserGroupListFragmentController controller;

    public UserGroupListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onDestroy() {
        controller.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserGroupListBinding.inflate(inflater);
        controller = new UserGroupListFragmentController(binding, this);
        binding.setController(controller);
        setBinding(binding);
        return binding.getRoot();
    }

    @Override
    public void onBackToFragment() {
        super.onBackToFragment();

        controller.onBackToFragment();
    }
}

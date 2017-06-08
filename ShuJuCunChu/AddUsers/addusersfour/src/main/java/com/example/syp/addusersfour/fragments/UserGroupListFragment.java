package com.example.syp.addusersfour.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syp.addusersfour.controller.UserGroupListFragmentController;
import com.example.syp.addusersfour.databinding.FragmentUserGroupBinding;

/**
 * Created by syp on 17-6-8.
 * <p>
 * 呈现用户组
 */

public class UserGroupListFragment extends AbstractFragment {
    private FragmentUserGroupBinding binding;
    private UserGroupListFragmentController controller;

    public UserGroupListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        controller.onDestroy();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserGroupBinding.inflate(inflater);
        controller = new UserGroupListFragmentController(binding, this);
        binding.setController(controller);
        setBinding(binding);
        return binding.getRoot();
    }

    //监听返回键
    @Override
    public void onBackToFragment() {
        super.onBackToFragment();

        controller.onBackToFragment();
    }
}

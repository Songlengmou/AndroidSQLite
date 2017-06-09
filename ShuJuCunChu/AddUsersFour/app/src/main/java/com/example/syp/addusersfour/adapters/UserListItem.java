package com.example.syp.addusersfour.adapters;

import android.view.View;
import android.widget.TextView;

import com.example.syp.addusersfour.R;

/**
 * Created by syp on 17-6-9.
 */

public class UserListItem {
    private TextView tvName, tvAge;

    public UserListItem(View itemView) {
        tvAge = (TextView) itemView.findViewById(R.id.tvAge);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }

    public TextView getTvAge() {
        return tvAge;
    }

    public TextView getTvName() {
        return tvName;
    }

}

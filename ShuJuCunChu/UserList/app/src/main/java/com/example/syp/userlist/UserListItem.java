package com.example.syp.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by syp on 17-6-6.
 */

public final class UserListItem extends RecyclerView.ViewHolder {

    private TextView tvName, tvAge;

    public UserListItem(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvAge = (TextView) itemView.findViewById(R.id.tvAge);
    }

    public TextView getTvAge() {
        return tvAge;
    }

    public TextView getTvName() {
        return tvName;
    }
}

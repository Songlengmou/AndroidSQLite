package com.example.syp.addusersfour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.syp.addusersfour.R;
import com.example.syp.addusersfour.db.DbCursor;

/**
 * Created by syp on 17-6-9.
 */

public class UserListAdapter extends BaseAdapter {
    private final Context context;
    private DbCursor cursor;

    public UserListAdapter(DbCursor cursor, Context context) {
        setCursor(cursor);

        this.context = context;
    }

    public void setCursor(DbCursor cursor) {
        if (this.cursor != null) {
            this.cursor.close();
        }

        this.cursor = cursor;

        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    @Override
    public DbCursor getItem(int position) {
        this.cursor.moveToPosition(position);
        return this.cursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_view, null);
            convertView.setTag(new UserListItem(convertView));
        }

        DbCursor cursor = getItem(position);
        UserListItem itemView = (UserListItem) convertView.getTag();
        itemView.getTvName().setText(cursor.getName());
        itemView.getTvAge().setText(String.valueOf(cursor.getAge()));
        return convertView;
    }
}

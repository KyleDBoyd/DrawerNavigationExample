package com.example.kyleb.newsapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kyleb.newsapp.R;
import com.example.kyleb.newsapp.models.DrawerItem;

import java.util.ArrayList;

/**
 * Created by kyleb on 2015-09-20.
 */
public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.ViewHolder> {
    /**
     * Drawer items list
     */
    private ArrayList<DrawerItem> drawerItems = new ArrayList<>();

    /**
     * Context
     */
    private Context context;

    /**
     * Drawer item viewholder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView subtitle;
        public ViewHolder(View v) {
            super(v);
            icon = (ImageView) v.findViewById(R.id.item_drawer_icon);
            title = (TextView) v.findViewById(R.id.item_drawer_title);
        }
    }

    /**
     * Constructor. Init drawer items list.
     * @param drawerItems
     * @param context
     */
    DrawerListAdapter(ArrayList<DrawerItem> drawerItems, Context context) {
        this.drawerItems = drawerItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drawer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        DrawerItem drawerItem = drawerItems.get(i);
        if (drawerItem != null) {
            // Icon
            if (drawerItem.getIcon() != null) {
                Drawable drawable = ContextCompat.getDrawable(context, drawerItem.getIcon());
                drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                viewHolder.icon.setImageDrawable(drawable);
            }

            // Title
            if (drawerItem.getTitle() != null) {
                viewHolder.title.setText(drawerItem.getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return drawerItems.size();
    }
}

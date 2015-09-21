package com.example.kyleb.newsapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kyleb.newsapp.R;
import com.example.kyleb.newsapp.models.DrawerItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    /**
     * Drawer list
     */
    @ViewById(R.id.drawer_list)
    RecyclerView drawerList;

    /**
     * Drawer adapter
     */
    DrawerListAdapter drawerListAdapter;

    /**
     * After view injection
     */
    @AfterViews
    void afterViewInjection() {
        if (drawerList != null) initDrawerList(drawerList);
    }

    /**
     * Build and set adapter for drawer list
     * @param recyclerView
     */
    private void initDrawerList(RecyclerView recyclerView) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Create/set adapter
        drawerListAdapter = new DrawerListAdapter(
                initDrawerNavigationOptions(),
                getApplicationContext()
        );
        recyclerView.setAdapter(drawerListAdapter);
    }

    /**
     * Initialize drawer navigation otions
     * @return
     */
    private ArrayList<DrawerItem> initDrawerNavigationOptions() {
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(getString(R.string.title_drawer_home), R.drawable.ic_home));
        drawerItems.add(new DrawerItem(getString(R.string.title_drawer_favorites), R.drawable.ic_favorite));
        drawerItems.add(new DrawerItem(getString(R.string.title_drawer_settings), R.drawable.ic_settings));
        return drawerItems;
    }
}

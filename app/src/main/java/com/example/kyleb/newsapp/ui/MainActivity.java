package com.example.kyleb.newsapp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.kyleb.newsapp.R;
import com.example.kyleb.newsapp.listeners.RecyclerViewClickListener;
import com.example.kyleb.newsapp.models.DrawerItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    /**
     * Fragment positions
     */
    private static final int HOME_POSITION = 0;
    private static final int FAVORITE_POSITION = 1;
    private static final int SETTINGS_POSITION = 2;

    /**
     * Fragment titles
     */
    private static final HashMap<Integer, String> FRAGMENT_TITLES;
    static {
        HashMap<Integer, String> fragmentTitles = new HashMap<>();
        fragmentTitles.put(HOME_POSITION, "Home");
        fragmentTitles.put(FAVORITE_POSITION, "Favorites");
        fragmentTitles.put(SETTINGS_POSITION, "Settings");
        FRAGMENT_TITLES = fragmentTitles;
    }

    /**
     * Drawer layout
    */
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

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
     * Drawer toggle
     */
    private ActionBarDrawerToggle drawerToggle;

    /**
     * After view injection
     */
    @AfterViews
    void afterViewInjection() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        if (drawerLayout != null) initDrawerListener(drawerLayout);
        if (drawerList != null) {
            initDrawerList(drawerList);
        }

        // Start home fragment by default
        startFragment(HOME_POSITION);

        // Sync drawer toggle state
        if (drawerToggle != null) drawerToggle.syncState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (drawerToggle != null && drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * Start fragment based on position
     * @param position
     */
    private void startFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case HOME_POSITION:
                fragment = new HomeFragment();
                break;
            case FAVORITE_POSITION:
                fragment = new FavoritesFragment();
                break;
            case SETTINGS_POSITION:
                fragment = new SettingsFragment();
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        // Highlight the selected item, update the title, and close the drawer
        setTitle(FRAGMENT_TITLES.get(position));
        drawerLayout.closeDrawer(drawerList);

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
                getApplicationContext(),
                this
        );

        // Init drawer toggle
        initDrawerToggle(drawerLayout);

        // Set adapter
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

    /**
     * Init drawer layout open/close listener
     * @param drawerLayout
     */
    private void initDrawerListener(DrawerLayout drawerLayout) {
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    /**
     * Start fragment on drawer item click
     * @param v
     * @param position
     */
    @Override
    public void recyclerViewListClicked(View v, int position) {
        startFragment(position);
    }

    /**
     * Initialize drawer toggle and set as drawer listener
     * @param drawerLayout
     */
    private void initDrawerToggle(DrawerLayout drawerLayout) {
        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.toggle_drawer_open,
                R.string.toggle_drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }
}

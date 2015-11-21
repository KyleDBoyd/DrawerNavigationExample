package com.example.kyleb.newsapp.ui;

import android.support.v4.app.Fragment;

import com.example.kyleb.newsapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * @author kyleb
 * @version 1.0
 * @date 2015-09-20
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {
    @AfterViews
    void afterViewInjection() {

    }
}

package com.example.kyleb.newsapp.models;

/**
 * Created by kyleb on 2015-09-20.
 */
public class DrawerItem {
    /**
     * Drawer item title
     */
    private String title;

    /**
     * Drawer item subtitle
     */
    private String subtitle;

    /**
     * Drawer item icon
     */
    private Integer icon;

    /**
     * Constructor. Init class variables
     * @param title
     * @param icon
     */
    public DrawerItem(String title, Integer icon) {
        this.title = title;
        this.icon = icon;
    }

    /**
     * Returns the drawer item title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the drawer item title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the draw item icon resource ID
     * @return
     */
    public Integer getIcon() {
        return icon;
    }

    /**
     * Sets the drawer item icon resource ID
     * @param icon
     */
    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}

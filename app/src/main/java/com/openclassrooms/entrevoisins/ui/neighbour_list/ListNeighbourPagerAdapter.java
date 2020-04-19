package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position / integer : position de l'item sélectionné
     * @return : objet : instance du fragment sélectionné
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) return NeighbourListFragment.newInstance(false);
        else return  NeighbourListFragment.newInstance(true);
    }

    /**
     * get the number of pages
     * @return : integer : retourne le nombre de pages du viewpage
     */
    @Override
    public int getCount() {
        return 2;
    }
}
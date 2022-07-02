package com.trademax.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.trademax.Fragments.Search_Painter_PlastersCategorie.Search_Painter_PlastersCategorie_Fragment;
import com.trademax.Fragments.Search_BuildersCategorie.Search_BuildersCategorie_Fragment;

public class SearchTabAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public SearchTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Search_Painter_PlastersCategorie_Fragment search_painter_fragment = new Search_Painter_PlastersCategorie_Fragment();
                return search_painter_fragment;
            case 1:
                Search_BuildersCategorie_Fragment search_plaster_fragment = new Search_BuildersCategorie_Fragment();
                return search_plaster_fragment;
            default:
                Search_Painter_PlastersCategorie_Fragment search_painter_fragment1 = new Search_Painter_PlastersCategorie_Fragment();
                return search_painter_fragment1;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}

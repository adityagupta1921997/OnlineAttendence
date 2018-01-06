package com.apkglobal.onlineattendence.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.apkglobal.onlineattendence.Fragment.AddClass;
import com.apkglobal.onlineattendence.Fragment.AddStudent;
import com.apkglobal.onlineattendence.Fragment.AddTeacher;
import com.apkglobal.onlineattendence.Fragment.LogOut;
import com.apkglobal.onlineattendence.Fragment.ViewDetails;


/**
 * Created by abhisharma on 07-08-2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }
    @Override
    public Fragment getItem(int position) {
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            AddStudent tab1 = new AddStudent();
            return tab1;
        }

        if(position == 1) // if the position is 0 we are returning the First tab
        {
            AddTeacher tab2 = new AddTeacher();
            return tab2;
        }

        if(position == 2) // if the position is 0 we are returning the First tab
        {
            AddClass tab3 = new AddClass();
            return tab3;
        }

        if(position == 3) // if the position is 0 we are returning the First tab
        {
            ViewDetails tab4 = new ViewDetails();
            return tab4;
        }


        else {
            LogOut tab5 = new LogOut();
            return tab5;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}

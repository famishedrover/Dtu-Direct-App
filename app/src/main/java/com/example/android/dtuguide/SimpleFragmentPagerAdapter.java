package com.example.android.dtuguide;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter
               {
    private Context mContext;
    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CCouncilFragment();
        } else if (position == 1){
            return new NoticesFragment();
        } else if (position==2) {
            return new TimeTableFragment();
        } else if(position==3){
            return new SyllabusFragment();
        }
        else if(position==4){
            return new NotesFragment();
        }
        else{
            return new ResultsFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.Council);
        } else if (position == 1) {
            return mContext.getString(R.string.Notices);
        } else if (position == 2) {
            return mContext.getString(R.string.Time_Table);
        } else if(position==3){
            return mContext.getString(R.string.Syllabus);
        }
        else if(position==4){
            return mContext.getString(R.string.Notes);
        }
        else{
            return mContext.getString(R.string.Results);
        }
    }
}

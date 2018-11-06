package com.banana.y17_2.promo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ViewPagerAdapter extends PagerAdapter {//Для обычного viewPager - extends FragmentStatePagerAdapter


    /**
     * Для PagerAdapter
     */


    private Context mContext;

    public ViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_adapter_layout, collection, false);
        collection.addView(layout);
        ImageView imageView = layout.findViewById(R.id.img);

        final Obj obj = Database.Objs[position];
        imageView.setImageResource(obj.resId);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}


/**
 * Для FragmentStatePagerAdapter
 */


/*
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {


        switch (i){
            case 0:
                return new Fragment2();
            case 1:
                return new Dom98111Fragment();
            case 2:
                return new KuelyeFragment();
            case 3:
                return new NikitaFragment();
            case 4:
                return new SobolFragment();
            default:
                //Toast.makeText(context, "Oops, Something went wrong", Toast.LENGTH_SHORT).show();
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    //For PagerTabStrip
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }

*/

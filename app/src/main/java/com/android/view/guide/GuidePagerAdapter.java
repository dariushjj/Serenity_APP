package com.android.view.guide;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class GuidePagerAdapter extends PagerAdapter {

    private List<View> viewList;

    public GuidePagerAdapter() {
    }

    public GuidePagerAdapter(List<View> list){
        super();
        this.viewList = list;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(this.viewList.get(position));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return this.viewList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(this.viewList.get(position));
        return this.viewList.get(position);
    }
}

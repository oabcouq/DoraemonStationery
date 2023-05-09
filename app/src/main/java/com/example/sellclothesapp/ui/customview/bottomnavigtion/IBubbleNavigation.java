package com.example.sellclothesapp.ui.customview.bottomnavigtion;

import android.graphics.Typeface;

import com.example.sellclothesapp.ui.customview.bottomnavigtion.listener.BubbleNavigationChangeListener;

public interface IBubbleNavigation {
    void setNavigationChangeListener(BubbleNavigationChangeListener navigationChangeListener);

    void setTypeface(Typeface typeface);

    int getCurrentActiveItemPosition();

    void setCurrentActiveItem(int position);

    void setBadgeValue(int position, String value);
}

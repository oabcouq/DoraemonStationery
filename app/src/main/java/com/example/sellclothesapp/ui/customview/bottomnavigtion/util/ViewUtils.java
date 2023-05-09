package com.example.sellclothesapp.ui.customview.bottomnavigtion.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;

import com.example.sellclothesapp.R;

import org.jetbrains.annotations.Nullable;

public class ViewUtils {
    public static int getThemeAccentColor(final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.color.colorAccent, value, true);
        return value.data;
    }

    public static void updateDrawableColor(@Nullable Drawable drawable, int color) {
        if (drawable == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) drawable.setTint(color);
        else drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }
}

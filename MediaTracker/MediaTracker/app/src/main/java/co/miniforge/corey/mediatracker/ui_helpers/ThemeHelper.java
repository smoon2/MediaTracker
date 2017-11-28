package co.miniforge.corey.mediatracker.ui_helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import co.miniforge.corey.mediatracker.R;


/**
 * Created by SonieMoon on 11/27/2017.
 */

public class ThemeHelper {

    private SharedPreferences sharedPref;
    private int themeLightBg;
    private int themeLightText;
    private int themeDarkText;
    private int themeDarkContainer;
    private int themeLightContainer;
    private int themeDarkBg;

    public ThemeHelper(Context context) {
        try {
            themeLightBg = ContextCompat.getColor(context, R.color.themeLightBg);


        } catch (RuntimeException e) {

        }

        sharedPref = context.getSharedPreferences("theme", 0);


    }

    public boolean darkThemeEnabled() {
        return sharedPref.getBoolean("darkTheme", false);
    }

    public void enableDarkTheme(boolean enabled) {
        sharedPref.edit().putBoolean("darkTheme", enabled).apply();

    }

    public void themeTextView(TextView... textviews) {
        boolean dark = darkThemeEnabled();
        for(TextView textView : textviews) {
            textView.setTextColor(dark ? themeDarkText : themeLightText);
        }
    }

    public void themeImageContainer(View... containers) {
        boolean dark = darkThemeEnabled();
        for (View view : containers) {
            view.setBackgroundColor(dark ? themeDarkContainer : themeLightContainer);
        }
    }

    public void themeBackground(View rootview) {
        boolean dark = darkThemeEnabled();
        rootview.setBackgroundColor(dark ? themeDarkBg : themeLightBg);

    }



}

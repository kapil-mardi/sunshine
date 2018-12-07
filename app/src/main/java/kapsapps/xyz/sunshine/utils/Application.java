package kapsapps.xyz.sunshine.utils;

import java.util.Calendar;
import java.util.Date;

import kapsapps.xyz.sunshine.R;
import timber.log.Timber;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour > 18 || hour < 6) {
            getApplicationContext().setTheme(R.style.night_theme);
        }
        
    }
}

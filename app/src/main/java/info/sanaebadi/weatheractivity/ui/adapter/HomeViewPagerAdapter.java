package info.sanaebadi.weatheractivity.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import info.sanaebadi.weatheractivity.ui.fragment.home.ForecastWeatherFragment;
import info.sanaebadi.weatheractivity.ui.fragment.home.TodayWeatherFragment;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {


    private Context myContext;
    int totalTabs;

    public HomeViewPagerAdapter(@NonNull FragmentManager fm, int totalTabs, Context context) {
        super(fm);
        this.totalTabs = totalTabs;
        this.myContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TodayWeatherFragment todayWeatherFragment = new TodayWeatherFragment();
                return todayWeatherFragment;
            case 1:
                ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment();
                return forecastWeatherFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}



package kapsapps.xyz.sunshine.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kapsapps.xyz.sunshine.R;
import kapsapps.xyz.sunshine.datasource.WeatherViewModal;
import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.ui.fragments.CityFragment;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTitle.setText(R.string.app_name);
        setSupportActionBar(mToolbar);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        CityFragment fragment1 = CityFragment.newInstance();

        mAdapter.addFragment(fragment1);

        mAdapter.notifyDataSetChanged();


    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        List<CityFragment> fragments;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(CityFragment fragment){
            this.fragments.add(fragment);
        }
    }
}

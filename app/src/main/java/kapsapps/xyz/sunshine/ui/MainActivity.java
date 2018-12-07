package kapsapps.xyz.sunshine.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kapsapps.xyz.sunshine.R;
import kapsapps.xyz.sunshine.ui.fragments.CityFragment;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.parent_view)
    RelativeLayout mParentView;

    ViewPagerAdapter mAdapter;

    BroadcastReceiver mTimeTickReceiver;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mTitle.setText(R.string.app_name);
        setSupportActionBar(mToolbar);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        CityFragment fragment1 = CityFragment.newInstance("Pune");

        mAdapter.addFragment(fragment1);

        mAdapter.notifyDataSetChanged();

        checkTheme();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mTimeTickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Timber.tag("Time service").d("got time tick");
                checkTheme();
            }
        };

        registerReceiver(mTimeTickReceiver,new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mTimeTickReceiver);
    }

    private void checkTheme() {

        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour >= 18 || hour < 6) {
            mParentView.setBackground(getResources().getDrawable(R.drawable.night_bg));
        }else{
            mParentView.setBackground(getResources().getDrawable(R.drawable.morning_bg));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.add_city){
            showAddCityDialog();
        }
        return true;
    }

    private void showAddCityDialog() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.new_city_addition);

        EditText newCityNameEdt = dialog.findViewById(R.id.city_name);
        Button addCity = dialog.findViewById(R.id.add_city_btn);

        dialog.show();

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCityName = newCityNameEdt.getText().toString();

                if(!newCityName.isEmpty()){
                    CityFragment fragment = CityFragment.newInstance(newCityName);
                    mAdapter.addFragment(fragment);
                    dialog.dismiss();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
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

package kapsapps.xyz.sunshine.ui.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import kapsapps.xyz.sunshine.R;
import kapsapps.xyz.sunshine.datasource.ForecastViewModal;
import kapsapps.xyz.sunshine.datasource.WeatherViewModal;
import kapsapps.xyz.sunshine.model.ForecastModal;
import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.ui.views.WeatherAdapter;
import timber.log.Timber;

public class CityFragment extends Fragment {

    @BindView(R.id.city_name)
    TextView mCityName;

    @BindView(R.id.weather_list_view)
    RecyclerView mWeatherList;

    ForecastViewModal mForecastViewModal;

    WeatherAdapter mWeatherAdapter;

    public CityFragment() {
        // Required empty public constructor
    }


    public static CityFragment newInstance() {
        CityFragment fragment = new CityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this,view);

        mWeatherList.setLayoutManager(new LinearLayoutManager(getContext()));
        mWeatherList.addItemDecoration(new DividerItemDecoration(mWeatherList.getContext(), DividerItemDecoration.VERTICAL));
        mWeatherAdapter = new WeatherAdapter(getLayoutInflater(),getContext());
        mWeatherList.setAdapter(mWeatherAdapter);

        mForecastViewModal = ViewModelProviders.of(this).get(ForecastViewModal.class);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mForecastViewModal.getWeatherData().observe(this, new Observer<ForecastModal>() {
            @Override
            public void onChanged(@Nullable ForecastModal forecastModal) {
                if(forecastModal != null) {
                    Timber.tag("Forcast data").d(forecastModal.toString());
                    mCityName.setText(forecastModal.getCity().getName());
                    mWeatherAdapter.setData(forecastModal.getWeatherData());
                }
            }
        });
    }
}

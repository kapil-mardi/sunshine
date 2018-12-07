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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @BindView(R.id.current_temp)
    TextView mCurrentTemp;

    @BindView(R.id.sunrise_time)
    TextView mSunRiseTime;

    @BindView(R.id.sunset_time)
    TextView mSunsetTime;

    @BindView(R.id.time)
    TextView mCurrentTime;

    @BindView(R.id.description)
    TextView mDescription;

    ForecastViewModal mForecastViewModal;

    WeatherViewModal mCurrentWeatherViewModal;

    WeatherAdapter mWeatherAdapter;

    String mInputCityName;

    public CityFragment() {
        // Required empty public constructor
    }


    public static CityFragment newInstance(String newCityName) {
        CityFragment fragment = new CityFragment();
        fragment.mInputCityName = newCityName;
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
        ButterKnife.bind(this, view);

        mWeatherList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mWeatherList.addItemDecoration(new DividerItemDecoration(mWeatherList.getContext(), DividerItemDecoration.HORIZONTAL));
        mWeatherAdapter = new WeatherAdapter(getLayoutInflater(), getContext());
        mWeatherList.setAdapter(mWeatherAdapter);

        mForecastViewModal = ViewModelProviders.of(this).get(ForecastViewModal.class);
        mCurrentWeatherViewModal = ViewModelProviders.of(this).get(WeatherViewModal.class);

        mForecastViewModal.setCityName(mInputCityName);
        mCurrentWeatherViewModal.setCityName(mInputCityName);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mForecastViewModal.getForecastData().observe(this, new Observer<ForecastModal>() {
            @Override
            public void onChanged(@Nullable ForecastModal forecastModal) {
                if (forecastModal != null) {
                    mWeatherAdapter.setData(forecastModal.getWeatherData());
                }
            }
        });

        mCurrentWeatherViewModal.getWeatherData().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(@Nullable Weather weather) {
                if (weather != null) {
                    Timber.tag("Weather data").d(weather.toString());

                    long time = weather.getDateTime();
                    String cityName = weather.getName();
                    long sunRiseTime = weather.getSys().getSunrise();
                    long sunSetTime = weather.getSys().getSunset();
                    double temp = weather.getMain().getTemp();
                    String descritption = weather.getWeatherData().get(0).getDescription();

                    Date dateToShow = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE\nhh:mm a");

                    dateToShow.setTime(time * 1000);
                    mCurrentTime.setText(dateFormat.format(dateToShow));

                    dateFormat = new SimpleDateFormat("hh:mm a");
                    dateToShow.setTime(sunRiseTime * 1000);
                    mSunRiseTime.setText(dateFormat.format(dateToShow));

                    dateToShow.setTime(sunSetTime * 1000);
                    mSunsetTime.setText(dateFormat.format(dateToShow));

                    int tempC = (int) (temp - 273.15);

                    mCurrentTemp.setText(tempC + "\u2103");

                    mCityName.setText(cityName);

                    mDescription.setText(descritption);

                }
            }
        });
    }
}

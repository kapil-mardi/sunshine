package kapsapps.xyz.sunshine.ui;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import kapsapps.xyz.sunshine.R;
import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.service.ServiceAPI;
import kapsapps.xyz.sunshine.service.ServiceProvider;
import kapsapps.xyz.sunshine.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();


        ServiceAPI serviceAPI = ServiceProvider.getInstance().getServiceAPI();
        Call<Weather> weatherCall = serviceAPI.getWeatherDataForCity("pune",Constants.API_KEY);
        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Timber.tag("Weather data").d(response.body().toString());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Timber.tag("Error").d(t.getLocalizedMessage());
            }
        });
    }
}

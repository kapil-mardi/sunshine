package kapsapps.xyz.sunshine.ui;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import kapsapps.xyz.sunshine.R;
import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.service.ServiceAPI;
import kapsapps.xyz.sunshine.service.ServiceProvider;
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

        Timber.tag("device id ").d(Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID));

        ServiceAPI serviceAPI = ServiceProvider.getInstance().getServiceAPI();
        Call<List<Weather>> data = serviceAPI.getWeatherList("21c469cedc1c1e96df08d86fc2355bbe");
        data.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                Timber.tag("data").d(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Timber.tag("data").d(t.getMessage());
            }
        });
    }
}

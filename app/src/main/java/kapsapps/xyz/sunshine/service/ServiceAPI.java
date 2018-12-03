package kapsapps.xyz.sunshine.service;

import java.util.List;

import kapsapps.xyz.sunshine.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {

    @GET("data/2.5/weather")
    Call<Weather> getWeatherDataForCity(@Query("q") String cityName, @Query("APPID") String apiKey);
}

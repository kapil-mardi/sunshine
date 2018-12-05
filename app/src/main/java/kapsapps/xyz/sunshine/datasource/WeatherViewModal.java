package kapsapps.xyz.sunshine.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.service.ServiceAPI;
import kapsapps.xyz.sunshine.service.ServiceProvider;
import kapsapps.xyz.sunshine.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModal extends ViewModel {

    private MutableLiveData<Weather> mMutableLiveData;
    private ServiceAPI serviceAPI;
    private String mCityName = "Pune";

    public void setCityName(String name){
        this.mCityName = name;
        makeServerCall();
    }

    public WeatherViewModal(){
        mMutableLiveData = new MutableLiveData<>();
        mMutableLiveData.setValue(null);

        serviceAPI = ServiceProvider.getInstance().getServiceAPI();
        makeServerCall();
    }

    private void makeServerCall() {

        Call<Weather> data = serviceAPI.getWeatherDataForCity(mCityName,Constants.API_KEY);
        data.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                mMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                mMutableLiveData.setValue(null);
            }
        });
    }

    public MutableLiveData<Weather> getWeatherData(){
        return mMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

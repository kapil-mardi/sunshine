package kapsapps.xyz.sunshine.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import kapsapps.xyz.sunshine.model.ForecastModal;
import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.service.ServiceAPI;
import kapsapps.xyz.sunshine.service.ServiceProvider;
import kapsapps.xyz.sunshine.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastViewModal extends ViewModel {

    private MutableLiveData<ForecastModal> mMutableLiveData;
    private ServiceAPI serviceAPI;
    private String mCityName;


    public void setCityName(String cityName){
        this.mCityName = cityName;
        makeServerCall();
    }

    public ForecastViewModal(){
        mMutableLiveData = new MutableLiveData<>();
        mMutableLiveData.setValue(null);

        serviceAPI = ServiceProvider.getInstance().getServiceAPI();
        makeServerCall();
    }

    private void makeServerCall() {

        Call<ForecastModal> data = serviceAPI.getForecastDataForCity(mCityName,Constants.API_KEY);
        data.enqueue(new Callback<ForecastModal>() {
            @Override
            public void onResponse(Call<ForecastModal> call, Response<ForecastModal> response) {
                if(response.body() != null){
                    mMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ForecastModal> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<ForecastModal> getForecastData(){
        return mMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

package kapsapps.xyz.sunshine.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ServiceProvider {

    private static ServiceProvider instance = new ServiceProvider();

    private final String BASE_URL = "http://api.openweathermap.org";

    private Retrofit mRetrofit;

    private ServiceProvider(){}


    public static ServiceProvider getInstance(){
        return instance;
    }

    public ServiceAPI getServiceAPI(){
        return getServiceProvider().create(ServiceAPI.class);
    }

    private Retrofit getServiceProvider(){

        if(mRetrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            });

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();


            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }
}

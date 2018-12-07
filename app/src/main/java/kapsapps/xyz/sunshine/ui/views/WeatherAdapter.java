package kapsapps.xyz.sunshine.ui.views;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kapsapps.xyz.sunshine.R;
import kapsapps.xyz.sunshine.model.MainInfo;
import kapsapps.xyz.sunshine.model.Weather;
import kapsapps.xyz.sunshine.model.WeatherData;

public class WeatherAdapter extends RecyclerView.Adapter {

    private int mClickedPosition = 0;
    private LayoutInflater mLayoutInflater;
    private List<Weather> mWeatherData;
    private Context mContext;

    public WeatherAdapter(LayoutInflater layoutInflater, Context context) {
        this.mLayoutInflater = layoutInflater;
        this.mContext = context;
    }

    public void setData(List<Weather> weatherData) {
        if (mWeatherData == null)
            mWeatherData = new ArrayList<>();
        mWeatherData.clear();
        mWeatherData.addAll(weatherData);
        notifyDataSetChanged();
    }

    public class SingleLineViewHolder extends ViewHolder {

        @BindView(R.id.date)
        TextView dateView;

        @BindView(R.id.temp)
        TextView avgTemp;

        @BindView(R.id.weather_description)
        TextView weatherDescription;

        @BindView(R.id.weather_img)
        AppCompatImageView weatherImg;

        public SingleLineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindData(MainInfo mainInfo, WeatherData weatherData, long date) {

            Date dateToShow = new Date();
            dateToShow.setTime(date * 1000);
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM\nhh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE\nhh:mm a");
            String dateStr = dateFormat.format(dateToShow);

            int avgTempC = (int) (mainInfo.getTemp() - 273.15);

            dateView.setText(dateStr);
            avgTemp.setText(avgTempC + "\u2103");

            weatherDescription.setText(weatherData.getMain());

            String imageUrl = "http://openweathermap.org/img/w/" + weatherData.getIcon() + ".png";

            /*Glide.with(mContext)
                    .load(R.drawable.ic_sun)
                    .into(weatherImg);*/
            AnimatedVectorDrawableCompat vectorDrawable = AnimatedVectorDrawableCompat.create(mContext,R.drawable.thunder_animated_vector);

            weatherImg.setImageDrawable(vectorDrawable);

            Animatable animatable = (Animatable) weatherImg.getDrawable();
            animatable.start();
        }
    }

    private class ExpandedViewHolder extends ViewHolder {

        public ExpandedViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.single_line_info, viewGroup, false);
        return new SingleLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        long date = mWeatherData.get(i).getDateTime();
        MainInfo mainInfo = mWeatherData.get(i).getMain();
        WeatherData weatherData = mWeatherData.get(i).getWeatherData().get(0);

        ((SingleLineViewHolder) viewHolder).bindData(mainInfo, weatherData, date);

    }

    @Override
    public int getItemCount() {
        if(mWeatherData == null) return 0;
        return mWeatherData.size();
    }
}

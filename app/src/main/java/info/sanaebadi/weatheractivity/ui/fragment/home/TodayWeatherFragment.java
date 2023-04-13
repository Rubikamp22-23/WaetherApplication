package info.sanaebadi.weatheractivity.ui.fragment.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import info.sanaebadi.weatheractivity.api.ApiClient;
import info.sanaebadi.weatheractivity.api.ApiInterface;
import info.sanaebadi.weatheractivity.base.BaseFragment;
import info.sanaebadi.weatheractivity.databinding.FragmentTodayWeatherBinding;
import info.sanaebadi.weatheractivity.model.current.CurrentWeatherResponse;
import info.sanaebadi.weatheractivity.model.current.DataItem;
import info.sanaebadi.weatheractivity.model.current.ResourcesResponse;
import info.sanaebadi.weatheractivity.util.Const;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayWeatherFragment extends BaseFragment {

    private FragmentTodayWeatherBinding binding;
    private ApiInterface apiInterface;
    private static final String TAG = "TodayWeatherFragment";

    private String cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTodayWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

//        callResourcesResponse("35.72687405640683", "51.40798507487467", Const.WEATHER_API_KEY);
        binding.edittextCityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cityName = editable.toString();
            }
        });

        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiCity(cityName, Const.WEATHER_API_KEY);
            }
        });



    }


    private void callApiCity(String city, String apiKey) {
        Call<CurrentWeatherResponse> currentByCity = apiInterface.getCurrentWeatherByCityResponse(city, apiKey);
        currentByCity.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                CurrentWeatherResponse currentWeatherResponse = response.body();
                binding.textviewCityName.setText("City Name: " + currentWeatherResponse.getName());
                binding.textviewDescription.setText("Description : " + currentWeatherResponse.getWeather().get(0).getDescription());
                binding.textviewMainWeather.setText("Main Weather : " + currentWeatherResponse.getWeather().get(0).getMain());
                binding.textviewMaxTemp.setText("MAX Temp : " + currentWeatherResponse.getMain().getTempMax());
                binding.textviewMinTemp.setText("MIN Temp : " + currentWeatherResponse.getMain().getTempMin());

            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void callResourcesResponse(String lat, String lon, String apiKey) {
        Call<CurrentWeatherResponse> currentWeatherResponseCall = apiInterface.getCurrentWeatherResponse(lat, lon, apiKey);
        currentWeatherResponseCall.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                CurrentWeatherResponse currentWeatherResponse = response.body();
//                binding.textviewCityName.setText("City Name: " + currentWeatherResponse.getName());
//                binding.textviewDescription.setText("Description : " + currentWeatherResponse.getWeather().get(0).getDescription());
//                binding.textviewMainWeather.setText("Main Weather : " + currentWeatherResponse.getWeather().get(0).getMain());
//                binding.textviewMaxTemp.setText("MAX Temp : " + currentWeatherResponse.getMain().getTempMax());
//                binding.textviewMinTemp.setText("MIN Temp : " + currentWeatherResponse.getMain().getTempMin());
//

            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
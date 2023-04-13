package info.sanaebadi.weatheractivity.api;

import info.sanaebadi.weatheractivity.model.CurrentWeatherResponse;
import info.sanaebadi.weatheractivity.model.ResourcesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeatherResponse(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appKey);


}

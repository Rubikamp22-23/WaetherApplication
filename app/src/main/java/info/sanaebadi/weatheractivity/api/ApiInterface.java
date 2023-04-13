package info.sanaebadi.weatheractivity.api;

import info.sanaebadi.weatheractivity.model.current.CurrentWeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeatherResponse(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appKey);

    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeatherByCityResponse(
            @Query("q") String city,
            @Query("appid") String appKey);

}

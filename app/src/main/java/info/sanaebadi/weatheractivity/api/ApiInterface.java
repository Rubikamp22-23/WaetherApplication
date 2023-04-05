package info.sanaebadi.weatheractivity.api;

import info.sanaebadi.weatheractivity.model.ResourcesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/unknown")
    Call<ResourcesResponse> getResourceResponse();
}

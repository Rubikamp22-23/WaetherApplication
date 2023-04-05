package info.sanaebadi.weatheractivity.ui.fragment.home;

import android.os.Bundle;
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
import info.sanaebadi.weatheractivity.model.DataItem;
import info.sanaebadi.weatheractivity.model.ResourcesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayWeatherFragment extends BaseFragment {

    private FragmentTodayWeatherBinding binding;
    private ApiInterface apiInterface;
    private static final String TAG = "TodayWeatherFragment";

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

        callResourcesResponse();
    }

    private void callResourcesResponse() {
        Call<ResourcesResponse> resourcesResponseCall = apiInterface.getResourceResponse();
        resourcesResponseCall.enqueue(new Callback<ResourcesResponse>() {
            @Override
            public void onResponse(Call<ResourcesResponse> call, Response<ResourcesResponse> response) {

                binding.prgressbar.setVisibility(View.VISIBLE);

                ResourcesResponse resourcesResponse = response.body();

                Log.i(TAG, "onResponse: " + resourcesResponse);

                String displayResponse = "";

                Integer text = resourcesResponse.getPage();
                Integer total = resourcesResponse.getTotal();
                Integer totalPages = resourcesResponse.getTotalPages();
                List<DataItem> datumList = resourcesResponse.getData();

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (DataItem datum : datumList) {
                    displayResponse += datum.getId() + " " + datum.getName() + " " + datum.getPantoneValue() + " " + datum.getYear() + "\n";
                }

                binding.textviewResponse.setText(displayResponse);

                binding.prgressbar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ResourcesResponse> call, Throwable t) {
                binding.prgressbar.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: " + t.getMessage());
                binding.textviewResponse.setText(t.getMessage());

            }
        });


    }
}
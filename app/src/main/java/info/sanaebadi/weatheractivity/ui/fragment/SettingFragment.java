package info.sanaebadi.weatheractivity.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.sanaebadi.weatheractivity.base.BaseFragment;
import info.sanaebadi.weatheractivity.databinding.FragmentSettingBinding;


public class SettingFragment extends BaseFragment {

    private FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
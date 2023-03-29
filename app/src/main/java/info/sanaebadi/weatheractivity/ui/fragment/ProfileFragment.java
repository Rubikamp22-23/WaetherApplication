package info.sanaebadi.weatheractivity.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import info.sanaebadi.weatheractivity.R;
import info.sanaebadi.weatheractivity.base.BaseFragment;
import info.sanaebadi.weatheractivity.databinding.FragmentProfileBinding;


public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRawVideo();

    }

    private void setupRawVideo() {
        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.profile_video;
        Uri uri = Uri.parse(videoPath);
        binding.video.setVideoURI(uri);
        MediaController mediaController = new MediaController(getActivity());
        binding.video.setMediaController(mediaController);
        mediaController.setAnchorView(binding.video);
    }

}
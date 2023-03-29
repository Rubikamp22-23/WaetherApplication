package info.sanaebadi.weatheractivity.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import info.sanaebadi.weatheractivity.R;
import info.sanaebadi.weatheractivity.base.BaseFragment;
import info.sanaebadi.weatheractivity.databinding.FragmentLoginBinding;
import info.sanaebadi.weatheractivity.util.Const;


public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navController = Navigation.findNavController(view);
        setInputsUp();


    }

    private void setInputsUp() {
        binding.edittextUsername.setText("");
        binding.edittextPassword.setText("");
        sharedPreferences = requireActivity().getSharedPreferences(Const.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        checkLogin();

        binding.buttonLogin.setOnClickListener(view -> {

            if (binding.edittextUsername.getText().toString().equals("") || binding.edittextPassword.getText().toString().equals("")) {
                Toast.makeText(requireActivity(), "Please Enter userName or Password", Toast.LENGTH_SHORT).show();
            } else {
                doLogin(binding.edittextUsername.getText().toString(), binding.edittextPassword.getText().toString());
            }
        });
    }

    private void checkLogin() {
        if (sharedPreferences == null) {
            sharedPreferences = requireActivity().getSharedPreferences(Const.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        }
        String userName = sharedPreferences.getString(Const.USER_NAME_KEY, "");

        if (userName != null && !userName.equals("")) {
            navController.navigate(R.id.action_loginFragment_to_homeFragment);
            requireActivity().finish();
        }
    }

    private void doLogin(String userName, String password) {
        try {
            if (password.equals("sanaebadi")) {
                if (sharedPreferences == null) {
                    sharedPreferences = requireActivity().getSharedPreferences(Const.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    sharedEditor = sharedPreferences.edit();
                    sharedEditor.putString(Const.USER_NAME_KEY, userName);
                    sharedEditor.commit();

                    navController.navigate(R.id.action_loginFragment_to_homeFragment);
                }
            } else {
                Toast.makeText(requireActivity(), "Invalid Credentails", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }

    }
}
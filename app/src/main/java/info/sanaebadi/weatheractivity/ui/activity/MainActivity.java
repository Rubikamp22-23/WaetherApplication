package info.sanaebadi.weatheractivity.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import info.sanaebadi.weatheractivity.R;

public class MainActivity extends AppCompatActivity {

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       test.setText("jkasnfjdhb");
    }
}
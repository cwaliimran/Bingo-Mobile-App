package com.bingoplayer.app.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bingoplayer.app.R;
import com.bingoplayer.app.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String result = bundle.getString("result");
            if (result.equals("winner")) {
                //show winner graphics
                //already shown in XML
            } else {
                //show finished graphics
                binding.ivResult.setImageResource(R.drawable.icon_finished);
                binding.tvTitle.setText("Finished");
            }
        }

    }

    public void PlayAgain(View view) {
        finish();
    }
}
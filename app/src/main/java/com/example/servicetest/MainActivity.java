package com.example.servicetest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicetest.databinding.ActivityMainBinding;
import com.example.servicetest.services.MusicService;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
    }

    private void initUI() {
        binding.buttonStart.setOnClickListener(v -> {
            startService(new Intent(this, MusicService.class));
            v.setClickable(false);
            binding.buttonResume.setClickable(false);
            binding.buttonPause.setClickable(true);
            binding.buttonStop.setClickable(true);
        });

        binding.buttonPause.setOnClickListener(v -> {
            startService(new Intent(this, MusicService.class).putExtra("Pause", true));
            v.setClickable(false);
            binding.buttonResume.setClickable(true);
            binding.buttonStart.setClickable(false);
            binding.buttonStop.setClickable(true);
        });

        binding.buttonResume.setOnClickListener(v -> {
            startService(new Intent(this, MusicService.class).putExtra("Resume", true));
            v.setClickable(false);
            binding.buttonPause.setClickable(true);
            binding.buttonStart.setClickable(false);
            binding.buttonStop.setClickable(true);
        });

        binding.buttonStop.setOnClickListener(v -> {
            stopService(new Intent(this, MusicService.class));
            v.setClickable(false);
            binding.buttonStart.setClickable(true);
            binding.buttonPause.setClickable(false);
        });
    }
}
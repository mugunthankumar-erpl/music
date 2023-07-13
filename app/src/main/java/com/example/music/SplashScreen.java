package com.example.music;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    TextView beatBlend,textview1;
    Button getStartedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLaunch = preferences.getBoolean("isFirstLaunch", true);
        beatBlend = findViewById(R.id.beatblend);
        textview1 = findViewById(R.id.textview1);
        getStartedButton = findViewById(R.id.getstarted);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(beatBlend, "translationX", 200f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(textview1, "translationX", -500f, 0f);
        animatorSet.playTogether(animator1, animator2);
        animatorSet.setDuration(750);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {
                getStartedButton.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                getStartedButton.setEnabled(true);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {
            }
        });
        animatorSet.start();

        if (isFirstLaunch) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();

            ProgressBar progressBar = findViewById(R.id.progressBar);
            getStartedButton.setOnClickListener(v -> {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(SplashScreen.this, HomePage.class));
                    finish();
                }, 3000);
            });
        } else {
            Intent intent = new Intent(SplashScreen.this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

}


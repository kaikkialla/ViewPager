package com.banana.y17_2.promo;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.TransitionOptions;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    TextView My_Account_Button;
    ImageView FiltersButton;
    Button Discover_More_Button;
    RelativeLayout rootContainer;
    Timer swipeTimer = new Timer();

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = findViewById(R.id.View_Pager);
        final CircleIndicator indicator = findViewById(R.id.indicator);
        My_Account_Button = findViewById(R.id.My_Account_Button);
        FiltersButton = findViewById(R.id.Filters_Button);
        Discover_More_Button = findViewById(R.id.Discover_More_Button);
        rootContainer = findViewById(R.id.Root_container);

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        //viewPager.setPageTransformer(true, new ZoomOutPageTransformer()); //Кастомная анимация смены картинок

        indicator.setViewPager(viewPager);

        swipeImage();

/*
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "ACTION DOWN", Toast.LENGTH_SHORT).show();
                swipeTimer.cancel();
                swipeImage();
            }
        });
*/

        Discover_More_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, FiltersButton, "Filters_Button_Transition");
                startActivity(intent, options.toBundle());

            }
        });


    }


    /**
     * Метод для автоматической смены картинки
     */
    public void swipeImage() {
        if (swipeTimer != null) {
            swipeTimer.cancel();
        }
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                int i = viewPager.getCurrentItem() + 1;
                if (i > Database.Objs.length - 1) {
                    i = 0;
                }
                viewPager.setCurrentItem(i, true);
                Log.e("ViewNumber", String.valueOf(viewPager.getCurrentItem()));
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }





}



//[-1;1]

//Modify the default slide transition to shrink the page as well
                /* float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                */
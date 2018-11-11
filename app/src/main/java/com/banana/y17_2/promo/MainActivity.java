package com.banana.y17_2.promo;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.TransitionOptions;

import java.util.Timer;
import java.util.TimerTask;


import android.support.v4.view.PagerAdapter;



import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    TextView My_Account_Button;
    ImageView FiltersButton;
    Button Discover_More_Button;
    RelativeLayout rootContainer;
    Timer swipeTimer = new Timer();

    ViewPager viewPager;


    int width;
    int height;

    FrameLayout frameLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final View view = findViewById(android.R.id.content);

        viewPager = findViewById(R.id.View_Pager);
        final CircleIndicator indicator = findViewById(R.id.indicator);
        My_Account_Button = findViewById(R.id.My_Account_Button);
        FiltersButton = findViewById(R.id.Filters_Button);
        Discover_More_Button = findViewById(R.id.Discover_More_Button);
        rootContainer = findViewById(R.id.Root_container);
        frameLayout = findViewById(R.id.Frame_Layout);



        //Получаем размеры экрана в пикселях(в данном случае только ширину)
        rootContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                width = rootContainer.getWidth();//Получаем ширину экрана

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();//Получаем доступ к размерам ViewPager
                params.width = width;//Теперь ширина VP равна ширине экрана
                viewPager.setLayoutParams(params);//Применяем изменения

            }
        });





        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer()); //Кастомная анимация смены картинок
        indicator.setViewPager(viewPager);//Прикрепляем индикатор к VP



        //Код для Shared Elements Transition. К проекту вообще не откносится
        Discover_More_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int ResIDnum = viewPager.getCurrentItem();
                //final Obj obj = Database.Objs[ResIDnum];
                //int ResID = obj.resId;

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                //intent.putExtra("ResID", ResID);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, FiltersButton, "Filters_Button_Transition");
                startActivity(intent, options.toBundle());

            }
        });

    }
}


class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.7f;//Уменьшение размеров элемента, когда он находится не в главной позиции
    private static final float MIN_ALPHA = 0.7f;//Увеличение прозрачности элемента, когда он находится не в главной позиции

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]

            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
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




        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
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
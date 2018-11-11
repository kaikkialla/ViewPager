package com.banana.y17_2.promo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Activity2 extends AppCompatActivity {

    ImageView ViewPagerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ViewPagerImageView = findViewById(R.id.imageView);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            int resId = (int) b.get("ResID");
            ViewPagerImageView.setImageResource(resId);
        }

    }
}

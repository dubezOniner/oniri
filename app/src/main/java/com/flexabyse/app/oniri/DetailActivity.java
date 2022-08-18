package com.flexabyse.app.oniri;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class DetailActivity extends AppCompatActivity {

    private TextView debugText, title, date;
    private ScrollView scrollView;
    private View anchorView;

    private static final int deltaY_ThresholdDp = 190;
    boolean isAnchored = false;
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        debugText = findViewById(R.id.debug);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);

        String titleTxt = getIntent().getStringExtra("title");
        String dateTxt = getIntent().getStringExtra("date");
        int img = getIntent().getIntExtra("img", 0);

        title.setText(titleTxt);
        date.setText(dateTxt);

        metrics = getResources().getDisplayMetrics();

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        anchorView = findViewById(R.id.sticky_fabs);
        scrollView = findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                //debugText.setText("scrollY: "+scrollY + ", isAnchored: "+isAnchored);
                int deltaY = (int)(scrollY / metrics.density); // factor in screen density

                if (isAnchored && deltaY < deltaY_ThresholdDp) {
                    isAnchored = false;
                }

                if (!isAnchored && deltaY > deltaY_ThresholdDp) {
                    isAnchored = true;
                }

                if (isAnchored) {
                    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) anchorView.getLayoutParams();
                    lp.topMargin = (int)(scrollY - deltaY_ThresholdDp * metrics.density);
                    anchorView.setLayoutParams(lp);
                }
            }
        });


    }
}
package com.example.myapplication;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class SubscriptionActivity extends AppCompatActivity {

    int MIN_DISTANCE = 150;
    int OFF_PATH = 100;
    int VELOCITY_THRESHOLD = 75;
    int imageIndex = 0;

    private ViewFlipper simpleViewFlipper;

    GestureDetector detector;
    View.OnTouchListener listener;

    class GalleryGestureDetector implements GestureDetector.OnGestureListener {

        Animation in_prev = AnimationUtils.loadAnimation(SubscriptionActivity.this, android.R.anim.slide_in_left);
        Animation out_prev = AnimationUtils.loadAnimation(SubscriptionActivity.this, android.R.anim.slide_out_right);
        Animation in_next = AnimationUtils.loadAnimation(SubscriptionActivity.this, R.anim.slide_in_right);
        Animation out_next = AnimationUtils.loadAnimation(SubscriptionActivity.this, R.anim.slide_out_left);
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {}

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {}

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            if (Math.abs(event1.getY() - event2.getY()) > OFF_PATH)
                return false;


            if (event1.getX() - event2.getX() > MIN_DISTANCE && Math.abs(velocityX) > VELOCITY_THRESHOLD && simpleViewFlipper.getDisplayedChild() != simpleViewFlipper.getChildCount() - 1) {
                // Swipe left
                simpleViewFlipper.setInAnimation(in_next);
                simpleViewFlipper.setOutAnimation(out_next);
                simpleViewFlipper.showNext();
            }
            else if(event2.getX() - event1.getX() > MIN_DISTANCE && Math.abs(velocityX) > VELOCITY_THRESHOLD && simpleViewFlipper.getDisplayedChild() != 0) {
                // Swipe right
                simpleViewFlipper.setInAnimation(in_prev);
                simpleViewFlipper.setOutAnimation(out_prev);
                simpleViewFlipper.showPrevious();
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_subscription);

        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper);

        detector = new GestureDetector(this, new GalleryGestureDetector());
        listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        };

        simpleViewFlipper.setOnTouchListener(listener);

        /* TODO redirect to payment for the buttons */

    }


}


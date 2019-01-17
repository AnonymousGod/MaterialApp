package com.ranjan.materialapp;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.ranjan.materialapp.paging.PagingActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ImageViewCompat;

public class AnimationActivity extends AppCompatActivity {

    int duration = 4000;
    View view;
    Button button;
    Handler animationHandler = new Handler();
    ImageView objectAnimator, valueAnimator, animationList, xmlAnimator, loading;
    private Runnable runLoading = new Runnable() {
        public void run() {
            animateLoading();
            animationHandler.postDelayed(this, 200);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        objectAnimator = findViewById(R.id.object_animator);
        valueAnimator = findViewById(R.id.value_animator);
        animationList = findViewById(R.id.animation_list);
        xmlAnimator = findViewById(R.id.xml_animator);
        loading = findViewById(R.id.bluesapling_loading);
        button = findViewById(R.id.button); //For making animation on state change through xml file
        view = findViewById(R.id.rectangle_background);//For adding gradient, stroke, solid, corners etc through xml file


        //Animate using multiple ObjectAnimator and combining them with AnimatorSet
        animateObjectAnimator();
        //animateObjectAnimatorAlt1();
        //animateSubclassObjectAnimators();

        //Animate a list with animation-list
        animateAnimationList();

        //Animate using multiple ValueAnimator
        animateValueAnimator();

        //Animate using ValueAnimator with new AnimatorListener for restarting the animation, and
        // AnimatorUpdateListener for what to do in animation.
        //animateValueAnimatorAlt();

        //Animate xml animator
        Drawable drawable = xmlAnimator.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        //Animate Bluesapling loading through xml. repeat_count not working in xml
        //animateXMLAnimatorLoading();

        //Animate Bluesapling loading through xml. Using handler to re-run the process
        animateXMLAnimatorLoadingAlt();

        animateValueAnimator();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_custom_activity:
                startActivity(new Intent(this, CustomViewActivity.class));
                return true;
            case R.id.action_room_activity:
                startActivity(new Intent(this, RoomActivity.class));
                return true;
            case R.id.action_paging_activity:
                startActivity(new Intent(this, RoomNetworkActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void animateObjectAnimator() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(objectAnimator, "scaleX", 0.8f, 1.2f);
        scaleXAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        scaleXAnimation.setRepeatCount(Animation.INFINITE);
        scaleXAnimation.setDuration(duration);

        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(objectAnimator, "scaleY", 0.8f, 1.2f);
        scaleYAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        scaleYAnimation.setRepeatCount(Animation.INFINITE);
        scaleYAnimation.setDuration(duration);

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(objectAnimator, "rotation", 0, 360);
        rotateAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(duration);

        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(scaleXAnimation, scaleYAnimation, rotateAnimation);
        animSetXY.start();
    }

    void animateObjectAnimatorAlt1() {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.2f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.2f);
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat("rotation", 0, 360);
        ObjectAnimator objectAnimatorGroup = ObjectAnimator.ofPropertyValuesHolder(objectAnimator, pvhX, pvhY, pvhR);
        objectAnimatorGroup.setDuration(duration);
        objectAnimatorGroup.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimatorGroup.setStartDelay(2000);
        objectAnimatorGroup.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimatorGroup.start();
    }

    void animateSubclassObjectAnimators() {
        AnimationSet set = new AnimationSet(true);

        Animation anim = new ScaleAnimation(0.8f, 1.2f, 0.8f, 1.2f, 24f, 24f);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        Animation rotate = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatMode(Animation.REVERSE);
        rotate.setRepeatCount(Animation.INFINITE);

        set.addAnimation(anim);
        set.addAnimation(rotate);
        set.setRepeatMode(Animation.REVERSE);
        set.setRepeatCount(Animation.INFINITE);
        set.setDuration(duration);

        objectAnimator.startAnimation(set);
    }

    void animateValueAnimator() {
        int colorFrom = getResources().getColor(R.color.black);
        int colorTo = getResources().getColor(R.color.colorPrimary);
        ValueAnimator animation = ValueAnimator.ofArgb(colorFrom, colorTo);
        animation.setDuration(duration);
        animation.setStartDelay(duration);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                int animatedValue = (int) updatedAnimation.getAnimatedValue();
                ImageViewCompat.setImageTintList(valueAnimator, ColorStateList.valueOf(animatedValue));
            }
        });
        animation.start();
    }

    public void animateValueAnimatorAlt() {
        final int colorFrom = getResources().getColor(R.color.black);
        final int colorTo = getResources().getColor(R.color.colorPrimary);
        ValueAnimator animation = ValueAnimator.ofArgb(colorFrom, colorTo);
        animation.setDuration(1000);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.setStartDelay(4000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageViewCompat.setImageTintList(objectAnimator,
                                ColorStateList.valueOf(colorTo));
                    }
                }, 3000);
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                int animatedValue = (int) updatedAnimation.getAnimatedValue();
                ImageViewCompat.setImageTintList(objectAnimator, ColorStateList.valueOf(animatedValue));
            }
        });
        animation.start();
    }

    void animateAnimationList() {
        animationList.setBackgroundResource(R.drawable.animation_list);
        AnimationDrawable listAnimation = (AnimationDrawable) animationList.getBackground();
        listAnimation.start();
    }

    void animateXMLAnimatorLoading() {
        //Using android:repeatCount="infinite" with android:startOffset didn't result in expected animation.
        //Next animation wasn't getting started after the offset value. Worked only for first time.
        Drawable loadingDrawable = loading.getDrawable();
        if (loadingDrawable instanceof Animatable) {
            ((Animatable) loadingDrawable).start();
        }
    }

    void animateXMLAnimatorLoadingAlt() {
        //This is somewhat working as intended but not expected. startOffset and duration values are getting
        //scaled somehow but overall flow looks good with adjusted values.
        animationHandler.postDelayed(runLoading, 0);
    }

    public void animateLoading() {
        Drawable simpleDrawable = loading.getDrawable();
        if (simpleDrawable instanceof Animatable) {
            ((Animatable) simpleDrawable).start();
        }
    }

}

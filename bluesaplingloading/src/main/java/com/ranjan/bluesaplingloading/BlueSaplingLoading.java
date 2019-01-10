package com.ranjan.bluesaplingloading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;

/**
 * Created by BlueSapling on 1/9/19.
 */
public class BlueSaplingLoading extends LinearLayout {
    String TAG = "BlueSaplingLoading ";
    ImageView loading;
    boolean changeColor;
    Handler botAnimHandler, midAnimHolder, resetHandler, animationHandler;
    int loadingBottomColor, loadingMidColor, loadingTopColor;
    int firstDuration, secondDuration, thirdDuration, resetDuration;
    VectorDrawableCompat.VFullPath botPath, midPath, topPath;
    AnimatedVectorDrawable d;

    public BlueSaplingLoading(Context context) {
        super(context);
        this.setWillNotDraw(false);
        initialize(context, null);
    }

    public BlueSaplingLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        inflate(context, R.layout.new_loading, this);
        loading = findViewById(R.id.bluesapling_loading);

        botAnimHandler = new Handler();
        midAnimHolder = new Handler();
        resetHandler = new Handler();
        animationHandler = new Handler();

        if (attrs == null) {
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BlueSaplingLoading);
        ta.getColor(R.styleable.BlueSaplingLoading_bottomColor, Color.GREEN);
        ta.getColor(R.styleable.BlueSaplingLoading_middleColor, Color.GREEN);
        ta.getColor(R.styleable.BlueSaplingLoading_topColor, Color.GREEN);
        loadingBottomColor = ta.getColor(R.styleable.BlueSaplingLoading_bottomColor, Color.GRAY);
        loadingMidColor = ta.getColor(R.styleable.BlueSaplingLoading_middleColor, Color.GRAY);
        loadingTopColor = ta.getColor(R.styleable.BlueSaplingLoading_topColor, Color.GRAY);
        firstDuration = ta.getColor(R.styleable.BlueSaplingLoading_firstDuration, 1000);
        secondDuration = ta.getColor(R.styleable.BlueSaplingLoading_secondDuration, 1000);
        thirdDuration = ta.getColor(R.styleable.BlueSaplingLoading_thirdDuration, 1000);
        resetDuration = ta.getColor(R.styleable.BlueSaplingLoading_resetDuration, 1000);

        ta.recycle();

        VectorChildFinder vector = new VectorChildFinder(context, R.drawable.bluesapling_loading_grey, loading);
        botPath = vector.findPathByName("bot");
        midPath = vector.findPathByName("mid");
        topPath = vector.findPathByName("top");

        d = (AnimatedVectorDrawable) context.getDrawable(R.drawable.animated_loading_library);

        startAnimating();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void animateLoading() {
        loading.setImageDrawable(d);
        assert d != null;
        d.start();

//        Drawable simpleDrawable = loading.getDrawable();
//        if (simpleDrawable instanceof Animatable) {
//            ((Animatable) simpleDrawable).start();
//        }
    }

    void startAnimating() {
        animationHandler.postDelayed(runLoading, 0);
//        botAnimHandler.postDelayed(botRunnable, 0);
//        Log.d(TAG, "startAnimating: " + (firstDuration + secondDuration + thirdDuration + resetDuration));
//        resetHandler.postDelayed(resetRunnable, firstDuration + secondDuration + thirdDuration + resetDuration);
    }

    private Runnable botRunnable = new Runnable() {
        public void run() {
            Log.d(TAG, "run: botRunnable");
            botPath.setFillColor(loadingBottomColor);
            //botAnimHandler.postDelayed(this, firstDuration);
        }
    };

    private Runnable runLoading = new Runnable() {
        public void run() {
            animateLoading();
            animationHandler.postDelayed(this, 200);
        }
    };

    private Runnable resetRunnable = new Runnable() {
        public void run() {
            Log.d(TAG, "run: resetRunnable");
            botPath.setFillColor(Color.GRAY);
            changeColor = true;
            invalidate();
            requestLayout();
            //botAnimHandler.postDelayed(this, firstDuration + secondDuration + thirdDuration + resetDuration);
        }
    };

}

//This isn't working because onDraw isn't getting called. By default onDraw doesn't get called for viewGroups
//But even if you set setWillNotDraw to false, it will not change because we are just changing the value of color
//of local objects. We need to use onDraw somehow.
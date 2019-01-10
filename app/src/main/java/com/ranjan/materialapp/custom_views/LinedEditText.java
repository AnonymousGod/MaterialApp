package com.ranjan.materialapp.custom_views;

/**
 * Created by BlueSapling on 1/8/19.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

public class LinedEditText extends AppCompatEditText {
    private Rect mRect;
    private Paint mPaint;

    // This constructor is used by LayoutInflater
    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Creates a Rect and a Paint object, and sets the style and color of the Paint object.
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
        mPaint.setStrokeWidth(8.0F);
    }

    public int changeValue(int x) {
        return x * 2;
    }

    public void main(String[] args) {
        int x = 3;
        changeValue(x);
    }

    /**
     * This is called to draw the LinedEditText object
     *
     * @param canvas The canvas on which the background is drawn.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // Gets the number of lines of text in the View.
        int count = getLineCount();
        // Gets the global Rect and Paint objects
        Rect r = mRect;
        Paint paint = mPaint;
        Log.d("LinedEditText", "onDraw: lineCount " + count);
        Log.d("LinedEditText", "onDraw: rectangle_background left " + r.left + " rectangle_background right " + r.right
                + " top " + r.top + " bottom " + r.bottom);
        /*
         * Draws one line in the rectangle for every line of text in the EditText
         */
        for (int i = 0; i < count; i++) {
            // Gets the baseline coordinates for the current line of text
            int baseline = getLineBounds(i, r);
            Log.d("LinedEditText", "i: " + i + ", baseline: " + baseline);
            Log.d("LinedEditText", "onDraw: rectangle_background left " + r.left + " rectangle_background right " + r.right
                    + " top " + r.top + " bottom " + r.bottom);
            /*
             * Draws a line in the background from the left of the rectangle to the right,
             * at a vertical position one dip below the baseline, using the "paint" object
             * for details.
             */
            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
        }
        // Finishes up by calling the parent method
        super.onDraw(canvas);
    }
}
package com.taal.welding.assistent.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import com.taal.welding.R;


/**
 * Created by Administrator on 2015-07-29.
 */
public class RevealBackgroundView extends View {
    public static final int STATE_NOT_STARTED = 0;
    public static final int STATE_FILL_STARTED = 1;
    public static final int STATE_FINISHED = 2;

    private static final Interpolator INTERPOLATOR = new AccelerateInterpolator();
    private static final int FILL_TIME = 600;
    private int state = STATE_NOT_STARTED;

    private Paint fillPaint;
    private int currentRadius;

    ObjectAnimator revealAnimator;
    private int startLocationX;
    private int startLocationY;
    private OnStateChangeListener onStateChangeListener;

    public RevealBackgroundView(Context context) {
        super(context);
        //init();
    }

    public RevealBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    public RevealBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RevealBackgroundView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //init();
    }


    private void init() {
        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(getResources().getColor(R.color.btn_context_menu_normal));
    }


    public void setFillPaintColor(int color) {
        fillPaint.setColor(color);
    }


    public void startFromLocation(int[] location) {
        changeState(STATE_FILL_STARTED);
        startLocationX = location[0];
        startLocationY = location[1];

        revealAnimator = ObjectAnimator.ofInt(this,"currentRadius",0,getHeight()).setDuration(FILL_TIME);
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                changeState(STATE_FINISHED);
            }
        });
        revealAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        if (state == STATE_FINISHED){
//            canvas.drawRect(0,0,getWidth(),getHeight(),fillPaint);
//        }else {
//            canvas.drawCircle(startLocationX,startLocationY,currentRadius,fillPaint);
//        }
    }

    public void setCurrentRadius(int currentRadius) {
        this.currentRadius = currentRadius;
        invalidate();
    }


    public void setToFinishedFrame() {
        changeState(STATE_FINISHED);
        invalidate();
    }


    private void changeState(int state) {
        if (this.state == state) {
            return;
        }

        this.state = state;
        if (onStateChangeListener != null) {
            onStateChangeListener.onStateChange(state);
        }
    }

    public int getState() {
        return state;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public static interface OnStateChangeListener {
        void onStateChange(int state);
    }

}

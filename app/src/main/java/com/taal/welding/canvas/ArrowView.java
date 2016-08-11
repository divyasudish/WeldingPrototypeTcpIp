package com.taal.welding.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by divyashreenair on 9/5/16.
 */
public class ArrowView extends View
{
    private Paint mPaint;
    private RectF mRect;
    private int mStrokeWidth = 10;
    private float mAngleStart;// = 270;
    private float mAngle;// = 0;
    private float arrowAngle = 0;
    private float currentAngle;
    int yColor;
    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path = new Path();
        float angle = getmAngle();

        float radius = canvas.getWidth()/3;
        float x = canvas.getWidth()/2;
        float y = canvas.getHeight()/2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(25);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(yColor);

        float l = 1.2f;
        float a = mAngle*(float) Math.PI/180;

        // Draw arrow
        path.moveTo(x+ (float) Math.cos(a) *radius, y + (float) Math.sin(a) * radius);
        path.lineTo(x+ (float) Math.cos(a + 0.1) *radius*l, y + (float) Math.sin(a + 0.1) * radius*l);
        path.lineTo(x+ (float) Math.cos(a - 0.1) *radius*l, y + (float) Math.sin(a - 0.1) * radius*l);
        path.lineTo(x+ (float) Math.cos(a) *radius, y + (float) Math.sin(a) * radius);
        canvas.drawPath(path, paint);
    }
    public void setColorCode(int x) {
        yColor = x;
        paint.setColor(x);
    }

    public float getAngle() {
        return mAngle;
    }
    public void setAngle(float angle) {
        this.mAngle = angle;
    }
    public float getmAngle() {
        return arrowAngle;
    }
    public void setmAngle(float angle) {
        this.arrowAngle = angle;
    }
    private Path path;
    private Paint paint;
}
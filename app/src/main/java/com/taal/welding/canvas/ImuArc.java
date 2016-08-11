package com.taal.welding.canvas;

/**
 * Created by divyashreenair on 29/4/16.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ImuArc extends View{
    private Paint paint;
    private Path path;
    private Paint mPaint;
    private RectF mRect;
    private int mStrokeWidth = 10;
    private int mAngleStart = 180;
    private float mAngle = 0;
    private float arrowAngle = 0;

    public ImuArc(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        path = new Path();
        mPaint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        MaskFilter mEmboss = new EmbossMaskFilter(new float[] { 0.0f, -1.0f, 0.5f},
                0.8f, 15, 1.0f);
        paint.setMaskFilter(mEmboss);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        System.out.println("width and height " + width + height);
        mRect = new RectF(50, 50, width-50, height-50);
        canvas.drawArc(mRect, mAngleStart, mAngle, false, paint);

        float angle = getmAngle();

        float radius = canvas.getWidth()/3;
        float x = canvas.getWidth()/2;
        float y = canvas.getHeight()/2;
//        final RectF oval = new RectF();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(25);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);

        float l = 1.2f;
        float a = angle*(float)Math.PI/180;
        // Draw arrow
        path.moveTo(x+ (float)Math.cos(a) *radius, y + (float)Math.sin(a) * radius);
        path.lineTo(x+ (float)Math.cos(a+0.1) *radius*l, y + (float)Math.sin(a+0.1) * radius*l);
        path.lineTo(x+ (float)Math.cos(a-0.1) *radius*l, y + (float)Math.sin(a-0.1) * radius*l);
        path.lineTo(x + (float) Math.cos(a) * radius, y + (float) Math.sin(a) * radius);
        canvas.drawPath(path, mPaint);
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

}
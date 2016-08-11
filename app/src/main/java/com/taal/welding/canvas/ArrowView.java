package com.taal.welding.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by divyashreenair on 9/5/16.
 */
public class ArrowView extends View
{
    public ArrowView(Context context) {
        super(context);
        path = new Path();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float angle = 270;

        float radius = canvas.getWidth()/3;
        float x = canvas.getWidth()/2;
        float y = canvas.getHeight()/2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(25);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        float l = 1.2f;
        float a = angle*(float)Math.PI/180;
        // Draw arrow
        path.moveTo(x+ (float)Math.cos(a) *radius, y + (float)Math.sin(a) * radius);
        path.lineTo(x+ (float)Math.cos(a+0.1) *radius*l, y + (float)Math.sin(a+0.1) * radius*l);
        path.lineTo(x+ (float)Math.cos(a-0.1) *radius*l, y + (float)Math.sin(a-0.1) * radius*l);
        path.lineTo(x+ (float)Math.cos(a) *radius, y + (float)Math.sin(a) * radius);
        canvas.drawPath(path, paint);
    }
    private Path path;
    private Paint paint;
}
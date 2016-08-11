package com.taal.welding.canvas;

/**
 * Created by divyashreenair on 29/4/16.
 */
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnimationArc extends Animation {

    private Arc arc;
    private RelArc relArc;
    private float oldAngle;
    private float newAngle;
    private float arrowAngle;
    
    public AnimationArc(Arc arc, float newAngle) {
        this.oldAngle = arc.getAngle();
        this.newAngle = newAngle;
        //this.arrowAngle = arrowAngle;
        this.arc = arc;
        System.out.println("Angles " + this.newAngle + "/// " + this.arrowAngle);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);
        arc.setAngle(angle);
        arc.requestLayout();
        arc.setmAngle(arrowAngle);
    }
}
package com.taal.welding.canvas;

/**
 * Created by divyashreenair on 29/4/16.
 */
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnimationAbsArc extends Animation {

    private AbsArc absArc;
    private float oldAngle;
    private float newAngle;
    private float arrowAngle;

    public AnimationAbsArc(AbsArc arc, int newAngle, float arrowAngle) {
        this.oldAngle = arc.getAngle();
        this.newAngle = newAngle;
        this.arrowAngle = arrowAngle;
        this.absArc = arc;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);
        absArc.setAngle(angle);
        absArc.requestLayout();
        absArc.setmAngle(arrowAngle);

    }
}
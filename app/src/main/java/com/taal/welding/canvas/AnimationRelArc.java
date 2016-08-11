package com.taal.welding.canvas;

/**
 * Created by divyashreenair on 29/4/16.
 */
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnimationRelArc extends Animation {

    private RelArc relarc;
    private float oldAngle;
    private float newAngle;
    private float arrowAngle;

    public AnimationRelArc(RelArc arc, int newAngle, float arrowAngle) {
        this.oldAngle = arc.getAngle();
        this.newAngle = newAngle;
        this.arrowAngle = arrowAngle;
        this.relarc = arc;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);
        relarc.setAngle(angle);
        relarc.requestLayout();
        relarc.setmAngle(arrowAngle);

    }
}
package com.taal.welding.canvas;

/**
 * Created by divyashreenair on 2/6/16.
 */
import android.animation.ObjectAnimator;
import android.util.Property;

public class FloatAnimation {
    public static final Property<FloatAnimation, Float> GROW_FACTOR = new Property<FloatAnimation, Float>(
            Float.class, "20.00") {
        @Override
        public void set(FloatAnimation object, Float value) {
            object.setGrowGfactor(value);
        }

        @Override
        public Float get(FloatAnimation object) {
            return object.getGrowFactor();
        }
    };

    private float mGrowFactor = 0f;

    public void setGrowGfactor(float grow) {
        mGrowFactor = grow;
        //invalidate the view to cause a redraw
        //in the onDraw() use the mGrowFactor to calculate the new radius
    }

    public float getGrowFactor() {
        return mGrowFactor;
    }

    public void startGrowAnimation() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, GROW_FACTOR, 1f);
        anim.setDuration(3000); //make animation 3 seconds long
        System.out.println("Inside start animation ");
        anim.start();
    }
}


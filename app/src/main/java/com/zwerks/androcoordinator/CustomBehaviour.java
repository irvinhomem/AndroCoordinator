package com.zwerks.androcoordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by irvin on 19/02/2017... adapted from AndroidAuthority.com
 */

public class CustomBehaviour extends CoordinatorLayout.Behavior<CircleImageView> {

    private final static String TAG = "CustomBehaviour";

    // The idea is that the View that uses this "behaviour" will have it's layout depending on another View
    public CustomBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // The "layoutDependsOn" method is overridden  to specify which class of Views we want to watch out for
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    // The "onDependentViewChanged" method is overridden so as to specify what should happen when the dependent view has changed
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        int[] dependencyLocation = new int[2];
        int[] childLocation = new int[2];

        /* Essentially this checks for the position of the Toolbar relative to the CircleImageView and increases
            the size of the CircleImageView correspondingly , using setScale()
        */
        dependency.getLocationInWindow(dependencyLocation);
        child.getLocationInWindow(childLocation);

        float diff = childLocation[1] - dependencyLocation[1];
        if(diff > 0) {
            float scale = diff/(float)childLocation[1];
            Log.d(TAG, "scale == " + scale);
            child.setScaleX(1+scale);
            child.setScaleY(1+scale);
        }
        return false;
    }
}
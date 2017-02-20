package com.zwerks.androcoordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
        return dependency instanceof Toolbar || dependency instanceof Snackbar.SnackbarLayout;
        //return dependency instanceof Toolbar;
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

        //Check for the current height of the Snackbar, and Translate the childView on the Y-axis by the height
        //"Dependency" here is the Snackbar and "child" is the CircleImageView
        if(dependency instanceof Snackbar.SnackbarLayout){
            //Log.d(TAG, "Snackbar Height: " + String.valueOf(dependency.getHeight()));
            //Log.d(TAG, "Snackbar Top: " + String.valueOf(dependency.getTop()));
            Log.d(TAG, "Snackbar TranslationY: " + String.valueOf(dependency.getTranslationY()));
            Log.d(TAG, "Snackbar Y-location: " + String.valueOf(dependency.getY()));

            float newPosition = dependency.getTranslationY();
            Log.d(TAG, "New POSITION: " + String.valueOf(newPosition));
            //child.setY(dependency.getY());
            //child.setTranslationY(dependency.getTranslationY());          // <-- Somewhat work
            //child.setTranslationY(dependency.getTranslationY()-60);          // <-- Somewhat work
            child.setTranslationY(dependency.getTranslationY() - dependency.getHeight());          // <-- WORKS !!!!!!! Finally!
            //child.setTranslationY(dependency.getTranslationY() + (dependency.getY() - child.getY()));  //<-- Somewhat works
            //child.setY(dependency.getTranslationY() + (dependency.getY() - child.getY()));  //<-- Somewhat also works

            //child.setTranslationY(dependency.getTranslationY() + dependency.getHeight());
            //child.setTranslationY(dependency.getTranslationY() + dependency.getMeasuredHeight());
            //child.setY(child.getY() - (dependency.getY() - child.getY()));
        }

        return false;
    }
}
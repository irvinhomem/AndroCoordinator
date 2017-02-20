package com.zwerks.androcoordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by irvin on 20/02/2017.
 */

public class CircleImageViewRiseBehaviour extends CoordinatorLayout.Behavior<CircleImageView> {

    String LOG_TAG = this.getClass().getSimpleName();

    public CircleImageViewRiseBehaviour(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        //return super.layoutDependsOn(parent, child, dependency);
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        //return super.onDependentViewChanged(parent, child, dependency);
        float translationY = getCircleImageViewTranslationYForSnackbar(parent, child);
        float percentComplete = -translationY / dependency.getHeight();
        float scaleFactor = 1 - percentComplete;

        child.setScaleX(scaleFactor);
        child.setScaleY(scaleFactor);
        return false;
    }

    private float getCircleImageViewTranslationYForSnackbar(CoordinatorLayout Layoutparent, CircleImageView CircleImagechild) {
        float minOffset = 0;
        final List<View> dependencies = Layoutparent.getDependencies(CircleImagechild);

        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && Layoutparent.doViewsOverlap(CircleImagechild, view)) {
                minOffset = Math.min(minOffset, ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }
        Log.d(LOG_TAG,"minOffset = "+ String.valueOf(minOffset));
        return minOffset;
    }
}

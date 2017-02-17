package com.zwerks.androcoordinator;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FabAndSnackbarActivity extends AppCompatActivity {

    private Button mShowSnackbarButton;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_and_snackbar);

        // Assign the Coordinator Layour to a variable
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        // Assign showSnackBarButton to a variable
        mShowSnackbarButton = (Button) findViewById(R.id.showSnackbarButton);

        mShowSnackbarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(mCoordinatorLayout,
                        "This is a simple Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                // Custom action
                            }
                        }).show();
            }
        });
    }
}

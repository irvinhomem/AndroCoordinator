package com.zwerks.androcoordinator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void openFab_n_SnackBar(View view){
        //Do something in response to the attacehd BUTTON click
        Intent intent = new Intent(this, FabAndSnackbarActivity.class);
        startActivity(intent);
    }

    public void openFab_follows_Widget(View view){
        //Do something in response to the attacehd BUTTON click
        Intent intent = new Intent(this, FabFollowsWidgetActivity.class);
        startActivity(intent);
    }

    public void openCollapsingToolbar(View view){
        //Do something in response to the attacehd BUTTON click
        Intent intent = new Intent(this, CollapsingToolbarActivity.class);
        startActivity(intent);
    }

}

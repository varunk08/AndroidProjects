package com.astro.draw_to_screen;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private MyGLSurfaceView myGLGameView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGLGameView = new MyGLSurfaceView(this);
        myGLGameView.setRenderer(new MyGameRenderer(this));
        setContentView(myGLGameView);
        
       
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	myGLGameView.onResume();
    }
    
    protected void onPause()
    {
    	super.onPause();
    	myGLGameView.onPause();
    }

    
    
}

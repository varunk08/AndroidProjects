package com.astro.draw_to_screen;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGameRenderer implements Renderer{
	
	private MyTexture1 bg = new MyTexture1();
	private Context context;
	public MyGameRenderer(Context context){
		
		this.context = context;
	}
	@Override
	public void onDrawFrame(GL10 gl)
	{
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL10.GL_BLEND); 
	    gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE); 
	     bg.draw(gl);
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
		
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
		
		bg.loadTexture(gl,R.drawable.test_pic,this.context);
		
	}

}

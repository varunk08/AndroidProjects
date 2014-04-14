package com.astromaze.retroblaster;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.astromaze.retroblaster.Assets;

public class R_MainMenuScreen extends Screen {

	public R_MainMenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// Graphics g = game.getGraphics();
	     List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	     game.getInput().getKeyEvents();
	     
	     int len = touchEvents.size();
	        for(int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if(event.type == TouchEvent.TOUCH_UP) {
	                
	                if(inBounds(event, 64, 220, 192, 42) ) {
	                    game.setScreen(new R_GameScreen(game));
	                   
	                    return;
	                }
	                
	            }
	        }
	        

	}
	
	 private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
	        if(event.x > x && event.x < x + width - 1 && 
	           event.y > y && event.y < y + height - 1) 
	            return true;
	        else
	            return false;
	    }

	@Override
	public void present(float deltaTime) {
		 Graphics g = game.getGraphics();
	     g.drawPixmap(Assets.background, 0, 0);
	     //g.drawPixmap(Assets.logo, 32, 20);
	     g.drawText("RETRO BLASTER", 160, 100,Color.BLUE,50);
	     g.drawPixmap(Assets.mainMenu, 64, 220,0,0,192,43);
	     

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

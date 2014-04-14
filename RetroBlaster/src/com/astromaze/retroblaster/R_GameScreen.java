package com.astromaze.retroblaster;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.astromaze.retroblaster.Assets;

public class R_GameScreen extends Screen {

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }
    
    R_World r_world;
    GameState state = GameState.Ready;
    public static final int lcdPixelSize=16;
    
    
	public R_GameScreen(Game game) {
		super(game);
		r_world = new R_World();
		
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
        	updateGameOver(touchEvents);

	}

	private void updateReady(List<TouchEvent> touchEvents){
		if(touchEvents.size() > 0)
            state = GameState.Running;
	}
	
	private void updateRunning(List<TouchEvent> touchEvents,float deltaTime){
	    int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y < 64) {
                    state = GameState.Paused;
                    return;
                }
            }
//            System.out.println("Touch X: " + event.x);
//            System.out.println("Touch Y: " + event.y);
            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 64 && event.y > 416) {
                    r_world.heroBlock.moveLeft();
                    System.out.println("LEFT LEFT");
                }
                if(event.x > 256 && event.y > 416) {
                    r_world.heroBlock.moveRight();
                }
                
                
                if((event.x > 128 && event.x < 192) && event.y > 416){
                	System.out.println("FIRE FIRE FIRE");
                	r_world.fireBullet(r_world.heroBlock.x,r_world.heroBlock.y);
                	
                }
            }
        }
        
        r_world.update(deltaTime);
        if(r_world.gameOver){
        	state = GameState.GameOver;
        	r_world.score=0;
        }
		
	}
	private void updatePaused(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > 148 && event.y < 196) {
                    	r_world.score=0;
                        game.setScreen(new R_LoadingScreen(game));                        
                        return;
                    }
                }
            }
        }
	}
	
	private void updateGameOver(List<TouchEvent> touchEvents){
		 int len = touchEvents.size();
	        for(int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if(event.type == TouchEvent.TOUCH_UP) {
	                if(event.x >= 128 && event.x <= 192 &&
	                   event.y >= 200 && event.y <= 264) {
	                	
	                    game.setScreen(new R_MainMenuScreen(game));
	                    return;
	                }
	            }
	        }
	}
	
	
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(r_world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
        

	}
	
	private void drawWorld(R_World world){
		Graphics g = game.getGraphics();
		R_HeroBlock hero = world.heroBlock;
		int x = hero.x * lcdPixelSize;
		int y = hero.y * lcdPixelSize;
		
		g.drawPixmap(Assets.tail, x, y);
		for(int i=0;i<world.enemyList.size();i++){
			g.drawPixmap(Assets.tail, world.enemyList.get(i).x*lcdPixelSize, world.enemyList.get(i).y*lcdPixelSize);
		}
		if(r_world.bullet!=null) g.drawPixmap(Assets.tail, r_world.bullet.x*lcdPixelSize, r_world.bullet.y*lcdPixelSize);
	}
	
	private void drawReadyUI(){
		 Graphics g = game.getGraphics();
	     g.drawPixmap(Assets.ready, 47, 100);
	     g.drawLine(0, 416, 480, 416, Color.BLACK);
	}
	
	private void drawRunningUI(){
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
        g.drawLine(224, 0, 224, 416, Color.BLACK);//vertical
        g.drawText("Score", 272, 64, Color.BLACK,30);
        g.drawText(Integer.toString(r_world.score), 272, 96, Color.BLACK,30);
        g.drawText("Level", 272, 128, Color.BLACK, 30);
        g.drawText(Integer.toString(r_world.level), 272, 160, Color.BLACK,30);
        g.drawPixmap(Assets.buttons, 128, 416, 0, 128, 64, 64);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
	}
	private void drawPausedUI(){
		 Graphics g = game.getGraphics();
	     g.drawPixmap(Assets.pause, 80, 100);
	     g.drawLine(0, 416, 480, 416, Color.BLACK);
	}
	private void drawGameOverUI(){

        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	@Override
	public void pause() {
		

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void dispose() {
		

	}

}

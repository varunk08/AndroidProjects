package com.astromaze.retroblaster;

import java.util.ArrayList;
import java.util.List;

public class R_World {

	static final int PLAY_AREA_WIDTH = 14;
	static final int PLAY_AREA_HEIGHT = 26;
	R_HeroBlock heroBlock;
	public R_Bullet bullet=null;
	float tickTime=0;
	static float tick = 2.0f;
	public List<R_Enemy> enemyList = new ArrayList<R_Enemy>();
	public boolean gameOver = false;
	public int score = 0;
	public int level=1;
	
	public R_World(){
		heroBlock = new R_HeroBlock(5, 25);
		tick=2.0f;
		for(int i=0;i<PLAY_AREA_WIDTH;i++){
			for(int j=-3;j<2;j++){
				enemyList.add(new R_Enemy(i,j));
			}
		}
	}
	
	public void update(float deltaTime){
		
		tickTime += deltaTime;
		while(tickTime > tick){
			tickTime-=tick;
				advanceEnemies();
				
			}
			
		if(bullet!=null){
			bullet.moveBullet(deltaTime);
			if(checkCollision()){
				bullet = null;
			}
			
			else if(bullet!=null){
				if(bullet.y<0) bullet=null;
			}
			
		}
		
		if(enemyList.size()==0){
			level++;
			createNextWave();
		}
	}
	
	public boolean checkCollision(){
		for(int i=0;i<enemyList.size();i++){
			if(bullet.x==enemyList.get(i).x && bullet.y == enemyList.get(i).y){
				enemyList.remove(i);
				score++;
				System.out.println("Enemy removed,size: "+ enemyList.size());
				return true;
			}
			
		}
		return false;
	}
	
	private void createNextWave(){
		for(int i=0;i<PLAY_AREA_WIDTH;i++){
			for(int j=-3;j<2;j++){
				enemyList.add(new R_Enemy(i,j));
			}
		}
		if(tick-0.5!=0){
			tick-=0.5;
		}
		else{
			gameOver=true;
		}
	}
	
	public void advanceEnemies(){
		for(int i=0;i<enemyList.size();i++){
			if(enemyList.get(i).y++ == PLAY_AREA_HEIGHT-1 || (enemyList.get(i).x==heroBlock.x && enemyList.get(i).y==heroBlock.y)){
				gameOver = true;
			}
			
		}
	}
	public void fireBullet(int x, int y){
		if(bullet == null) bullet = new R_Bullet(x,y);
	}
}

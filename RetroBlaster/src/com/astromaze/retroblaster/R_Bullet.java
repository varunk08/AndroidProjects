package com.astromaze.retroblaster;

public class R_Bullet {

	public int x;
	public int y;
	public int velocity = 1;
	public R_Bullet(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	
	public void moveBullet(float deltaTime){
		//this.y -= velocity * ((int)Math.ceil(deltaTime * 50));
		this.y--;
		//System.out.println("Bullet y: " + this.y);
	}
}

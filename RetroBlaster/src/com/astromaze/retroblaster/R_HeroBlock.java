package com.astromaze.retroblaster;

public class R_HeroBlock {
public int x,y;

public R_HeroBlock(int x, int y){
	this.x=x;
	this.y=y;
}

public void moveLeft(){
	if(this.x>0) this.x--;
}

public void moveRight(){
	if(this.x<R_World.PLAY_AREA_WIDTH-1) this.x++;
}
}

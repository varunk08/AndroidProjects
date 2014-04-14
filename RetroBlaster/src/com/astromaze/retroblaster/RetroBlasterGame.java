package com.astromaze.retroblaster;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class RetroBlasterGame extends AndroidGame {
	@Override public Screen getStartScreen(){
		return new R_LoadingScreen(this);
	}

}

package main;


public enum gameStatus {
	PLAYING,
	MENU,
	SETTING;
	
	public static gameStatus gs = MENU;
	
	public static void setGameStatus(gameStatus status) {
		gs = status;
	}
}

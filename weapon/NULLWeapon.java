package com.tw.trainning.fightergame.weapon;

public class NULLWeapon extends Weapon{

    private static NULLWeapon instance = new NULLWeapon();
	
	public static NULLWeapon getInstance(){
		instance = (instance == null ? new NULLWeapon() : instance);
		return instance;
	}

	private NULLWeapon() {
		super("", 0);
	}

}

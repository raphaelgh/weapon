package com.tw.trainning.fightergame.player;

import java.io.PrintStream;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Soldier extends Player{

	private Weapon weapon;
	private final String role = "սʿ";
	
	public Soldier(String name, int attackValue, int blood, Weapon weapon, int protect) {
		super(name, attackValue, blood);
		this.weapon = weapon;
		this.protector = protect;
	}
	
	@Override
	protected String getRole(){
		return role;
	}
	
	@Override
	protected void beAttacked(Player playerB, PrintStream out){
		playerB.beAttacked(attackValue+weapon.attack(playerB), getRole()+name+
				weapon.name()+"������"+playerB.getRole()+playerB.name+","+weapon.bust(name), out);
	}

	@Override
	public String toString(){
		return super.toString()+seperator+"������:"+this.getProtector()+seperator+
		"\r\nװ����������Ϣ:"+this.weapon.toString();
	}
}

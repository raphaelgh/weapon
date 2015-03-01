package com.tw.trainning.fightergame.player;

import java.io.PrintStream;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Soldier extends Player{

	private Weapon weapon;
	private final String role = "战士";
	
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
				weapon.name()+"攻击了"+playerB.getRole()+playerB.name+","+weapon.bust(name), out);
	}

	@Override
	public String toString(){
		return super.toString()+seperator+"防御力:"+this.getProtector()+seperator+
		"\r\n装备的武器信息:"+this.weapon.toString();
	}
}

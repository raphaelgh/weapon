package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.NULLWeapon;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	//public static enum STATE{DEFEAT, FREEZE, FIRE, POISON, HALO, CANNOTATTACK, NORMAL};
	
	public static final String DEFEATED = "被打败了";
	public static final String NOTHING = "";

	protected final String name;     //not support rename
	protected final int attackValue; //not support upgrade
	protected int blood;
	protected String playerStatus="";
	private final String role = "普通人";
	protected Weapon affectWithWeapon = new NULLWeapon();
	
	public Player(String name, int attackValue, int blood) {
		this.name = (name != null ? name : "player");
		this.attackValue = attackValue;
		this.blood = blood;
	}

	public boolean canBeAttack() {
		return blood > 0;
	}
	
	protected String getRole(){
		return role;
	}

	public void attack(Player playerB, PrintStream out) {
		checkAffect(out);
		if(this.playerStatus.indexOf(",无法攻击")!= -1){
			this.affectWithWeapon.printStopAttackOnce(name, playerB.name, out);
			this.playerStatus = this.playerStatus.replace(",无法攻击", "");
			return;
		}
		playerB.beAttacked(attackValue, getRole()+name+"攻击了"+playerB.getRole()+playerB.name+",", out);
	}

	protected void beAttacked(int attackValue, String info, PrintStream out){
		blood = blood - attackValue;
		String attackStatus = this.affectWithWeapon.getWeaponAttributeName();
		attackStatus = ("".equals(attackStatus) ? attackStatus : name+attackStatus+",");
		out.println(info+name+"受到"+attackValue+"点伤害,"+attackStatus+
				name+"剩余生命:"+blood);
		playerStatus = blood <= 0 ? DEFEATED : playerStatus;
	}
	
	public String beAffectedByWeapon(Weapon weapon){
		weapon.accumulate(this.affectWithWeapon);
		this.affectWithWeapon = weapon;
		this.playerStatus = weapon.affectPlayerStatus(playerStatus);
		return playerStatus;
	}	
	
	protected void checkAffect(PrintStream out) {
		blood = affectWithWeapon.affectBlood(name, blood, playerStatus, out);
	}

	public void outputStatus(PrintStream out) {
		out.println(name + playerStatus);
	}
}

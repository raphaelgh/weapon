package com.tw.trainning.fightergame.player;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.NULLWeapon;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	protected static final String DEFEATED = "被打败了";
	protected static final String seperator = ",";
	protected final String name;     //not support rename
	protected final int attackValue; //not support upgrade
	protected int blood;
	protected String playerStatus="";
	private final String role = "普通人";
	protected Weapon affectWithWeapon = NULLWeapon.getInstance();
	
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
	
	public int beAffectedByWeapon(Weapon weapon){
		int intensifiedWeaponAttackValue = weapon.accumulate(this.affectWithWeapon);
		this.affectWithWeapon = weapon;
		this.playerStatus = weapon.affectPlayerStatus(playerStatus);
		return intensifiedWeaponAttackValue;
	}	
	
	protected void checkAffect(PrintStream out) {
		blood = affectWithWeapon.affectBlood(name, blood, playerStatus, out);
	}

	public void outputStatus(PrintStream out) {
		out.println(name + playerStatus);
	}
	
	@Override
	public String toString(){
		return "名字:"+name+seperator+"角色:"+this.getRole()+seperator+"攻击力:"+attackValue;
	}
}

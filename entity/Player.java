package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.NULLWeapon;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	public static final String DEFEATED = "�������";
	public static final String NOTHING = "";

	protected final String name;     //not support rename
	protected final int attackValue; //not support upgrade
	protected int blood;
	protected String playerStatus="";
	protected String attackStatus = "";
	private final String role = "��ͨ��";
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
		if(this.playerStatus.indexOf(",�޷�����")!= -1){
			this.affectWithWeapon.printStopAttackOnce(name, playerB.name, out);
			this.playerStatus = this.playerStatus.replace(",�޷�����", "");
			return;
		}
		playerB.beAttacked(attackValue, getRole()+name+"������"+playerB.getRole()+playerB.name+",", out);
	}

	protected void beAttacked(int attackValue, String info, PrintStream out){
		blood = blood - attackValue;
		out.println(info+name+"�ܵ�"+attackValue+"���˺�,"+attackStatus+
				name+"ʣ������:"+blood);
		playerStatus = blood <= 0 ? DEFEATED : playerStatus;
	}
	
	public void beAffectedByWeapon(Weapon weapon){
		this.affectWithWeapon = weapon;
		this.playerStatus = weapon.affectPlayerStatus(playerStatus);
		if(attackStatus.indexOf(name) != -1){
			attackStatus = attackStatus.substring(name.length(), attackStatus.length() -1);
		}
		this.attackStatus = weapon.affectAttackStatus(name, attackStatus);
	}
	
	protected void checkAffect(PrintStream out) {
		blood = affectWithWeapon.affectBlood(name, blood, playerStatus, out);
	}

	public void outputStatus(PrintStream out) {
		out.println(name + playerStatus);
	}
	
	public String playerStatus(){
		return this.playerStatus;
	}
	
	
	
}

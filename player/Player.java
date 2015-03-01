package com.tw.trainning.fightergame.player;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.NULLWeapon;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	protected static final String DEFEATED = "�������";
	protected static final String seperator = ",";
	protected final String name;     //not support rename
	protected final int attackValue; //not support upgrade
	protected int blood;
	protected String playerStatus="";
	private final String role = "��ͨ��";
	protected Weapon affectWithWeapon = NULLWeapon.getInstance();
	protected int protector;
	
	public Player(String name, int attackValue, int blood) {
		this.name = (name != null ? name : "player");
		this.attackValue = attackValue;
		this.blood = blood;
		this.protector = 0;
	}
	
	protected int getProtector(){
		return this.protector;
	}

	public boolean isLive(PrintStream out) {
		return checkAffect(out) > 0;
//		return blood > 0;
	}
	
	protected String getRole(){
		return role;
	}
	
	protected boolean canBeAttack(){
		return this.playerStatus.indexOf(",�޷�����") == -1;
	}

	public void attack(Player playerB, PrintStream out) {
		if(!canBeAttack()){
			this.affectWithWeapon.printStopAttackOnce(name, playerB.name, out);
			this.playerStatus = this.playerStatus.replace(",�޷�����", "");
			return;
		}
		this.beAttacked(playerB, out);
	}
	
	protected void beAttacked(Player playerB, PrintStream out){
		playerB.beAttacked(attackValue, getRole()+name+"������"+playerB.getRole()+playerB.name+",", out);
	}

	protected void beAttacked(int attackValue, String info, PrintStream out){
		int value = (attackValue - this.getProtector()) < 0 ? 0 : (attackValue - this.getProtector());
		blood = blood - value;
		String attackStatus = this.affectWithWeapon.getWeaponAttributeName();
		attackStatus = ("".equals(attackStatus) ? attackStatus : name+attackStatus+",");
		out.println(info+name+"�ܵ�"+value+"���˺�,"+attackStatus+
				name+"ʣ������:"+blood);
		playerStatus = blood <= 0 ? DEFEATED : playerStatus;
	}
	
	public int beAffectedByWeapon(Weapon weapon){
		int intensifiedWeaponAttackValue = weapon.accumulate(this.affectWithWeapon);
		this.affectWithWeapon = weapon;
		this.playerStatus = weapon.affectPlayerStatus(playerStatus);
		return intensifiedWeaponAttackValue;
	}	
	
	protected int checkAffect(PrintStream out) {
		blood = affectWithWeapon.affectBlood(name, blood, playerStatus, out);
		playerStatus = blood <= 0 ? DEFEATED : playerStatus;
		return blood;
	}

	public void outputStatus(PrintStream out) {
		out.println(name + playerStatus);
	}
	
	@Override
	public String toString(){
		return "����:"+name+seperator+"��ɫ:"+this.getRole()+seperator+"������:"+attackValue;
	}
}

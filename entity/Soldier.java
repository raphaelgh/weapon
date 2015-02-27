package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Soldier extends Player{

	private Weapon weapon;
	private int protect;
	private final String role = "սʿ";
	
	public Soldier(String name, int attackValue, int blood, Weapon weapon, int protect) {
		super(name, attackValue, blood);
		this.weapon = weapon;
		this.protect = protect;
	}
	
	@Override
	protected String getRole(){
		return role;
	}
	
	@Override
	public void attack(Player playerB, PrintStream out) {
		checkAffect(out);
		if(this.playerStatus.indexOf(",�޷�����")!= -1){
			this.affectWithWeapon.printStopAttackOnce(name, playerB.name, out);
			this.playerStatus = this.playerStatus.replace(",�޷�����", "");
			return;
		}
		playerB.beAttacked(attackValue+weapon.attack(playerB), getRole()+name+
				weapon.name()+"������"+playerB.getRole()+playerB.name+","+weapon.bust(name), out);
	}
	
	@Override
	protected void beAttacked(int attackValue, String info, PrintStream out){
		blood = blood - (attackValue - protect);
		String attackStatus = this.affectWithWeapon.getWeaponAttributeName();
		attackStatus = ("".equals(attackStatus) ? attackStatus : name+attackStatus+",");
		out.println(info+name+"�ܵ�"+(attackValue - protect)+"���˺�,"+attackStatus+
				name+"ʣ������:"+blood);
		playerStatus = blood <= 0 ? DEFEATED : playerStatus;
	}


}

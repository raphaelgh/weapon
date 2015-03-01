package com.tw.trainning.fightergame;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.player.Player;
import com.tw.trainning.fightergame.player.Soldier;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponRespository;

public class Game {

	Player playerA;
	Player playerB;
	PrintStream out;
	
	public Game(Player playerA, Player playerB, PrintStream print) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.out = print;
	}

	public void start(){
		while(playerA.isLive(out)){
			playerA.attack(playerB, out);
			if(!playerB.isLive(out)){
				break;
			}
			playerB.attack(playerA, out);
		}
		Player player = playerA.isLive(out) ? playerB : playerA;
		player.outputStatus(out);
	}
	
	private void start(InputStream in){
		int i = 1;
		System.out.println("--------��"+i+"�غ�-------------");
		while(playerA.isLive(out)){						
			playerA.attack(playerB, out);
			wait123(2);
			if(!playerB.isLive(out)){
				break;
			}
			playerB.attack(playerA, out);
			//continue123(in);
			wait123(2);		
			i++;
			System.out.println();
			System.out.println("--------��"+i+"�غ�-------------");
		}
		Player player = playerA.isLive(out) ? playerB : playerA;
		player.outputStatus(out);
		System.out.println("����bye������Ϸ,���س�����Ϸ����");
	}
	
	private void wait123(int seconds){
		try {
			Thread.sleep(seconds*1000);			
		} catch (InterruptedException e) {

		}
	}
	
//	private void continue123(InputStream in){
//		System.err.println("���س�������...");
//		try {
//			in.read();
//		} catch (IOException e) {				
//		}
//	}
	
	public static void main(String[] args){		
		Random random = new Random();
		WeaponRespository weaponRes = new WeaponRespository(random);	
		Game game;
		System.out.println("���س�����Ϸ��ʼ");
		while(!isBye()){
			game = gameGenerator(random, weaponRes);
			game.start(System.in);
		}		
	}
	
	private static boolean isBye(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try{
			return "bye".equalsIgnoreCase(in.readLine());
		}
		catch(Exception e){
			return true;
		}
	}
	
	private static Game gameGenerator(Random random, WeaponRespository weaponRes){
		String name;
		int attackValue;
		String[] inputs = getInput();
		name = inputs[0];
		attackValue = Integer.parseInt(inputs[1]);		
		Player myself;
		Player other;
		myself = generateSelf(random, weaponRes, name, attackValue);
		other = generateCompetor(random, 10, weaponRes);
		return new Game(myself, other, System.out);		
	}
	
	private static Player generateSelf(Random random, WeaponRespository weaponRes, String name, int attackValue){		
		Weapon weapon;
		Player myself;
		weapon = weaponRes.fetchWeapon(random.nextInt(weaponRes.size()));
		System.out.println("ϵͳΪ��ѡ���������:\r\n"+weapon.toString());
		myself = new Soldier(name, attackValue, 100, weapon, 10);
		System.out.println("�����������룬������Ϣ����:\r\n"+myself.toString());
		return myself;
	}
	
	private static String[] getInput(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String name;
		int attackValue;
		while(true){
			try {
				System.out.println("��������������:");
				name = in.readLine();
				name = (name == null || "".equals(name) ? "��С��" : name);
				System.out.println("���������Ĺ�����:");
				try{
					attackValue = Integer.parseInt(in.readLine());					
				}
				catch(Exception e){
					attackValue = 5;
				}
				break;
			} catch (Exception e) {
				System.err.println("�����������������룡");
			}
		}
		String[] strs = {name, String.valueOf(attackValue)};
		return strs;
	}

	private static Player generateCompetor(Random random, int attackValue, WeaponRespository weaponRes) {
		Weapon weapon = weaponRes.fetchWeapon(random.nextInt(weaponRes.size()));
		String name = "�����";
		int value = random.nextInt(attackValue) + 1;
		Player player = new Soldier(name, value, 100, weapon, 10);
		System.out.println("���Ķ�����Ϣ����:\r\n"+player.toString());
		return player;
	}
}

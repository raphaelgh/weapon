package com.tw.trainning.fightergame.testcase;

import java.io.PrintStream;
import java.util.Random;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.tw.trainning.fightergame.Game;
import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.entity.Soldier;
import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponWithFreeze;
import com.tw.trainning.fightergame.weapon.WeaponWithPoison;


public class GameTest {
	
	PrintStream out = mock(PrintStream.class);
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void should_two_player_fight_together_and_output_result(){
		//PrintStream out = mock(PrintStream.class);
		Player playerA = new Player("����", 20, 100);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("�����������");
	}
	
//	@Test
//	public void should_two_player_fight_together_and_output_process_and_result(){
//		//PrintStream out = mock(PrintStream.class);
//		Player playerA = new Player("����", 40, 100);
//		Player playerB = new Player("����", 30, 100);
//		Game game = new Game(playerA, playerB, out);
//		game.start();
//		verify(out).println("���Ĺ���������,�����ܵ�40���˺�,����ʣ������:60");
//		verify(out).println("��������������,�����ܵ�30���˺�,����ʣ������:70");
//		verify(out).println("���Ĺ���������,�����ܵ�40���˺�,����ʣ������:20");
//		verify(out).println("��������������,�����ܵ�30���˺�,����ʣ������:40");
//		verify(out).println("���Ĺ���������,�����ܵ�40���˺�,����ʣ������:-20");
//		verify(out).println("�����������");
//	}
	
	@Test
	public void should_soldier_use_weapon_fight_with_person(){
		Weapon weapon = new Weapon("����ľ��", 10);
		Soldier playerA = new Soldier("����",40,100,weapon,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ����������ľ����������ͨ������,�����ܵ�50���˺�,����ʣ������:50");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		verify(out).println("սʿ����������ľ����������ͨ������,�����ܵ�50���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_person_fight_with_person(){
		Player playerA = new Player("����",40,100);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("��ͨ�����Ĺ�������ͨ������,�����ܵ�40���˺�,����ʣ������:60");
		verify(out).println("��ͨ��������������ͨ������,�����ܵ�30���˺�,����ʣ������:70");
		verify(out).println("��ͨ�����Ĺ�������ͨ������,�����ܵ�40���˺�,����ʣ������:20");
		verify(out).println("��ͨ��������������ͨ������,�����ܵ�30���˺�,����ʣ������:40");
		verify(out).println("��ͨ�����Ĺ�������ͨ������,�����ܵ�40���˺�,����ʣ������:-20");
		verify(out).println("�����������");
	}
}

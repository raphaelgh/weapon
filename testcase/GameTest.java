package com.tw.trainning.fightergame.testcase;

import java.io.PrintStream;
import java.util.Random;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.tw.trainning.fightergame.Game;
import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.entity.Soldier;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponRespository;
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
	
	@Test
	public void should_soldier_fight_with_soldier(){
		Weapon stick = new Weapon("����ľ��", 10);
		Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",40,100,stick,20);
		Soldier playerB = new Soldier("����", 30, 100, sword, 20);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ����������ľ��������սʿ����,�����ܵ�30���˺�,����ʣ������:70");
		verify(out).println("սʿ����������������սʿ����,�����ܵ�50���˺�,����ʣ������:50");
		verify(out).println("սʿ����������ľ��������սʿ����,�����ܵ�30���˺�,����ʣ������:40");
		verify(out).println("սʿ����������������սʿ����,�����ܵ�50���˺�,����ʣ������:0");
		verify(out).println("���ı������");
	}
	
	@Test
	public void should_weapon_respository_work_well(){
		WeaponRespository.listWeapon(out);
		verify(out).print("����:����ľ��,������:20\r\n����:����,������:50\r\n" +
				"����:��ì,������:40\r\n����:����,������:30\r\n");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_affect_one_time_fight_with_person(){
		Weapon poison = new WeaponWithPoison("���ʶ���", 10, 1);
		//Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",40,100,poison,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�50���˺�,�����ж���,����ʣ������:50");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:48");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_affect_one_time_and_with_random_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("���ʶ���", 10, 1, random);
		//Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",40,100,poison,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�50���˺�,�����ж���,����ʣ������:50");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:48");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�50���˺�,����ʣ������:-2");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_affect_more_than_one_time_and_with_random_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("���ʶ���", 10, 2, random);
		//Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",20,100,poison,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�30���˺�,�����ж���,����ʣ������:70");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:68");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�30���˺�,����ʣ������:38");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:36");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�30���˺�,����ʣ������:6");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�30���˺�,����ʣ������:-24");
		verify(out).println("�����������");
	}
}

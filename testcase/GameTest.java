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
import com.tw.trainning.fightergame.weapon.WeaponWithBust;
import com.tw.trainning.fightergame.weapon.WeaponWithFire;
import com.tw.trainning.fightergame.weapon.WeaponWithFreeze;
import com.tw.trainning.fightergame.weapon.WeaponWithHalo;
import com.tw.trainning.fightergame.weapon.WeaponWithPoison;


public class GameTest {
	
	PrintStream out = mock(PrintStream.class);
	Random random;
	
	@Before
	public void setup(){
		random = mock(Random.class);
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
		when(random.nextBoolean())
		.thenReturn(true);
		Weapon poison = new WeaponWithPoison("���ʶ���", 10, 1, random);
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
		when(random.nextBoolean())
		.thenReturn(true)
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
		//verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:4");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�30���˺�,����ʣ������:-24");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_effect_accumulation_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("���ʶ���", 10, 2, random);
		//Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",5,100,poison,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�15���˺�,�����ж���,����ʣ������:85");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:83");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�15���˺�,�������ж���,����ʣ������:68");
		verify(out).println("�����ܵ�4�㶾���˺�,����ʣ������:64");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�15���˺�,����ʣ������:49");
		verify(out).println("�����ܵ�4�㶾���˺�,����ʣ������:45");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�15���˺�,����ʣ������:30");
		verify(out).println("�����ܵ�4�㶾���˺�,����ʣ������:26");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�15���˺�,����ʣ������:11");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�15���˺�,����ʣ������:-4");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_effect_accumulation_twice_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("���ʶ���", 5, 2, random);
		//Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",5,100,poison,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,�����ж���,����ʣ������:90");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,�������ж���,����ʣ������:78");
		verify(out).println("�����ܵ�4�㶾���˺�,����ʣ������:74");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,����ʣ������:64");
		verify(out).println("�����ܵ�4�㶾���˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out).println("�����ܵ�4�㶾���˺�,����ʣ������:46");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,����ʣ������:36");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,�����ж���,����ʣ������:26");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:24");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,����ʣ������:14");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:12");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,����ʣ������:2");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:20");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,����ʣ������:-8");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_fire_and_effect_accumulation_twice_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon fire = new WeaponWithFire("���潣", 5, 2, random);
		Soldier playerA = new Soldier("����",5,100,fire,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,�����Ż���,����ʣ������:90");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,�������Ż���,����ʣ������:78");
		verify(out).println("�����ܵ�4�������˺�,����ʣ������:74");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:64");
		verify(out).println("�����ܵ�4�������˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out).println("�����ܵ�4�������˺�,����ʣ������:46");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:36");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,�����Ż���,����ʣ������:26");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:24");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:14");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:12");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:2");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:20");
		
		verify(out).println("սʿ�����û��潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:-8");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Weapon freeze = new WeaponWithFreeze("������", 20, 2, random);
		Soldier playerA = new Soldier("����",10,100,freeze,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����������,����ʣ������:70");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����ʣ������:40");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����ʣ������:10");		
		verify(out).println("��������ֱ����,û�л�������");
		verify(out, never()).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����ʣ������:-20");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_effect_accumulation_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		Weapon freeze = new WeaponWithFreeze("������", 5, 2, random);
		Soldier playerA = new Soldier("����",5,100,freeze,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,�����ֶ�����,����ʣ������:80");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:70");		
		verify(out, times(2)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out, times(2)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:40");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:20");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:10");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_effect_accumulation_twice_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon freeze = new WeaponWithFreeze("������", 5, 2, random);
		Soldier playerA = new Soldier("����",5,100,freeze,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,�����ֶ�����,����ʣ������:80");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:70");		
		verify(out, times(3)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out, times(3)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:40");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:20");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:10");
		verify(out, times(3)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Weapon halo = new WeaponWithHalo("�δ�", 20, 2, random);
		Soldier playerA = new Soldier("����",10,100,halo,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�30���˺�,�����ε���,����ʣ������:70");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�30���˺�,����ʣ������:40");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:0��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�30���˺�,����ʣ������:10");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�30���˺�,����ʣ������:-20");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_and_effect_accumulation_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false);
		Weapon halo = new WeaponWithHalo("�δ�", 10, 2, random);
		Soldier playerA = new Soldier("����",10,100,halo,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�20���˺�,�����ε���,����ʣ������:80");
		verify(out, times(2)).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�20���˺�,�������ε���,����ʣ������:60");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:2��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�20���˺�,����ʣ������:40");		
		verify(out, times(2)).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�20���˺�,����ʣ������:20");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:0��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�20���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_and_effect_accumulation_twice_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		Weapon halo = new WeaponWithHalo("�δ�", 5, 2, random);
		Soldier playerA = new Soldier("����",5,100,halo,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,�����ε���,����ʣ������:90");
		verify(out, times(3)).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,�������ε���,����ʣ������:80");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:2��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:70");		
		verify(out, times(3)).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:60");
		verify(out, times(2)).println("�����ε���,�޷�����,ѣ�λ�ʣ:0��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,�����ε���,����ʣ������:40");
		verify(out, times(3)).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:30");
		verify(out, times(2)).println("�����ε���,�޷�����,ѣ�λ�ʣ:0��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:20");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:10");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,����ʣ������:0");		
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Weapon bust = new WeaponWithBust("����", 20, 2, random);
		Soldier playerA = new Soldier("����",10,100,bust,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�70���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ������������������ͨ������,�����ܵ�30���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_effect_accumulation_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false);
		Weapon bust = new WeaponWithBust("����", 20, 2, random);
		Soldier playerA = new Soldier("����",10,100,bust,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�70���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�70���˺�,����ʣ������:-40");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_effect_accumulation_twice_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon bust = new WeaponWithBust("����", 5, 2, random);
		Soldier playerA = new Soldier("����",5,100,bust,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�20���˺�,����ʣ������:80");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�20���˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ������������������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ������������������ͨ������,�����ܵ�10���˺�,����ʣ������:40");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ������������������ͨ������,�����ܵ�10���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�20���˺�,����ʣ������:10");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ������������������ͨ������,�����ܵ�10���˺�,����ʣ������:0");
		
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_fight_with_soldier_use_weapon_with_halo(){
		Random poisonRandom = mock(Random.class);
		when(poisonRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Random haloRandom = mock(Random.class);
		when(haloRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Weapon poison = new WeaponWithPoison("����", 15, 2, poisonRandom);
		Weapon halo = new WeaponWithHalo("�δ�", 20, 2, haloRandom);
		Soldier playerA = new Soldier("����",10,100,poison,10);
		Soldier playerB = new Soldier("����", 20, 100, halo, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ö���������սʿ����,�����ܵ�15���˺�,�����ж���,����ʣ������:85");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:83");
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�30���˺�,�����ε���,����ʣ������:70");
		
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:81");
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�30���˺�,����ʣ������:40");
		
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:0��");
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�30���˺�,����ʣ������:10");
		
		verify(out).println("սʿ�����ö���������սʿ����,�����ܵ�15���˺�,����ʣ������:66");
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�30���˺�,����ʣ������:-20");
		
		verify(out).println("���ı������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_fire_fight_with_soldier_use_weapon_with_freeze(){
		Random fireRandom = mock(Random.class);
		when(fireRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Random freezeRandom = mock(Random.class);
		when(freezeRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Weapon fire = new WeaponWithFire("���潣", 15, 3, fireRandom);
		Weapon freeze = new WeaponWithFreeze("����", 20, 2, freezeRandom);
		Soldier playerA = new Soldier("����",10,100,fire,10);
		Soldier playerB = new Soldier("����", 20, 100, freeze, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����û��潣������սʿ����,�����ܵ�15���˺�,�����Ż���,����ʣ������:85");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:83");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,���Ķ�����,����ʣ������:70");
		
		verify(out).println("սʿ�����û��潣������սʿ����,�����ܵ�15���˺�,����ʣ������:68");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:66");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�����û��潣������սʿ����,�����ܵ�15���˺�,����ʣ������:51");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:49");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,����ʣ������:10");
		
		verify(out).println("���Ķ���ֱ����,û�л�������");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,����ʣ������:-20");
		
		verify(out).println("���ı������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_fight_with_soldier_use_weapon_with_freeze(){
		Random haloRandom = mock(Random.class);
		when(haloRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Random freezeRandom = mock(Random.class);
		when(freezeRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Weapon halo = new WeaponWithHalo("�δ�", 15, 3, haloRandom);
		Weapon freeze = new WeaponWithFreeze("����", 20, 2, freezeRandom);
		Soldier playerA = new Soldier("����",10,100,halo,10);
		Soldier playerB = new Soldier("����", 20, 100, freeze, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�15���˺�,�����ε���,����ʣ������:85");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�15���˺�,����ʣ������:70");
		verify(out).println("�����ε���,�޷�����,ѣ�λ�ʣ:0��");

		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�15���˺�,����ʣ������:55");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,���Ķ�����,����ʣ������:70");
		
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�15���˺�,����ʣ������:40");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�������δ�������սʿ����,�����ܵ�15���˺�,����ʣ������:25");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,����ʣ������:10");
		
		verify(out).println("���Ķ���ֱ����,û�л�������");
		verify(out).println("սʿ�����ñ���������սʿ����,�����ܵ�30���˺�,����ʣ������:-20");
		
		verify(out).println("���ı������");
	}
}

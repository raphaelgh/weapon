package com.tw.trainning.fightergame.testcase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.tw.trainning.fightergame.Game;
import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.entity.Soldier;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponRespository;
import com.tw.trainning.fightergame.weapon.attribute.Attribute;
import com.tw.trainning.fightergame.weapon.attribute.Bust;
import com.tw.trainning.fightergame.weapon.attribute.Fire;
import com.tw.trainning.fightergame.weapon.attribute.Freeze;
import com.tw.trainning.fightergame.weapon.attribute.Halo;
import com.tw.trainning.fightergame.weapon.attribute.Poison;


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
	
	//*-------------------------first problem finished------------------------------------------------
	
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
	
	//*-------------------------second problem finished------------------------------------------------
	
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
	public void should_soldier_use_weapon_fight_with_soldier_use_protect(){
		
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
	
	//*---------------------------third problem finished----------------------------------------------
	
	@Test
	public void should_weapon_respository_work_well(){
		when(random.nextInt(20))
		.thenReturn(1)
		.thenReturn(1);
		
		when(random.nextInt(7))
		.thenReturn(1);
		
		when(random.nextInt(5))
		.thenReturn(1)
		.thenReturn(1);
		
		when(random.nextInt(3))
		.thenReturn(1)
		.thenReturn(1);
		
		WeaponRespository weaponRes = new WeaponRespository(random);
		weaponRes.listWeapon(out);
		verify(out).print("����:����@��,������:2\r\n����1:�˺�:����,�˺�ֵ:2,�����ִ�:1,��������:false\r\n\r\n");
	}
	
	//*---------------------------weapon respository finished----------------------------------------------
	
	@Test
	public void should_soldier_use_weapon_with_poison_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute poison = new Poison("����", 2, 2);
		Weapon poisonArrow = new Weapon("���ʶ���", 10, poison, random);
		//Weapon sword = new Weapon("����", 40);
		Soldier playerA = new Soldier("����",40,100,poisonArrow,20);
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
	public void should_soldier_use_weapon_with_poison_and_accumulation_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(0)
		.thenReturn(1);
		Attribute poison = new Poison("����", 2, 2);
		Weapon poisonArrow = new Weapon("���ʶ���", 5, poison, random);
		Soldier playerA = new Soldier("����",5,100,poisonArrow,20);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,�����ж���,����ʣ������:90");
		verify(out).println("�����ܵ�2�㶾���˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������ʶ�����������ͨ������,�����ܵ�10���˺�,�����ж���,����ʣ������:78");
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
	public void should_soldier_use_weapon_with_freeze_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute freeze = new Freeze("����", 2);
		Weapon freezeWeapon = new Weapon("������", 20, freeze, random);
		Soldier playerA = new Soldier("����",10,100,freezeWeapon,10);
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
	public void should_soldier_use_weapon_with_freeze_and_accumulation_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(0)
		.thenReturn(1);
		Attribute freeze = new Freeze("����", 2);
		Weapon freezeWeapon = new Weapon("������", 5, freeze, random);
		Soldier playerA = new Soldier("����",5,100,freezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:80");
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
	public void should_soldier_use_weapon_with_halo_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute halo = new Halo("�δ�", 2);
		Weapon haloHammer = new Weapon("�δ�", 20, halo, random);
		Soldier playerA = new Soldier("����",10,100,haloHammer,10);
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
	public void should_soldier_use_weapon_with_halo_and_accumulation_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		Attribute halo = new Halo("�δ�", 2);
		Weapon haloHammer = new Weapon("�δ�", 5, halo, random);
		Soldier playerA = new Soldier("����",5,100,haloHammer,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,�����ε���,����ʣ������:90");
		verify(out, times(3)).println("�����ε���,�޷�����,ѣ�λ�ʣ:1��");
		
		verify(out).println("սʿ�������δ���������ͨ������,�����ܵ�10���˺�,�����ε���,����ʣ������:80");
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
	public void should_soldier_use_weapon_with_bust_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute bust = new Bust();
		Weapon bustWeapon = new Weapon("����", 20, bust, random);
		Soldier playerA = new Soldier("����",10,100,bustWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ������������������ͨ������,���ķ�����ȫ��һ��,�����ܵ�70���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ������������������ͨ������,�����ܵ�30���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_accumulation_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(0)
		.thenReturn(1);
		Attribute bust = new Bust();
		Weapon bustWeapon = new Weapon("����", 5, bust, random);
		Soldier playerA = new Soldier("����",5,100,bustWeapon,10);
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
	public void should_soldier_use_weapon_with_fire_fight_with_soldier_use_weapon_with_freeze(){
		Random fireRandom = mock(Random.class);
		when(fireRandom.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		
		Random freezeRandom = mock(Random.class);
		when(freezeRandom.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		
		Attribute fire = new Fire("����", 3);
		Weapon fireWeapon = new Weapon("���潣", 15, fire, fireRandom);
		Attribute feeze = new Freeze("����", 2);
		Weapon freezeWeapon = new Weapon("����", 20, feeze, freezeRandom);
		Soldier playerA = new Soldier("����",10,100,fireWeapon,10);
		Soldier playerB = new Soldier("����", 20, 100, freezeWeapon, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����û��潣������սʿ����,�����ܵ�15���˺�,����������,����ʣ������:85");
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
	public void should_soldier_use_weapon_with_poison_fight_with_soldier_use_weapon_with_halo(){
		Random poisonRandom = mock(Random.class);
		when(poisonRandom.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		
		Random haloRandom = mock(Random.class);
		when(haloRandom.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		
		Attribute posion = new Poison("����", 2);
		Weapon poisonWeapon = new Weapon("����", 15, posion, poisonRandom);
		Attribute halo = new Halo("ѣ��", 2);
		Weapon haloWeapon = new Weapon("�δ�", 20, halo, haloRandom);
		Soldier playerA = new Soldier("����",10,100,poisonWeapon,10);
		Soldier playerB = new Soldier("����", 20, 100, haloWeapon, 10);
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
	public void should_soldier_use_weapon_with_halo_fight_with_soldier_use_weapon_with_freeze(){
		Random haloRandom = mock(Random.class);
		when(haloRandom.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		
		Random freezeRandom = mock(Random.class);
		when(freezeRandom.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		
		Attribute halo = new Halo("ѣ��", 3);
		Weapon haloWeapon = new Weapon("�δ�", 15, halo, haloRandom);
		Attribute freeze = new Freeze("����", 2);
		Weapon freezeWeapon = new Weapon("����", 20, freeze, freezeRandom);
		Soldier playerA = new Soldier("����",10,100,haloWeapon,10);
		Soldier playerB = new Soldier("����", 20, 100, freezeWeapon, 10);
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
	
	//*----------------------fourth problem step one finished-----------------------------------------
	
	@Test
	public void should_weapon_with_two_different_attributes_and_start_one_by_one(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(1)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(4);
		Attribute freeze = new Freeze("����", 2);
		Attribute fire = new Fire("����", 3);
		Weapon firePlusFreezeWeapon = new Weapon("���溮����", 5, fire, random);
		firePlusFreezeWeapon.addAttribute(freeze);
		Soldier playerA = new Soldier("����",5,100,firePlusFreezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:78");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:76");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:66");		
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:64");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:54");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:44");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");		
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:34");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:24");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:14");
		verify(out).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:4");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:20");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:-6");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_weapon_with_two_different_attributes_and_cannot_accumlate_and_player_only_at_one_status(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(3)
		.thenReturn(2)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(4);
		Attribute freeze = new Freeze("����", 2);
		Attribute fire = new Fire("����", 3);
		Weapon firePlusFreezeWeapon = new Weapon("���溮����", 5, fire, random);
		firePlusFreezeWeapon.addAttribute(freeze);
		Soldier playerA = new Soldier("����",5,100,firePlusFreezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:78");
		verify(out, never()).println("�����ܵ�2�������˺�,����ʣ������:86");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:68");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:58");
		verify(out).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:48");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");		
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:38");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:28");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:18");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:8");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:20");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����ʣ������:-2");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_weapon_with_two_same_attributes_and_accumulate(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(3)
		.thenReturn(2)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(4);
		Attribute smallFire = new Fire("����", 2, 2);
		Attribute bigFire = new Fire("����", 3, 5);
		Weapon fireWeapon = new Weapon("���潣", 5, smallFire, random);
		fireWeapon.addAttribute(bigFire);
		Soldier playerA = new Soldier("����",5,100,fireWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:78");
		verify(out).println("�����ܵ�7�������˺�,����ʣ������:71");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:61");	
		verify(out).println("�����ܵ�7�������˺�,����ʣ������:54");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:44");
		verify(out).println("�����ܵ�7�������˺�,����ʣ������:37");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:27");
		verify(out).println("�����ܵ�7�������˺�,����ʣ������:20");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");		
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:10");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_weapon_with_one_attribute_and_bust_and_bust_not_influence_player_status(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(3)
		.thenReturn(2)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(4);
		Attribute smallFire = new Fire("����", 2, 2);
		Attribute bust = new Bust();
		Weapon fireWeaponWithBust = new Weapon("���潣", 5, smallFire, random);
		fireWeaponWithBust.addAttribute(bust);
		Soldier playerA = new Soldier("����",5,100,fireWeaponWithBust,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������潣��������ͨ������,���ķ�����ȫ��һ��,�����ܵ�20���˺�,����ʣ������:68");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:66");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:56");	
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:46");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:36");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");		
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:26");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:16");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:6");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:20");
		
		verify(out).println("սʿ���������潣��������ͨ������,�����ܵ�10���˺�,����ʣ������:-4");		
		verify(out).println("�����������");
	}
	
	//*----------------------fourth problem step two finished-----------------------------------------
}

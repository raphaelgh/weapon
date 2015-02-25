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
import com.tw.trainning.fightergame.weapon.RefactorWeapon;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.attribute.Attribute;
import com.tw.trainning.fightergame.weapon.attribute.Fire;
import com.tw.trainning.fightergame.weapon.attribute.Freeze;


public class RefactorTest {
	
	PrintStream out = mock(PrintStream.class);
	Random random;
	
	@Before
	public void setup(){
		random = mock(Random.class);
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute freeze = new Freeze("������", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("������", 20, freeze, random);
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
	public void should_soldier_use_weapon_with_freeze_and_effect_accumulation_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1);
		Attribute freeze = new Freeze("������", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("������", 5, freeze, random);
		Soldier playerA = new Soldier("����",5,100,freezeWeapon,10);
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
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(1)
		.thenReturn(0)
		.thenReturn(1);
		Attribute freeze = new Freeze("������", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("������", 5, freeze, random);
		Soldier playerA = new Soldier("����",5,100,freezeWeapon,10);
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
	public void should_weapon_first_fire_and_freeze_when_fire_finish(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(1)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(4);
		Attribute freeze = new Freeze("������", 2, true);
		Attribute fire = new Fire("����", 3, true);
		RefactorWeapon firePlusFreezeWeapon = new RefactorWeapon("���溮����", 5, fire, random);
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
	public void should_weapon_first_fire_and_freeze_when_fire_not_finish(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1)
		.thenReturn(3)
		.thenReturn(2)
		.thenReturn(2)
		.thenReturn(3)
		.thenReturn(4);
		Attribute freeze = new Freeze("����", 2, true);
		Attribute fire = new Fire("����", 3, true);
		RefactorWeapon firePlusFreezeWeapon = new RefactorWeapon("���溮����", 5, fire, random);
		firePlusFreezeWeapon.addAttribute(freeze);
		Soldier playerA = new Soldier("����",5,100,firePlusFreezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("�����ܵ�2�������˺�,����ʣ������:88");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ���������溮������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:78");
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
}

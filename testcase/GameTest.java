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
		Player playerA = new Player("李四", 20, 100);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("张三被打败了");
	}
	
	//*-------------------------first problem finished------------------------------------------------
	
//	@Test
//	public void should_two_player_fight_together_and_output_process_and_result(){
//		//PrintStream out = mock(PrintStream.class);
//		Player playerA = new Player("李四", 40, 100);
//		Player playerB = new Player("张三", 30, 100);
//		Game game = new Game(playerA, playerB, out);
//		game.start();
//		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:60");
//		verify(out).println("张三攻击了李四,李四受到30点伤害,李四剩余生命:70");
//		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:20");
//		verify(out).println("张三攻击了李四,李四受到30点伤害,李四剩余生命:40");
//		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:-20");
//		verify(out).println("张三被打败了");
//	}
	
	//*-------------------------second problem finished------------------------------------------------
	
	@Test
	public void should_soldier_use_weapon_fight_with_person(){
		Weapon weapon = new Weapon("优质木棒", 10);
		Soldier playerA = new Soldier("李四",40,100,weapon,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质木棒攻击了普通人张三,张三受到50点伤害,张三剩余生命:50");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		verify(out).println("战士李四用优质木棒攻击了普通人张三,张三受到50点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_person_fight_with_person(){
		Player playerA = new Player("李四",40,100);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("普通人李四攻击了普通人张三,张三受到40点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了普通人李四,李四受到30点伤害,李四剩余生命:70");
		verify(out).println("普通人李四攻击了普通人张三,张三受到40点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了普通人李四,李四受到30点伤害,李四剩余生命:40");
		verify(out).println("普通人李四攻击了普通人张三,张三受到40点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_fight_with_soldier_use_protect(){
		
		Weapon stick = new Weapon("优质木棒", 10);
		Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",40,100,stick,20);
		Soldier playerB = new Soldier("张三", 30, 100, sword, 20);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质木棒攻击了战士张三,张三受到30点伤害,张三剩余生命:70");
		verify(out).println("战士张三用利剑攻击了战士李四,李四受到50点伤害,李四剩余生命:50");
		verify(out).println("战士李四用优质木棒攻击了战士张三,张三受到30点伤害,张三剩余生命:40");
		verify(out).println("战士张三用利剑攻击了战士李四,李四受到50点伤害,李四剩余生命:0");
		verify(out).println("李四被打败了");
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
		verify(out).print("名称:寒冰@棒,攻击力:2\r\n属性1:伤害:寒冰,伤害值:2,发动轮次:1,发动概率:false\r\n\r\n");
	}
	
	//*---------------------------weapon respository finished----------------------------------------------
	
	@Test
	public void should_soldier_use_weapon_with_poison_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute poison = new Poison("毒性", 2, 2);
		Weapon poisonArrow = new Weapon("优质毒箭", 10, poison, random);
		//Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",40,100,poisonArrow,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到50点伤害,张三中毒了,张三剩余生命:50");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:48");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到50点伤害,张三剩余生命:-2");
		verify(out).println("张三被打败了");
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
		Attribute poison = new Poison("毒性", 2, 2);
		Weapon poisonArrow = new Weapon("优质毒箭", 5, poison, random);
		Soldier playerA = new Soldier("李四",5,100,poisonArrow,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三中毒了,张三剩余生命:90");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三中毒了,张三剩余生命:78");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:74");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:64");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:46");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:36");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三中毒了,张三剩余生命:26");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:24");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:14");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:12");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:2");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:20");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:-8");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute freeze = new Freeze("冰冻", 2);
		Weapon freezeWeapon = new Weapon("寒冰剑", 20, freeze, random);
		Soldier playerA = new Soldier("李四",10,100,freezeWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三冻僵了,张三剩余生命:70");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:40");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:10");		
		verify(out).println("张三冻得直哆嗦,没有击中李四");
		verify(out, never()).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
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
		Attribute freeze = new Freeze("冰冻", 2);
		Weapon freezeWeapon = new Weapon("寒冰剑", 5, freeze, random);
		Soldier playerA = new Soldier("李四",5,100,freezeWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:90");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:80");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:70");		
		verify(out, times(3)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out, times(3)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:40");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");
		verify(out, times(3)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute halo = new Halo("晕锤", 2);
		Weapon haloHammer = new Weapon("晕锤", 20, halo, random);
		Soldier playerA = new Soldier("李四",10,100,haloHammer,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三晕倒了,张三剩余生命:70");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三剩余生命:40");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三剩余生命:10");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
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
		Attribute halo = new Halo("晕锤", 2);
		Weapon haloHammer = new Weapon("晕锤", 5, halo, random);
		Soldier playerA = new Soldier("李四",5,100,haloHammer,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三晕倒了,张三剩余生命:90");
		verify(out, times(3)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三晕倒了,张三剩余生命:80");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:2轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:70");		
		verify(out, times(3)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:60");
		verify(out, times(2)).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三晕倒了,张三剩余生命:40");
		verify(out, times(3)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out, times(2)).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");		
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_fight_with_person(){
		when(random.nextInt(9))
		.thenReturn(0)
		.thenReturn(1);
		Attribute bust = new Bust();
		Weapon bustWeapon = new Weapon("利剑", 20, bust, random);
		Soldier playerA = new Soldier("李四",10,100,bustWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到70点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
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
		Weapon bustWeapon = new Weapon("利剑", 5, bust, random);
		Soldier playerA = new Soldier("李四",5,100,bustWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:80");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:40");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:10");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		
		verify(out).println("张三被打败了");
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
		
		Attribute fire = new Fire("烧伤", 3);
		Weapon fireWeapon = new Weapon("火焰剑", 15, fire, fireRandom);
		Attribute feeze = new Freeze("冰冻", 2);
		Weapon freezeWeapon = new Weapon("冰刀", 20, feeze, freezeRandom);
		Soldier playerA = new Soldier("李四",10,100,fireWeapon,10);
		Soldier playerB = new Soldier("张三", 20, 100, freezeWeapon, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用火焰剑攻击了战士张三,张三受到15点伤害,张三烧伤了,张三剩余生命:85");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:83");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四冻僵了,李四剩余生命:70");
		
		verify(out).println("战士李四用火焰剑攻击了战士张三,张三受到15点伤害,张三剩余生命:68");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:66");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用火焰剑攻击了战士张三,张三受到15点伤害,张三剩余生命:51");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:49");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:10");
		
		verify(out).println("李四冻得直哆嗦,没有击中张三");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:-20");
		
		verify(out).println("李四被打败了");
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
		
		Attribute posion = new Poison("毒性", 2);
		Weapon poisonWeapon = new Weapon("毒剑", 15, posion, poisonRandom);
		Attribute halo = new Halo("眩晕", 2);
		Weapon haloWeapon = new Weapon("晕锤", 20, halo, haloRandom);
		Soldier playerA = new Soldier("李四",10,100,poisonWeapon,10);
		Soldier playerB = new Soldier("张三", 20, 100, haloWeapon, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用毒剑攻击了战士张三,张三受到15点伤害,张三中毒了,张三剩余生命:85");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:83");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四晕倒了,李四剩余生命:70");
		
		verify(out).println("李四晕倒了,无法攻击,眩晕还剩:1轮");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:81");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四剩余生命:40");
		
		verify(out).println("李四晕倒了,无法攻击,眩晕还剩:0轮");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四剩余生命:10");
		
		verify(out).println("战士李四用毒剑攻击了战士张三,张三受到15点伤害,张三剩余生命:66");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四剩余生命:-20");
		
		verify(out).println("李四被打败了");
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
		
		Attribute halo = new Halo("眩晕", 3);
		Weapon haloWeapon = new Weapon("晕锤", 15, halo, haloRandom);
		Attribute freeze = new Freeze("冰冻", 2);
		Weapon freezeWeapon = new Weapon("冰刀", 20, freeze, freezeRandom);
		Soldier playerA = new Soldier("李四",10,100,haloWeapon,10);
		Soldier playerB = new Soldier("张三", 20, 100, freezeWeapon, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三晕倒了,张三剩余生命:85");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:70");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:0轮");

		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:55");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四冻僵了,李四剩余生命:70");
		
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:40");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:25");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:10");
		
		verify(out).println("李四冻得直哆嗦,没有击中张三");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:-20");
		
		verify(out).println("李四被打败了");
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
		Attribute freeze = new Freeze("冰冻", 2);
		Attribute fire = new Fire("烧伤", 3);
		Weapon firePlusFreezeWeapon = new Weapon("烈焰寒冰剑", 5, fire, random);
		firePlusFreezeWeapon.addAttribute(freeze);
		Soldier playerA = new Soldier("李四",5,100,firePlusFreezeWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三烧伤了,张三剩余生命:90");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:78");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:76");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:66");		
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:64");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:54");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:44");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");		
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:34");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:24");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:14");
		verify(out).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:4");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:20");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:-6");
		verify(out).println("张三被打败了");
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
		Attribute freeze = new Freeze("冰冻", 2);
		Attribute fire = new Fire("烧伤", 3);
		Weapon firePlusFreezeWeapon = new Weapon("烈焰寒冰剑", 5, fire, random);
		firePlusFreezeWeapon.addAttribute(freeze);
		Soldier playerA = new Soldier("李四",5,100,firePlusFreezeWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三烧伤了,张三剩余生命:90");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:78");
		verify(out, never()).println("张三受到2点烧伤伤害,张三剩余生命:86");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:68");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:58");
		verify(out).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:48");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");		
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:38");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:28");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:18");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:8");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:20");
		
		verify(out).println("战士李四用烈焰寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:-2");
		verify(out).println("张三被打败了");
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
		Attribute smallFire = new Fire("烧伤", 2, 2);
		Attribute bigFire = new Fire("烧伤", 3, 5);
		Weapon fireWeapon = new Weapon("烈焰剑", 5, smallFire, random);
		fireWeapon.addAttribute(bigFire);
		Soldier playerA = new Soldier("李四",5,100,fireWeapon,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三烧伤了,张三剩余生命:90");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三烧伤了,张三剩余生命:78");
		verify(out).println("张三受到7点烧伤伤害,张三剩余生命:71");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:61");	
		verify(out).println("张三受到7点烧伤伤害,张三剩余生命:54");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:44");
		verify(out).println("张三受到7点烧伤伤害,张三剩余生命:37");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:27");
		verify(out).println("张三受到7点烧伤伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");		
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
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
		Attribute smallFire = new Fire("烧伤", 2, 2);
		Attribute bust = new Bust();
		Weapon fireWeaponWithBust = new Weapon("烈焰剑", 5, smallFire, random);
		fireWeaponWithBust.addAttribute(bust);
		Soldier playerA = new Soldier("李四",5,100,fireWeaponWithBust,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三烧伤了,张三剩余生命:90");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:68");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:66");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:56");	
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:46");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:36");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");		
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:26");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:16");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:6");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:20");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:-4");		
		verify(out).println("张三被打败了");
	}
	
	//*----------------------fourth problem step two finished-----------------------------------------
}

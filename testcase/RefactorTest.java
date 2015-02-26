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
import com.tw.trainning.fightergame.weapon.attribute.Fire;
import com.tw.trainning.fightergame.weapon.attribute.Freeze;
import com.tw.trainning.fightergame.weapon.attribute.Halo;
import com.tw.trainning.fightergame.weapon.attribute.Poison;


public class RefactorTest {
	
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
		WeaponRespository.listWeapon(out);
		verify(out).print("名称:优质木棒,攻击力:20\r\n名称:利剑,攻击力:50\r\n" +
				"名称:长矛,攻击力:40\r\n名称:砍刀,攻击力:30\r\n");
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
	
	//*----------------------fourth problem step one finished, rest is bust test case-----------------------------------------
	
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
	public void should_weapon_first_fire_and_freeze_when_fire_not_finish(){
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
	public void should_weapon_with_two_fire_and_accumulate(){
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
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:27");
		verify(out).println("张三受到7点烧伤伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");		
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用烈焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
}

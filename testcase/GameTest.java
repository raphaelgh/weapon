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
	
	PrintStream out = mock(PrintStream.class);;
	Weapon weapon = mock(Weapon.class);
	Protect protect = mock(Protect.class);
	Soldier soldier;
	Player person = new Player("Jack", "person", 100, 30, out);
	Game game;
	Random mockedRandom = mock(Random.class);
	
	@Before
	public void setup(){
		when(weapon.value()).thenReturn(20);
		when(weapon.toString()).thenReturn("stick");
		when(protect.value()).thenReturn(10);
		soldier = new Soldier("Tommy", "soldier", 100, 20, weapon, protect, out);
	}
	
	@Test
	public void should_person_tommy_with_attack_20_fight_with_person_jack_with_attack_20_and_output_tommy_be_defeated(){
		Player A = new Player("Tommy", "person", 100, 20, out);
		Player B = new Player("Jack", "person", 100, 30, out);
//		Player A = mock(Player.class);
//		when(A.canBeAttack()).thenReturn(true);
		game = new Game(A, B, out);
		game.start();
		verify(out).println("person Tommy attack person Jack. Jack lose 20 blood. Jack left 80 blood.");
		verify(out).println("person Jack attack person Tommy. Tommy lose 30 blood. Tommy left 70 blood.");
		verify(out).println("person Tommy attack person Jack. Jack lose 20 blood. Jack left 60 blood.");
		verify(out).println("person Jack attack person Tommy. Tommy lose 30 blood. Tommy left 40 blood.");
		verify(out).println("person Tommy attack person Jack. Jack lose 20 blood. Jack left 40 blood.");
		verify(out).println("person Jack attack person Tommy. Tommy lose 30 blood. Tommy left 10 blood.");
		verify(out).println("person Tommy attack person Jack. Jack lose 20 blood. Jack left 20 blood.");
		verify(out).println("person Jack attack person Tommy. Tommy lose 30 blood. Tommy left -20 blood.");
		verify(out).println("Tommy be defeated!");
	}
	
	@Test
	public void should_soldier_tommy_with_attack_20_use_weapon_20_with_protect_10_fight_with_person_jack_with_attack_30(){
		when(weapon.use()).thenReturn(" use stick");
		when(weapon.harmStatus(person.getName())).thenReturn("");
		Player soldier = new Player("Tommy", "soldier", 100, 20, weapon, protect, out, null);
		game = new Game(soldier, person, out);
		game.start();
		verify(out).println("soldier Tommy use stick attack person Jack. Jack lose 40 blood. Jack left 60 blood.");
		verify(out).println("person Jack attack soldier Tommy. Tommy lose 20 blood. Tommy left 80 blood.");
		verify(out).println("soldier Tommy use stick attack person Jack. Jack lose 40 blood. Jack left 20 blood.");
		verify(out).println("person Jack attack soldier Tommy. Tommy lose 20 blood. Tommy left 60 blood.");
		verify(out).println("soldier Tommy use stick attack person Jack. Jack lose 40 blood. Jack left -20 blood.");
		verify(out).println("Jack be defeated!");
	}
	
	@Test
	public void should_person_jack_with_attack_30_fight_with_soldier_tommy_with_attack_20_use_weapon_stick_20_with_protect_10(){
		when(weapon.use()).thenReturn(" use stick");
		when(weapon.harmStatus(person.getName())).thenReturn("");
		Player soldier = new Player("Tommy", "soldier", 100, 20, weapon, protect, out, null);
		game = new Game(soldier, person, out);
		game.start();
		verify(out).println("soldier Tommy use stick attack person Jack. Jack lose 40 blood. Jack left 60 blood.");
		verify(out).println("person Jack attack soldier Tommy. Tommy lose 20 blood. Tommy left 80 blood.");
		verify(out).println("soldier Tommy use stick attack person Jack. Jack lose 40 blood. Jack left 20 blood.");
		verify(out).println("person Jack attack soldier Tommy. Tommy lose 20 blood. Tommy left 60 blood.");
		verify(out).println("soldier Tommy use stick attack person Jack. Jack lose 40 blood. Jack left -20 blood.");
		verify(out).println("Jack be defeated!");
	}
	
	@Test
	public void should_soldier_tommy_with_attack_20_use_weapon_stick_20_with_protect_10_fight_with_soldier_jack_with_attack_20_use_weapon_gun_100_with_protect_10(){
		Soldier tommy = new Soldier("Tommy", "soldier", 100, 20, weapon, protect, out);
		Weapon jackWeapon = mock(Weapon.class);
		when(jackWeapon.toString()).thenReturn("gun");
		when(jackWeapon.value()).thenReturn(100);
		Soldier jack = new Soldier("Jack", "soldier", 100, 20, jackWeapon, protect, out);
		game = new Game(tommy, jack, out);
		game.start();
		verify(out).println("soldier Tommy use stick attack soldier Jack. Jack lose 30 blood. Jack left 70 blood.");
		verify(out).println("soldier Jack use gun attack soldier Tommy. Tommy lose 110 blood. Tommy left -10 blood.");
		verify(out).println("Tommy be defeated!");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_possibility_attack_person(){
		when(mockedRandom.nextInt(9))
		.thenReturn(9)
		.thenReturn(9)
		.thenReturn(9)
		.thenReturn(9);
		Weapon weapon = new WeaponWithPoison("venom", 20, 2, out);
		Player soldier = new Player("Tommy", "soldier", 100, 20, weapon, protect, out, mockedRandom);
		Player person = new Player("Jack", "person", 100, 30, out);
		game = new Game(soldier, person, out);
		game.start();
		verify(out).println("soldier Tommy use venom attack person Jack. Jack lose 40 blood. Jack is poisoned. Jack left 60 blood.");
		verify(out).println("Jack is poisoned and lost 2 blood. Jack left 58 blood.");
		verify(out).println("person Jack attack soldier Tommy. Tommy lose 20 blood. Tommy left 80 blood.");
		verify(out).println("soldier Tommy use venom attack person Jack. Jack lose 40 blood. Jack is poisoned. Jack left 18 blood.");
		verify(out).println("Jack is poisoned and lost 2 blood. Jack left 16 blood.");
		verify(out).println("person Jack attack soldier Tommy. Tommy lose 20 blood. Tommy left 60 blood.");
		verify(out).println("soldier Tommy use venom attack person Jack. Jack lose 40 blood. Jack is poisoned. Jack left -24 blood.");
		verify(out).println("Jack be defeated!");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_possibility_attack_soldier_use_weapon_with_poison_and_possibility(){
		Random poisonRandom = mock(Random.class);
		Random freezeRandom = mock(Random.class);
		when(poisonRandom.nextInt(9))
		.thenReturn(9)
		.thenReturn(9)
		.thenReturn(1)
		.thenReturn(9);
		when(freezeRandom.nextInt(9))
		.thenReturn(9)
		.thenReturn(2)
		.thenReturn(1)
		.thenReturn(2);
		Weapon weaponWithPoison = new WeaponWithPoison("poison sword", 20, 2, out);
		Weapon weaponWithFreeze = new WeaponWithFreeze("freeze sword", 20, 0, out);
		Player tommy = new Player("Tommy", "soldier", 100, 20, weaponWithPoison, protect, out, poisonRandom);
		Player jack = new Player("Jack", "soldier", 100, 20, weaponWithFreeze, protect, out, freezeRandom);
		game = new Game(tommy, jack, out);
		game.start();
		verify(out).println("soldier Tommy use poison sword attack soldier Jack. Jack lose 30 blood. Jack is poisoned. Jack left 70 blood.");
		verify(out).println("Jack is poisoned and lost 2 blood. Jack left 68 blood.");
		verify(out).println("soldier Jack use freeze sword attack soldier Tommy. Tommy lose 30 blood. Tommy is freezed. Tommy left 70 blood.");
		//verify(out).println("Tommy is freezed.");
		verify(out).println("soldier Tommy use poison sword attack soldier Jack. Jack lose 30 blood. Jack is poisoned. Jack left 38 blood.");
		verify(out).println("Jack is poisoned and lost 2 blood. Jack left 36 blood.");
		verify(out).println("soldier Jack use sword attack soldier Tommy. Tommy lose 30 blood. Tommy left 40 blood.");
		//verify(out).println("Tommy is freezed.");
		verify(out).println("soldier Tommy use sword attack soldier Jack. Jack lose 30 blood. Jack left 6 blood.");
		//verify(out).println("Jack is poisoned and lost 2 blood. Jack left 4 blood.");
		verify(out).println("soldier Jack use sword attack soldier Tommy. Tommy lose 30 blood. Tommy left 10 blood.");
		//verify(out).println("Tommy is freezed.");
		verify(out).println("Tommy is freezed and cannot attack.");
		verify(out).println("soldier Jack use sword attack soldier Tommy. Tommy lose 30 blood. Tommy left -20 blood.");
		verify(out).println("Tommy be defeated!");
	}
	
//	@Test
//	public void should_mock(){
//		PrintStream out = mock(PrintStream.class);
//		Player A = mock(Player.class);
//		when(A.canBeAttack()).thenReturn(false);
//		Game game = new Game(A, A, out);
//		game.start();
//		verify(out).println();
//	}
}

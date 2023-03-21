package com.tokio.proyectofinal.models;

import java.util.Random;

import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

public class Heroe extends Player implements Cloneable{
	
	private final int  MAX_DAMAGE = 100;
	static public enum WarrioHeroe {
		 HOBBIT, ELFO, HUMAN
	}

	@Getter @Setter
	public WarrioHeroe warrioHeroes;
	public static final String[] nameHobbits = new String[] {"frodo" , "bilbo", "sam", "pippin", "merry"};
    public static final String[] nameElfos = new String[] {"arwen" , "legolas", "galadriel"};
	public static final String[] nameHumans = new String[] {"gandalf", "aragorn", "isildur"};
	
	public Heroe(String name, int life, int armor, ImageView imageView, WarrioHeroe warrio) {
		super(name, life, armor, imageView);
		this.warrioHeroes = warrio;

	}
	

	//contructor privado para clonar el objeto
	private Heroe(Heroe player) {
		setName(player.getName());
		setLife(player.getLife());
		setArmor(player.getArmor());
		setImageView(player.getImageView());
		setTotalLife(player.getLife() + player.getArmor());
		setInitialTotalLife(player.getLife() + player.getArmor());
		setWarrioHeroes(player.getWarrioHeroes());
	}

	@Override
	public int fight() {
		int shot1 = new Random().nextInt(MAX_DAMAGE);
		int shot2 = new Random().nextInt(MAX_DAMAGE);
		int shot = Math.max(shot1, shot2);
		setShot(shot);
		return getShot();

	}
	@Override
	public String toString() {
		return getName() + " - " + warrioHeroes.toString() + " (" + getInitialTotalLife() + ")";
	}
	
	@Override
	public Heroe clone() throws CloneNotSupportedException {
		return new Heroe(this);
	}
}

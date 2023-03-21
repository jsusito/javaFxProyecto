package com.tokio.proyectofinal.models;

import java.util.Random;

import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

public class Beast extends Player implements Cloneable{

	private final int  MAX_DAMAGE = 90;
	public static enum WarrioBeasts {
		ORCOS, TRASGOS, SAURON
	}
	public static String[] nameOrcos = new String[] {"bolg","lurtz", "ugluk", "sharku"};
    public static String[] nameTrasgos = new String[] {"grantrasgo", "trasgo"};
	public static String[] nameSauron = new String[] {"sauron"};
	
	@Getter @Setter
	public WarrioBeasts warrioBeast;
	
	public Beast(String name, int life, int armor, ImageView imageView, WarrioBeasts warrio) {
		super(name, life, armor, imageView);
		this.warrioBeast = warrio;
	}
	
	//Constructor privado para clonar Objeto
	private Beast(Beast player) {
		setName(player.getName());
		setLife(player.getLife());
		setArmor(player.getArmor());
		setTotalLife(player.getLife() + player.getArmor());
		setInitialTotalLife(player.getLife() + player.getArmor());
		setWarrioBeast(player.getWarrioBeast());
		setImageView(player.getImageView());

	}

	@Override
	public int fight() {
		int shot = new Random().nextInt(MAX_DAMAGE);
		setShot(shot);
		return getShot();
	}
	
	@Override
	public String toString() {
		return getName() + " - " + warrioBeast.toString() + " (" + getInitialTotalLife() + ")";
	}
	
	@Override
	public Beast clone() throws CloneNotSupportedException {
		
		return new Beast(this);
	}
}

package com.tokio.proyectofinal.models;

import javafx.scene.image.ImageView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public abstract class Player {
	private String name;
	private float life;
	private int shot;
	private float armor;
	private float totalLife; 
	
	private ImageView imageView;
	private boolean died;
	private float initialTotalLife; //vida inicial que va a mantener durante todo el juego
			
	@Setter(AccessLevel.NONE)
	private int pointFight;
	
	public Player(String name, float life, float armor, ImageView imageView) {
		super();
		this.name = name;
		this.imageView = imageView;
		this.life = life;
		this.armor = armor;
		totalLife = life + armor;
		initialTotalLife = life + armor;
		died = false;
		
	}
	
	
	abstract public int fight();
	
	public void setDamage(float damage) {
		if(armor > 0)
			armor -= damage;
		
		if(armor == 0) {
			life -= damage;
			return;
		}
		if(armor < 0) {
			life += armor;
			armor = 0;
		}
	}
			
	//Cuando las batallas superan el primer round se inicializa la vida a como quedo en el round anterior
	public void nextRoundTotalLife() {
		initialTotalLife = totalLife; 
	}

	public void setPointFight(float currentPointFight) {
			pointFight += currentPointFight;
	}

	public void setBattelLife(float pointFight) {
		totalLife -= pointFight;
	}
	
	public void resetPointFight() {
		pointFight = 0; 
	}
}

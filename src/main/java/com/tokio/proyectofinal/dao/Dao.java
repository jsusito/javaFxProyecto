package com.tokio.proyectofinal.dao;

import java.util.ArrayList;

import com.tokio.proyectofinal.models.Beast;
import com.tokio.proyectofinal.models.Heroe;
import com.tokio.proyectofinal.models.Player;

public class Dao {
	private ArrayList<Beast> beasts;
	private ArrayList<Heroe> heroes;
	
	public Dao() {
		beasts = new ArrayList<>();
		heroes = new ArrayList<>();
	}
	
	public Dao(ArrayList<Heroe> heroes, ArrayList<Beast> beasts) {
		this.beasts = new ArrayList<>(beasts);
		this.heroes = new ArrayList<>(heroes);
	}
	
	//clonamos objeto para desvincularlo de la lista
	//Asi cada vez que se le da al boton de lucha no modifica los valores de la lista y da un resultado limpio y correcto cada vez
	public void addBeast(Beast beast) {
		try {
			beasts.add(beast.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addHeroe(Heroe heroe) {
		try {
			heroes.add(heroe.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void modify(Player player) {
		
	}
	
	public void removeAll() {
		heroes = new ArrayList<>();
		beasts = new ArrayList<>();
	}
	
	public ArrayList<Beast> getBeasts(){
		return beasts;
	}
	
	public ArrayList<Heroe> getHeroes(){
		return heroes;
	}
	
		
}

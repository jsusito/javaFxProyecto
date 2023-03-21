package com.tokio.proyectofinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tokio.proyectofinal.dao.Dao;
import com.tokio.proyectofinal.models.Beast;
import com.tokio.proyectofinal.models.Beast.WarrioBeasts;
import com.tokio.proyectofinal.models.Heroe;
import com.tokio.proyectofinal.models.Heroe.WarrioHeroe;
import com.tokio.proyectofinal.models.TableResult;
import com.tokio.proyectofinal.util.ImageUtil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

public class ControllerRoundsBattles{

	@Getter
	@Setter
	private ArrayList<Heroe> heroes;;
	@Getter
	@Setter
	private ArrayList<Beast> beasts;
	
	private Dao daoWarrior;
	public TextArea fightTextArea;
	
	private int totalTurn;
	private int turnBattel;
	private int rounds;
	
	private float heroeLifeBeforeBattle;
	private float heroeArmorBeforeBattle;
	private float beastLifeBeforeBattle;
	private float beastArmorBeforeBattle;
	private float lifeHeroBeforeBattle;
	private float lifeBeastBeforeBattle;
	public static boolean roundStep = false;
	public static boolean roundContinue = true;

	static public ArrayList<TableResult> results;
	private TableResult itemHeroe;
	private TableResult itemBeast;
	
	public ControllerRoundsBattles(Dao currentDaoWarrior, TextArea fightTextArea) {
		daoWarrior = currentDaoWarrior;
		this.fightTextArea = fightTextArea;
		heroes = (daoWarrior.getHeroes());
		beasts = (daoWarrior.getBeasts());
		results = new ArrayList<TableResult>();
		itemHeroe = new TableResult();
		itemBeast = new TableResult();
	}

	public void init() {
		List<Heroe> heroesTurn = null;
		List<Beast> beastTurn = null;
		do {
			showRounds(++rounds);
			
			//Iniciamos un round
			initBattle();
			
			// Comprobamos los vencedores
			heroesTurn = heroes.stream().filter(heroe -> (heroe.isDied() == false))
					.collect(Collectors.toList());
			beastTurn = beasts.stream().filter(beast -> (beast.isDied() == false)).collect(Collectors.toList());
			
			StringBuilder builderProcessBattle =  new StringBuilder("\nLista de finalistas\n"); 
			builderProcessBattle.append("Heroes: ");
			heroesTurn.forEach(player -> builderProcessBattle.append(player.getName() + " "));
			if(heroesTurn.isEmpty())
				builderProcessBattle.append("No hay Finalista");
			builderProcessBattle.append("\n");
			builderProcessBattle.append("Bestias: ");
			beastTurn.forEach(player -> builderProcessBattle.append(player.getName() + " "));
			if(beastTurn.isEmpty())
				builderProcessBattle.append("No hay Finalista");
			showFinalist(builderProcessBattle.toString());
			
			//Si quedan vencedores o otros jugadores pendientes que no hayan jugado da comienza otro round
			if ((heroesTurn.size() > 0) && (beastTurn.size() > 0)) {
				heroes = new ArrayList<>(heroesTurn);
				beasts = new ArrayList<>(beastTurn);
												
				//Establecemos la vida inicial para mostrar al principio del proximo rounds
				heroes.forEach(Heroe::nextRoundTotalLife);
				beasts.forEach(Beast::nextRoundTotalLife);
				init();
			}
			else { 
				StringBuilder msg = new StringBuilder();
				Alert alert = new Alert(AlertType.INFORMATION);
				ImageView image = null;
				
				if(!heroesTurn.isEmpty()) {
					msg.append("LOS HEROES HAN GANADO\n");
					heroesTurn.forEach(heroe -> msg.append(heroe.getName() + "\n"));
					image = ImageUtil.getImageView("heroes.jpg");
				}
				else if(!beastTurn.isEmpty()) {
					msg.append("LAS BESTIAS HAN GANADO\n");
					beastTurn.forEach(bestia -> msg.append(bestia.getName() + "\n"));
					image = ImageUtil.getImageView("beasts.jpg");
				}
				else
					msg.append("No hay finalistas");
				
				
				alert.setTitle("Lista de Finalistas");
				alert.setHeaderText("");
				if(image != null)
					alert.setGraphic(image);
				alert.setContentText(msg.toString());
				alert.showAndWait();
				return;
			}
		}while(false);
		
	}

	
	public void initBattle() {

		turnBattel = 0;
		// Sacamos el numero de participantes para el primer Round consecutivo
		//Sacamos de las dos listas el número maximo de participantes que sean pares
		
		totalTurn = Math.min(heroes.size(), beasts.size());

		Beast beast = null;
		Heroe heroe = null;

		boolean battelContinue = true;

		do {
			turnBattel++;
			showBattelTurn();
			//Se empieza a emparejar los contrincantes de los dos Arrays(Heroes y Bestias)
			for (int i = 0; i < totalTurn; i++) {
				beast = beasts.get(i);
				heroe = heroes.get(i);

				itemHeroe.setName(heroe.getName());
				itemBeast.setName(beast.getName());
				
				//Si alguno de los dos ya ha muerto en turnos anteriores pasa a la siguiente pareja
				if (heroe.isDied() == true || beast.isDied() == true)
					continue;
				
				battleWarrios(beast, heroe);

			}

			// Comprobamos las bestias muertas
			int numberDiedBeast = 0;
			for (int i = 0; i < totalTurn; i++) {
				Beast b = beasts.get(i);
				if (b.isDied())
					numberDiedBeast++;
			}
			
			// Comprobamos los heroes muertos
			int numberDiedHeroes = 0;
			for (int i = 0; i < totalTurn; i++) {
				Heroe h = heroes.get(i);
				if (h.isDied())
					numberDiedHeroes++;
			}

			// Si no estan todas las bestias muertas no se acaba el primer Round
			if (numberDiedBeast >= totalTurn)
				battelContinue = false;
			// Si no estan todas los heroes muertos no se acaba el primer Round
			if (numberDiedHeroes >= totalTurn)
				battelContinue = false;

			// Comprobamos vivo del oponente contrario
			//Si aunque no estén todas las bestias muertas y no estén todos los heroes muertos, pero cada uno ha acabado con
			//su contrincante dará verdadero y se acaba el rounds.
			battelContinue = allcheckPair();
		} while (battelContinue);
		
		
	}

	private boolean allcheckPair() {

		// comprobamos si hay parejas donde los dos sigan vivo
		// Si alguna paraje sigue vive hay que continuar la batalla
		for (int i = 0; i < totalTurn; i++) {
			Beast b = beasts.get(i);
			Heroe h = heroes.get(i);
			if ((h.isDied() == false && b.isDied() == false))
				return true;
		}
		return false;
	}

	// Luchan los seleccionados y se establecen los puntos de vida
	private void battleWarrios(Beast beast, Heroe heroe) {

		//Inicializamos los puntos de la Lucha anterior.
		heroe.resetPointFight();
		beast.resetPointFight();
		
		// get point the battle
		heroe.setPointFight(heroe.fight());
		beast.setPointFight(beast.fight());		
		
		heroeLifeBeforeBattle = heroe.getLife();
		heroeArmorBeforeBattle = heroe.getArmor();
		lifeHeroBeforeBattle = heroe.getLife() + heroe.getArmor();
		
		beastLifeBeforeBattle = beast.getLife();
		beastArmorBeforeBattle = beast.getArmor();
		lifeBeastBeforeBattle = beast.getLife() + beast.getArmor();
		
		
		//condiciones si elfo contro orcos + 10 unidades de daño
		//condiciones si hobbits contro trasgos pierden 5 unidades
		//condiciones si orcos le quita un 10% de armadura al oponente
		float extraDamageArmor = 0;
		
		if(heroe.getWarrioHeroes() == WarrioHeroe.ELFO && beast.getWarrioBeast() == WarrioBeasts.ORCOS) 
			heroe.setPointFight(10);
		if(heroe.getWarrioHeroes() == WarrioHeroe.HOBBIT && beast.getWarrioBeast() == WarrioBeasts.TRASGOS) 
			beast.setPointFight(5);
		if(beast.getWarrioBeast() == WarrioBeasts.ORCOS) {
			extraDamageArmor =  (heroe.getArmor() * 0.10f);
		}
		
		//Datos para formar la tabla
		itemHeroe.setRounds(rounds);
		itemBeast.setRounds(rounds);
		
		itemBeast.setType("BESTIA");
		itemHeroe.setType("HEROE");
		
		itemHeroe.setLife(heroe.getTotalLife());
		itemHeroe.setArmor(heroe.getArmor());
		itemHeroe.setAtack(heroe.getShot());
		
		itemBeast.setLife(beast.getTotalLife());
		itemBeast.setArmor(beast.getArmor());
		itemBeast.setAtack(beast.getShot());
		
		results.add(itemHeroe);
		results.add(itemBeast);
		
		itemHeroe = new TableResult();
		itemBeast = new TableResult();
		//Sacamos los puntos totales a restar
		//ExtraDamage a cualquier heroe si oponente es Orco
		if (beast.getWarrioBeast() == WarrioBeasts.ORCOS) 
			beast.setPointFight(extraDamageArmor);
		
		heroe.setDamage(beast.getPointFight());
		beast.setDamage(heroe.getPointFight());
		heroe.setBattelLife(beast.getPointFight());
		beast.setBattelLife(heroe.getPointFight());
				
		if (beast.getTotalLife() <= 0)
			beast.setDied(true);
		if (heroe.getTotalLife() <= 0)
			heroe.setDied(true);
		
		showResult(heroe, beast);
		
		//Para el caso de que el usuario quiera ver la lucha por etapas se estable una espera hasta que el usuario le de al boton
		//continuar >>
		if (ControllerRoundsBattles.roundStep) {
			while(!ControllerRoundsBattles.roundContinue){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ControllerRoundsBattles.roundContinue = false;
		}	
	}

	private void showFinalist(String string) {
		fightTextArea.appendText(string);
	}

	private void showRounds(int round) {
		String battleProcess =("\n******************** Round Número " + round + "***************************");
		fightTextArea.appendText(battleProcess);
	}
	
	private void showResult(Heroe heroe, Beast beast) {
		String result = (heroe.getName().toUpperCase() + "\nVida inicial: " + (heroeLifeBeforeBattle 
				 + " Armadura:" + heroeArmorBeforeBattle + " Total: " + lifeHeroBeforeBattle
				 + "\nsaca " + heroe.getPointFight() + " y el oponente " + 
					beast.getName() + " se queda con " +  beast.getTotalLife() + "\n" 
				 + 	(beast.getName().toUpperCase() + "\nVida inicial: " + (beastLifeBeforeBattle 
				 + " Armadura:" + beastArmorBeforeBattle + " Total: " + lifeBeastBeforeBattle
				 + "\nsaca " + beast.getPointFight() + " y el oponente " + 
		     		heroe.getName() + " se queda con " +  heroe.getTotalLife() + "\n" ))));
		
		fightTextArea.appendText("\n" + result);
		
	}
	
	
	private void showBattelTurn() {
		String battleProcess = ("\nBatalla n:" + turnBattel + "\n--------------------------------------------");
		fightTextArea.appendText(battleProcess);
	}
	  
}

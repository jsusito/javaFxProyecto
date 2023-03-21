package com.tokio.proyectofinal.view.top;

import com.tokio.proyectofinal.util.FontUtil;
import com.tokio.proyectofinal.util.PersonalLabel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class TopPanel extends HBox{
	
	
	//public ImageView image;
	
	public TopPanel() {
		super();
		setPadding(new Insets(5, 5, 5, 5));
		setSpacing(10);
		initComponents();
		
	}

	private void initComponents() {
		Label title = PersonalLabel.getLabel("La  batalla  por  la  tierra  media" , 50);
		Font font = FontUtil.getFont("CircularStd-Black.otf", 50); 
		title.setFont(font);
		setAlignment(Pos.CENTER);
		getChildren().add(title);
		
	}

}

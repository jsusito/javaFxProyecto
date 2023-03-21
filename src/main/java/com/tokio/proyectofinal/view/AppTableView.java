package com.tokio.proyectofinal.view;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.tokio.proyectofinal.models.TableResult;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppTableView extends HBox{

	public TableView<TableResult> results;
	public AppTableView() {
		results = new TableView<>();
		initComponent();
	}

	private void initComponent() {
		Field[] fields = TableResult.class.getDeclaredFields();
		results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		results.setPlaceholder(new Label("No has seleccionado ningún participante"));
		for(Field f : fields) {
			
			TableColumn<TableResult, String> column = new TableColumn<>(f.getName());
			column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
			results.getColumns().add(column);
			
		}
	}
	
	public void show(ArrayList<TableResult> resultTable) {
		results.getItems().addAll(resultTable);
		getChildren().add(results);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Resultado de Puntuaciones");
		Scene scene = new Scene(this);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
}

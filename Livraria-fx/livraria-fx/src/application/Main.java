package application;

import java.io.IOException;

import br.com.casadocodigo.produto.Produto;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import repo.RepositorioDeProdutos;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		Group group = new Group();
		Scene scence = new Scene(group, 690, 510);

		/* Duvida */
		ObservableList<Produto> produtos = new RepositorioDeProdutos().lista();
		TableView tableView = new TableView<>(produtos);

		Button button = new Button("Exportar CSV");
		button.setLayoutX(575);
		button.setLayoutY(25);
		button.setOnAction(event -> {
			ExportarEmCSV(produtos);
		});// mesmo metodo mas com um expressão lambda do java 8

		/*
		 * (new EventHandler<ActionEvent>(){
		 * 
		 * @Override public void handle(ActionEvent event){ try{ new
		 * Exportador().paraCSV(produtos); }catch(IOException e ){
		 * System.out.println("Falhou ao exportar :"+e); } } });
		 */

		TableColumn nomeColumn = new TableColumn("Nome");
		nomeColumn.setMinWidth(180);
		nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));

		TableColumn descricaoColumn = new TableColumn("Descrição");
		descricaoColumn.setMinWidth(230);
		descricaoColumn.setCellValueFactory(new PropertyValueFactory("desc"));

		TableColumn valorColumn = new TableColumn("Valor");
		valorColumn.setMinWidth(60);
		valorColumn.setCellValueFactory(new PropertyValueFactory("Valor"));

		TableColumn isbnColumn = new TableColumn("ISBN");
		isbnColumn.setMinWidth(180);
		isbnColumn.setCellValueFactory(new PropertyValueFactory("ISBN"));

		tableView.getColumns().addAll(nomeColumn, descricaoColumn, valorColumn, isbnColumn);

		VBox vbox = new VBox(tableView);
		vbox.setPadding(new Insets(70, 0, 0, 10));

		Label label = new Label("Listagem de livros");
		label.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30));
		label.setPadding(new Insets(20, 0, 10, 10));
		group.getChildren().addAll(label, vbox, button);
		primaryStage.setScene(scence);
		primaryStage.setTitle("Sistema de livraria com Java FX");
		primaryStage.show();
	}

	private void ExportarEmCSV(ObservableList<Produto> produtos) {
		try {
			new Exportador().paraCSV(produtos);
		} catch (IOException e) {
			System.out.println("Falah ao criar o arquivo :" + e);
		}
	}

	public static void main(String[] args) throws IOException {
		launch(args);

	}
}

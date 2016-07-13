package application;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.produto.Livro;
import br.com.casadocodigo.produto.LivroImpresso;
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

@SuppressWarnings({"unchecked","rawtypes"})
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	
		Group group = new Group();
		Scene scence = new Scene(group, 690, 510);

		/* Duvida */
		ObservableList<Produto> produtos = new RepositorioDeProdutos().lista();
		TableView tableView = new TableView<>(produtos);
		
		Button button = new Button ("Exportar para CSV");
		button.setLayoutX(575);
		button.setLayoutY(25);
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
		vbox.setPadding(new Insets(70,0,0,10));
		
		Label label = new Label("Listagem de livros");
		label.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30));
		label.setPadding(new Insets(20, 0, 10, 10));
		group.getChildren().addAll(label,vbox,button);
		primaryStage.setScene(scence);
		primaryStage.setTitle("Sistema de livraria com Java FX");
		primaryStage.show();
	}

	public static void main(String[] args) throws IOException{
		launch(args);

		Autor autor = new Autor();
		autor.setNome("Lucas Ferreira");
		Livro livro = new LivroImpresso(autor);
		livro.SetNome("Livro do X");
		new Exportador().paraCSV(Arrays.asList(livro));
	}
}

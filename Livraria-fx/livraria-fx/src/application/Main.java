package application;

import java.io.IOException;

import br.com.casadocodigo.produto.Produto;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		/* Duvida */
		ObservableList<Produto> produtos = new ProdutoDAO().lista();
		TableView tableView = new TableView<>(produtos);
		
		double valorTotal = produtos.stream().mapToDouble(Produto::getValor).sum();
		
		/*
		int qtde = 0;
		for (Produto produto : produtos){
			valorTotal += produto.getValor();
			qtde++;
		}*/
		
		Group group = new Group();
		Scene scene = new Scene(group, 690, 510);
		
		scene.getStylesheets().add(getClass()
				.getResource("application.css").toExternalForm());
		
		

		Label label = new Label("Listagem de livros");
		label.setId("label-listagem");
		/**
		 *label.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30)); 
		 * label.setPadding(new Insets(20, 0, 10, 10));
		label.setStyle("-fx-font-size: 30px; "
				+ "-fx-padding: 20 0 10 10;");
		 * */
		
		Label progresso = new Label();
		progresso.setId("progresso");
		
		Label labelFooter = new Label(String.format("Você tem R$%.2f em estoque,"
				+ "com um total de %d produtos", valorTotal,produtos.size()));
		labelFooter.setId("label-footer");
		
		Button button = new Button("Exportar CSV");
		button.setId("button");
		button.setOnAction(event -> {
				Task<Void> task = new Task<Void>(){
					@Override
					protected Void call() throws Exception{
						DormePorSegundos();
						ExportarEmCSV(produtos);
						return null;
					}
				};
				
			/*
			 * Sem java 8
			 
			task.setOnRunning(new EventHandler<WorkerStateEvent>(){
				  @Override
				  	public void handle(WorkerStateEvent e){
				  	 progresso.setText("Exportando....");
				  	}
				  });
			 * }
			 * */	
			
			task.setOnRunning(e -> progresso.setText("exportando..."));
			task.setOnSucceeded(e ->progresso.setText("concluido"));
			new Thread(task).start();
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
		vbox.setId("vbox");
		//vbox.setPadding(new Insets(70, 0, 0, 10));
		
		group.getChildren().addAll(label,labelFooter, vbox, button, progresso);
		primaryStage.setScene(scene);
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
	
	private void DormePorSegundos(){
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			System.out.println("Ops! algo deu errado : "+e);
		}
	}

	public static void main(String[] args) throws IOException {
		launch(args);

	}
}
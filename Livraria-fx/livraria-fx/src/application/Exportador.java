package application;


import java.util.List;
import java.io.IOException;
import java.io.PrintStream;
import br.com.casadocodigo.produto.Produto;

public class Exportador {
	
	public void paraCSV(List<Produto> produtos)
		throws IOException{
			PrintStream ps = new PrintStream ("produtos.csv");
			ps.println("Nome, Dexcrição, Valor, ISBN");
			for (Produto produto: produtos){
				ps.println(String.format("%s, %s, %s, %s,",
						produto.getNome(),
						produto.getDesc(),
						produto.getValor(),
						produto.getISBN()));
			}
			ps.close();
	}
	
	public static void main(String [] args) throws IOException{
	
	}
}

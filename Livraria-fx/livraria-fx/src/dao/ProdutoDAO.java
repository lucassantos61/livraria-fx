package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.produto.LivroImpresso;
import br.com.casadocodigo.produto.Produto;
import connection.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProdutoDAO {
	public ObservableList<Produto> lista(){
		ObservableList<Produto> produtos = FXCollections
				.observableArrayList();

		PreparedStatement ps;
		try(Connection conn = new ConnectionFactory().getConnection()){
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM produtos");
			ResultSet resultset = ps.executeQuery();
			while(resultset.next()){
				LivroImpresso livro = new LivroImpresso(new Autor());
				livro.SetNome(resultset.getString("nome"));
				livro.setDesc(resultset.getString("descricao"));
				livro.setValor(resultset.getDouble("valor"));
				livro.setISBN(resultset.getString("isbn"));
				produtos.add(livro);
			}
			resultset.close();
			ps.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return produtos;
		
	}
	
	public void adiciona (Produto produto){
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps ;
		try{
			ps = (PreparedStatement) conn.prepareStatement("INSERT INTO peodutos (nome,descricao,valor,isbn) VALUES"
					+ "(?,?,?,?)");
			ps.setString(1,produto.getNome());
			ps.setString(2,produto.getDesc());
			ps.setDouble(3, produto.getValor());
			ps.setString(4,produto.getISBN());
			
			ps.close();
			conn.close();
					
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

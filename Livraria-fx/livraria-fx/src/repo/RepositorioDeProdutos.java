package repo;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.produto.Livro;
import br.com.casadocodigo.produto.LivroImpresso;
import br.com.casadocodigo.produto.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RepositorioDeProdutos {
	public ObservableList<Produto> lista(){
		Autor autor = new Autor();
		autor.setNome("Rodrigo Turini");
		autor.setCpf("111.111.111-11");
		autor.setEmail("rodrigo.turino@caelum.com");
		
		Livro livro = new LivroImpresso(autor);
		livro.SetNome("Java 8 Prático");
		livro.setDesc("Novos reccursos da linguagem");
		livro.setValor(59.90);
		livro.setISBN("978-85-66250-46-6");
		
		Livro maisUmlivro = new LivroImpresso(autor);
		maisUmlivro.SetNome("Desbravando a O.O");
		maisUmlivro.setDesc("Livro de java O.O");
		maisUmlivro.setValor(59.90);
		maisUmlivro.setISBN("321-54-67890-11-2");
		
		Autor outroAutor = new Autor();
		outroAutor.setNome("Paulo silveira");
		outroAutor.setCpf("111.111.111-11");
		outroAutor.setEmail("paulo.silveira@caelum.com");
		
		Livro outroLivro = new LivroImpresso (outroAutor);
		outroLivro.SetNome("Lógica de programação");
		outroLivro.setDesc("Crie seus primeiros programas");
		outroLivro.setValor(59.90);
		outroLivro.setISBN("978-85-66250-22-0");
		
		return FXCollections
				.observableArrayList(livro,maisUmlivro,outroLivro);
		
	}
}

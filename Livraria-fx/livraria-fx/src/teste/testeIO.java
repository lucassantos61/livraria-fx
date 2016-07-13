package teste;

import java.util.Scanner;

import application.Exportador;
import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.produto.Livro;
import br.com.casadocodigo.produto.LivroImpresso;

public class testeIO {
	public static void main(String[] args) {
		try {
			Autor autor = new Autor();
			autor.setNome("Lucas Ferreira");
			Livro livro = new LivroImpresso(autor);
			livro.SetNome("Livro do X");
			
			/*InputStream is = new FileInputStream("teste.txt");
			InputStream is = System.in;
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String linha = reader.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = reader.readLine();
			}
			reader.close();
			Scanner sc = new Scanner (new File ("teste.txt"));
			while (sc.hasNextLine()){
				System.out.println(sc.nextLine());
			}*/
			
			Scanner sc = new Scanner (System.in);
			System.out.println("Digite o nome :");
			String nome = sc.nextLine();
			System.out.println("Digite a idade : ");
			int idade = sc.nextInt();
			System.out.println("Nome digitado :" + nome);
			System.out.println("Idade informada :" + idade);

		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo : " + e);
		}
	}
}

package teste;

import application.Exportador;

public class TestandoThread {
	public static void main (String...args){
		//Codigo Verboso
		/*new Thread(new Runnable (){
			@Override
			public void run (){
				System.out.println("Rodando em paralelo !");
			}
		}).start();*/
		//Expressão Lambda do java 8
		new Thread(()-> System.out.println("Rodando em paralelo !")).start();
		System.out.println("terminei de rodar a main !");
	}
}

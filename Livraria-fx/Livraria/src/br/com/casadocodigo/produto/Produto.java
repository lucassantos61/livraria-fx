package br.com.casadocodigo.produto;

public interface Produto 
	extends Comparable<Produto>{
	String getNome();
	String getISBN();
	String getDesc();
	public abstract double getValor();
}

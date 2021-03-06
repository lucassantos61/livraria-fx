package br.com.casadocodigo.produto;
import br.com.casadocodigo.livraria.Autor;

public class LivroImpresso extends Livro implements Promocional{
    public LivroImpresso(Autor autor){
        super(autor);
    }
     private boolean jaTeveDesconto = false;
    public double getTaxaDeImpressao(){
        return getValor()*0.05;
    }
    @Override
    public boolean aplicaDesconto(double desc){
    	synchronized (this) {
			if (jaTeveDesconto){
				return false;
			}
		}
        jaTeveDesconto = true;
    	if (desc > 0.3){
    		return false;
    	}else{
    		double preco = this.getValor();
    		this.setValor(preco-(preco*desc));
    		return true;
    	}
    }
}
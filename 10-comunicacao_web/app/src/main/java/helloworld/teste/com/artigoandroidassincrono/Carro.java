package helloworld.teste.com.artigoandroidassincrono;

/**
 * Created by ricardo on 29/07/2015.
 */
public class Carro {

    public String nome;
    public int ano;
    public String marca;
    public float preco;

    @Override
    public String toString() {
        return nome + " - " + marca + " ("+ ano +")";
    }
}
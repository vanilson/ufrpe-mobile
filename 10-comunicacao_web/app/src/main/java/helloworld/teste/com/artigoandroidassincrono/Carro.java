package helloworld.teste.com.artigoandroidassincrono;


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
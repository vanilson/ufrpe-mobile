package mobile.ufrpe.br.listviewexample;

public class Pessoa {
    public String nome;
    public String sobrenome;
    public int idade;
    public String pais;

    public Pessoa(String nome, String sobrenome, int idade, String pais) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return nome + " (" + sobrenome + ")";
    }
}

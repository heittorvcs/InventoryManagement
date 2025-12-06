package model;

public class Produto implements Identificavel {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public void setNome(String nome) { this.nome = nome; } 

    @Override
    public String toString() {
        return String.format("Produto [ID=%d, Nome=%s, Preço=%.2f, Qtd=%d]", 
            id, nome, preco, quantidade);
    }
}
package model;

public class Categoria implements Identificavel {
    private int id;
    private String nomeCategoria;
    private String descricao;

    public Categoria(int id, String nomeCategoria, String descricao) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nomeCategoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public void setNomeCategoria(String nome) { this.nomeCategoria = nome; }

    @Override
    public String toString() {
        return String.format("Categoria [ID=%d, Nome=%s, Desc=%s]", 
            id, nomeCategoria, descricao);
    }
}
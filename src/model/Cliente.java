package model;

public class Cliente implements Identificavel {
    private int id;
    private String nomeCompleto;
    private String cpf;
    private String cidade;

    public Cliente(int id, String nomeCompleto, String cpf, String cidade) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cidade = cidade;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nomeCompleto; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    
    public void setNomeCompleto(String nome) { this.nomeCompleto = nome; }

    @Override
    public String toString() {
        return String.format("Cliente [ID=%d, Nome=%s, CPF=%s, Cidade=%s]", 
            id, nomeCompleto, cpf, cidade);
    }
}
package model;

public class Fornecedor implements Identificavel {
    private int id;
    private String nomeFantasia; 
    private String cnpj;
    private String email;

    public Fornecedor(int id, String nomeFantasia, String cnpj, String email) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.email = email;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nomeFantasia; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void setNomeFantasia(String nome) { this.nomeFantasia = nome; }

    @Override
    public String toString() {
        return String.format("Fornecedor [ID=%d, Nome=%s, CNPJ=%s, Email=%s]", 
            id, nomeFantasia, cnpj, email);
    }
}
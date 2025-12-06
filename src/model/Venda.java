package model;

public class Venda implements Identificavel {
    private int id;
    private String dataVenda; 
    private int idCliente;    // Quem comprou
    private int idProduto;    // O que foi comprado
    private int quantidade;
    private double valorTotal;

    public Venda(int id, String dataVenda, int idCliente, int idProduto, int quantidade, double valorTotal) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    // --- Identificavel ---
    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { 
        return "Venda do dia " + dataVenda; 
    }

    public String getDataVenda() { return dataVenda; }
    public void setDataVenda(String dataVenda) { this.dataVenda = dataVenda; }

    public int getIdCliente() { return idCliente; }
    public int getIdProduto() { return idProduto; }
    public int getQuantidade() { return quantidade; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return String.format("Venda [ID=%d, Data=%s, ClienteID=%d, ProdID=%d, Total=R$%.2f]", 
            id, dataVenda, idCliente, idProduto, valorTotal);
    }
}
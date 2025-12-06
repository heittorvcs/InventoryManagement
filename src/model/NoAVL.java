package model;

public class NoAVL<T extends Identificavel> {

    private T dado;
    private NoAVL<T> esquerdo;
    private NoAVL<T> direito;
    private int altura;

    public NoAVL(T dado) {
        this.dado = dado;
        this.esquerdo = null;
        this.direito = null;
        this.altura = 1; 
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public NoAVL<T> getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(NoAVL<T> esquerdo) {
        this.esquerdo = esquerdo;
    }

    public NoAVL<T> getDireito() {
        return direito;
    }

    public void setDireito(NoAVL<T> direito) {
        this.direito = direito;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
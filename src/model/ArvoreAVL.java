package model;

import java.io.FileWriter;
import java.io.IOException;

// <T extends Identificavel> garante que podemos chamar .getId() nos objetos
public class ArvoreAVL<T extends Identificavel> {

    private NoAVL<T> raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    // -- Métodos para altura e balanceamento --
    private int obterAltura(NoAVL<T> no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }

    private void atualizarAltura(NoAVL<T> no) {
        if (no != null) {
            no.setAltura(1 + Math.max(obterAltura(no.getEsquerdo()), obterAltura(no.getDireito())));
        }
    }
    
    private int obterFatorBalanceamento(NoAVL<T> no) {
        if (no == null) {
            return 0;
        }
        return obterAltura(no.getEsquerdo()) - obterAltura(no.getDireito());
    }
    
    private NoAVL<T> balancear(NoAVL<T> no) {
        if (no == null) return null;

        atualizarAltura(no);
        int fb = obterFatorBalanceamento(no);

        // Desequilíbrio à esquerda
        if (fb > 1) {
            if (obterFatorBalanceamento(no.getEsquerdo()) < 0) {
            	// Rotação dupla(Esquerda-Direita)
                no.setEsquerdo(rotacaoEsquerda(no.getEsquerdo()));
            }
            // Rotação simples
            return rotacaoDireita(no);
        }

        // Desequilíbrio à direita
        if (fb < -1) {
            if (obterFatorBalanceamento(no.getDireito()) > 0) {
            	// Rotação dupla(Direita-Esquerda)
                no.setDireito(rotacaoDireita(no.getDireito()));
            }
            // Rotação simples
            return rotacaoEsquerda(no);
        }

        return no;
    }
    
    // -- Rotações na árvore --
    private NoAVL<T> rotacaoDireita(NoAVL<T> y) {
        NoAVL<T> x = y.getEsquerdo();
        NoAVL<T> t2 = x.getDireito();

        x.setDireito(y);
        y.setEsquerdo(t2);

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private NoAVL<T> rotacaoEsquerda(NoAVL<T> x) {
        NoAVL<T> y = x.getDireito();
        NoAVL<T> t2 = y.getEsquerdo();

        y.setEsquerdo(x);
        x.setDireito(t2);

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    // -- Inserção --
    public void inserir(T dado) {
        this.raiz = inserirRecursivo(this.raiz, dado);
    }

    private NoAVL<T> inserirRecursivo(NoAVL<T> noAtual, T dado) {
        if (noAtual == null) {
            return new NoAVL<>(dado);
        }

        // Comparação usando o ID da interface
        if (dado.getId() < noAtual.getDado().getId()) {
            noAtual.setEsquerdo(inserirRecursivo(noAtual.getEsquerdo(), dado));
        } else if (dado.getId() > noAtual.getDado().getId()) {
            noAtual.setDireito(inserirRecursivo(noAtual.getDireito(), dado));
        } else {
            // ID duplicado: ignora
            return noAtual;
        }

        return balancear(noAtual);
    }

    // -- Busca --
    public T buscar(int id) {
        NoAVL<T> noEncontrado = buscarRecursivo(this.raiz, id);
        if (noEncontrado != null) {
            return noEncontrado.getDado();
        }
        return null;
    }

    private NoAVL<T> buscarRecursivo(NoAVL<T> noAtual, int id) {
        if (noAtual == null) {
            return null;
        }

        if (id == noAtual.getDado().getId()) {
            return noAtual;
        }

        if (id < noAtual.getDado().getId()) {
            return buscarRecursivo(noAtual.getEsquerdo(), id);
        } else {
            return buscarRecursivo(noAtual.getDireito(), id);
        }
    }
    
    // -- Listagem --
    public void listarEmOrdem() {
        if (this.raiz == null) {
            System.out.println(" (Árvore vazia)");
        } else {
            listarEmOrdemRecursivo(this.raiz);
        }
    }

    private void listarEmOrdemRecursivo(NoAVL<T> noAtual) {
        if (noAtual != null) {
            listarEmOrdemRecursivo(noAtual.getEsquerdo());
            System.out.println(noAtual.getDado().toString());
            listarEmOrdemRecursivo(noAtual.getDireito());
        }
    }
    
    // -- Remoção --
    public void remover(int id) {
        this.raiz = removerRecursivo(this.raiz, id);
    }

    private NoAVL<T> removerRecursivo(NoAVL<T> noAtual, int id) {
        if (noAtual == null) return null;

        if (id < noAtual.getDado().getId()) {
            noAtual.setEsquerdo(removerRecursivo(noAtual.getEsquerdo(), id));
        } else if (id > noAtual.getDado().getId()) {
            noAtual.setDireito(removerRecursivo(noAtual.getDireito(), id));
        } else {
            // Nó encontrado
            if ((noAtual.getEsquerdo() == null) || (noAtual.getDireito() == null)) {
                NoAVL<T> temp = null;
                if (temp == noAtual.getEsquerdo()) {
                    temp = noAtual.getDireito();
                } else {
                    temp = noAtual.getEsquerdo();
                }

                if (temp == null) {
                    temp = noAtual;
                    noAtual = null;
                } else {
                    noAtual = temp;
                }
            } else {
                // Caso com 2 filhos: pega o sucessor
                NoAVL<T> temp = encontrarMenor(noAtual.getDireito());
                noAtual.setDado(temp.getDado());
                // Remove o sucessor recursivamente
                noAtual.setDireito(removerRecursivo(noAtual.getDireito(), temp.getDado().getId()));
            }
        }

        if (noAtual == null) return null;

        return balancear(noAtual);
    }

    private NoAVL<T> encontrarMenor(NoAVL<T> no) {
        NoAVL<T> atual = no;
        while (atual.getEsquerdo() != null) {
            atual = atual.getEsquerdo();
        }
        return atual;
    }
    
    // -- Graphviz --
    public void gerarArquivoDOT(String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write("digraph AVLTree {\n");
            writer.write("  node [shape=box, style=filled, color=\"lightblue\"];\n");

            if (this.raiz == null) {
                writer.write("  null [shape=plaintext, label=\"Árvore Vazia\"];\n");
            } else {
                gerarNosERelacoesDOT(this.raiz, writer);
            }

            writer.write("}\n");
            System.out.println("Arquivo DOT gerado: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo DOT: " + e.getMessage());
        }
    }

    private void gerarNosERelacoesDOT(NoAVL<T> noAtual, FileWriter writer) throws IOException {
        if (noAtual == null) return;

        // Monta o label usando ID e Nome da interface
        String label = String.format("\"[%d] %s\\nAlt: %d\"",
                noAtual.getDado().getId(),
                noAtual.getDado().getNome(),
                noAtual.getAltura()
        );
        writer.write("  " + noAtual.getDado().getId() + " [label=" + label + "];\n");

        if (noAtual.getEsquerdo() != null) {
            writer.write("  " + noAtual.getDado().getId() + " -> " + noAtual.getEsquerdo().getDado().getId() + ";\n");
            gerarNosERelacoesDOT(noAtual.getEsquerdo(), writer);
        } else {
            writer.write("  null_esq_" + noAtual.getDado().getId() + " [shape=point, width=0.1];\n");
            writer.write("  " + noAtual.getDado().getId() + " -> null_esq_" + noAtual.getDado().getId() + " [style=invis];\n");
        }

        if (noAtual.getDireito() != null) {
            writer.write("  " + noAtual.getDado().getId() + " -> " + noAtual.getDireito().getDado().getId() + ";\n");
            gerarNosERelacoesDOT(noAtual.getDireito(), writer);
        } else {
            writer.write("  null_dir_" + noAtual.getDado().getId() + " [shape=point, width=0.1];\n");
            writer.write("  " + noAtual.getDado().getId() + " -> null_dir_" + noAtual.getDado().getId() + " [style=invis];\n");
        }
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }
}
classDiagram
    %% Interface que garante que todos tenham ID
    class Identificavel {
        <<interface>>
        +getId() int
    }

    %% Classe principal que gerencia o sistema
    class Main {
        -arvoreClientes : ArvoreAVL~Cliente~
        -arvoreProdutos : ArvoreAVL~Produto~
        -arvoreFornecedores : ArvoreAVL~Fornecedor~
        -arvoreCategorias : ArvoreAVL~Categoria~
        -arvoreVendas : ArvoreAVL~Venda~
        +main(String[] args)
        -menuCliente()
        -menuProduto()
        -menuFornecedor()
        -menuCategoria()
        -menuVenda()
    }

    %% Estrutura de Dados (Genérica)
    class NoAVL~T~ {
        -dado : T
        -esquerdo : NoAVL~T~
        -direito : NoAVL~T~
        -altura : int
        +getDado() T
        +getAltura() int
        +setAltura(int)
    }

    class ArvoreAVL~T~ {
        -raiz : NoAVL~T~
        +inserir(T dado)
        +remover(int id)
        +buscar(int id) T
        +atualizar(T dado)
        +listarEmOrdem()
        +imprimirEstrutura()
        -balancear(NoAVL) NoAVL
        -rotacaoDireita(NoAVL) NoAVL
        -rotacaoEsquerda(NoAVL) NoAVL
    }

    %% Entidades do Sistema
    class Cliente {
        -id : int
        -nome : String
        -cpf : String
        -email : String
        +getId() int
        +toString() String
    }

    class Produto {
        -id : int
        -nome : String
        -preco : double
        -quantidade : int
        +getId() int
        +toString() String
    }

    class Fornecedor {
        -id : int
        -nome : String
        -cnpj : String
        +getId() int
        +toString() String
    }

    class Categoria {
        -id : int
        -nome : String
        -descricao : String
        +getId() int
        +toString() String
    }

    class Venda {
        -id : int
        -idCliente : int
        -total : double
        -data : Date
        +getId() int
        +toString() String
    }

    %% RELACIONAMENTOS

    %% Implementação da Interface (..|>)
    Identificavel <|.. Cliente
    Identificavel <|.. Produto
    Identificavel <|.. Fornecedor
    Identificavel <|.. Categoria
    Identificavel <|.. Venda

    %% Composição e Agregação
    %% Arvore tem um No Raiz
    ArvoreAVL *-- NoAVL : contem raiz
    
    %% O No contem um dado T que deve ser Identificavel
    NoAVL o-- Identificavel : armazena

    %% A Main usa várias árvores
    Main --> ArvoreAVL : instancia 5 arvores
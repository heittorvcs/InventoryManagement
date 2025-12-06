package app;

import model.*;
import java.util.Scanner;

public class Main {

    // Instancia as 5 árvores genéricas
    private static ArvoreAVL<Produto> arvoreProdutos = new ArvoreAVL<>();
    private static ArvoreAVL<Fornecedor> arvoreFornecedores = new ArvoreAVL<>();
    private static ArvoreAVL<Cliente> arvoreClientes = new ArvoreAVL<>();
    private static ArvoreAVL<Categoria> arvoreCategorias = new ArvoreAVL<>();
    private static ArvoreAVL<Venda> arvoreVendas = new ArvoreAVL<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // popularDadosIniciais();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===========================");
            System.out.println("   SISTEMA DE INVENTÁRIO   ");
            System.out.println("===========================");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Fornecedores");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("4. Gerenciar Categorias");
            System.out.println("5. Gerenciar Vendas");
            System.out.println("0. Sair");
            System.out.print(">>> Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1: menuProduto(); break;
                case 2: menuFornecedor(); break;
                case 3: menuCliente(); break;
                case 4: menuCategoria(); break;
                case 5: menuVenda(); break;
                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    // 1. Menu de produtos
    private static void menuProduto() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GESTÃO DE PRODUTOS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar (ID)");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar Todos");
            System.out.println("6. Gerar Gráfico (.dot)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = lerInteiro();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = lerDouble();
                    System.out.print("Quantidade: ");
                    int qtd = lerInteiro();
                    arvoreProdutos.inserir(new Produto(id, nome, preco, qtd));
                    System.out.println("Produto salvo!");
                    break;
                case 2:
                    System.out.print("ID do Produto: ");
                    Produto p = arvoreProdutos.buscar(lerInteiro());
                    System.out.println(p != null ? p : "Não encontrado.");
                    break;
                case 3:
                    System.out.print("ID para atualizar: ");
                    Produto pAt = arvoreProdutos.buscar(lerInteiro());
                    if (pAt != null) {
                        System.out.println("Atualizando: " + pAt.getNome());
                        
                        System.out.print("Novo Nome (Enter para manter): ");
                        String nNome = scanner.nextLine();
                        if (!nNome.isEmpty()) pAt.setNome(nNome);
                        
                        System.out.print("Novo Preço (-1 para manter): ");
                        double nPreco = lerDouble();
                        if (nPreco >= 0) pAt.setPreco(nPreco);
                        
                        System.out.println("Atualizado com sucesso!");
                    } else System.out.println("Não encontrado.");
                    break;
                case 4:
                    System.out.print("ID para remover: ");
                    arvoreProdutos.remover(lerInteiro());
                    System.out.println("Operação de remoção concluída.");
                    break;
                case 5:
                    arvoreProdutos.listarEmOrdem();
                    break;
                case 6:
                    arvoreProdutos.gerarArquivoDOT("produtos.dot");
                    break;
            }
        }
    }

    // 2. Menu de fornecedores
    private static void menuFornecedor() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GESTÃO DE FORNECEDORES ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar Todos");
            System.out.println("6. Gerar Gráfico (.dot)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = lerInteiro();
                    System.out.print("Nome Fantasia: ");
                    String nome = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    arvoreFornecedores.inserir(new Fornecedor(id, nome, cnpj, email));
                    System.out.println("Fornecedor salvo!");
                    break;
                case 2:
                    System.out.print("ID do Fornecedor: ");
                    Fornecedor f = arvoreFornecedores.buscar(lerInteiro());
                    System.out.println(f != null ? f : "Não encontrado.");
                    break;
                case 3:
                    System.out.print("ID para atualizar: ");
                    Fornecedor fAt = arvoreFornecedores.buscar(lerInteiro());
                    if (fAt != null) {
                        System.out.print("Novo Nome (Enter para manter): ");
                        String nNome = scanner.nextLine();
                        if (!nNome.isEmpty()) fAt.setNomeFantasia(nNome);

                        System.out.print("Novo Email (Enter para manter): ");
                        String nEmail = scanner.nextLine();
                        if (!nEmail.isEmpty()) fAt.setEmail(nEmail);
                        
                        System.out.println("Atualizado!");
                    } else System.out.println("Não encontrado.");
                    break;
                case 4:
                    System.out.print("ID para remover: ");
                    arvoreFornecedores.remover(lerInteiro());
                    System.out.println("Remoção concluída.");
                    break;
                case 5:
                    arvoreFornecedores.listarEmOrdem();
                    break;
                case 6:
                    arvoreFornecedores.gerarArquivoDOT("fornecedores.dot");
                    break;
            }
        }
    }

    // 3. Menu de clientes
    private static void menuCliente() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GESTÃO DE CLIENTES ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar Todos");
            System.out.println("6. Gerar Gráfico (.dot)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = lerInteiro();
                    System.out.print("Nome Completo: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();
                    arvoreClientes.inserir(new Cliente(id, nome, cpf, cidade));
                    System.out.println("Cliente salvo!");
                    break;
                case 2:
                    System.out.print("ID do Cliente: ");
                    Cliente c = arvoreClientes.buscar(lerInteiro());
                    System.out.println(c != null ? c : "Não encontrado.");
                    break;
                case 3:
                    System.out.print("ID para atualizar: ");
                    Cliente cAt = arvoreClientes.buscar(lerInteiro());
                    if (cAt != null) {
                        System.out.print("Novo Nome (Enter para manter): ");
                        String nNome = scanner.nextLine();
                        if (!nNome.isEmpty()) cAt.setNomeCompleto(nNome);

                        System.out.print("Nova Cidade (Enter para manter): ");
                        String nCidade = scanner.nextLine();
                        if (!nCidade.isEmpty()) cAt.setCidade(nCidade);
                        System.out.println("Atualizado!");
                    } else System.out.println("Não encontrado.");
                    break;
                case 4:
                    System.out.print("ID para remover: ");
                    arvoreClientes.remover(lerInteiro());
                    System.out.println("Remoção concluída.");
                    break;
                case 5:
                    arvoreClientes.listarEmOrdem();
                    break;
                case 6:
                    arvoreClientes.gerarArquivoDOT("clientes.dot");
                    break;
            }
        }
    }

    // 4. Menu de categorias
    private static void menuCategoria() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GESTÃO DE CATEGORIAS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("5. Listar Todos");
            System.out.println("6. Gerar Gráfico (.dot)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int id = lerInteiro();
                    System.out.print("Nome da Categoria: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    arvoreCategorias.inserir(new Categoria(id, nome, desc));
                    System.out.println("Categoria salva!");
                    break;
                case 2:
                    System.out.print("ID da Categoria: ");
                    Categoria c = arvoreCategorias.buscar(lerInteiro());
                    System.out.println(c != null ? c : "Não encontrada.");
                    break;
                case 3:
                    System.out.print("ID para atualizar: ");
                    Categoria cAt = arvoreCategorias.buscar(lerInteiro());
                    if (cAt != null) {
                        System.out.print("Novo Nome (Enter para manter): ");
                        String nNome = scanner.nextLine();
                        if (!nNome.isEmpty()) cAt.setNomeCategoria(nNome);
                        
                        System.out.print("Nova Descrição (Enter para manter): ");
                        String nDesc = scanner.nextLine();
                        if (!nDesc.isEmpty()) cAt.setDescricao(nDesc);
                        System.out.println("Atualizado!");
                    } else System.out.println("Não encontrada.");
                    break;
                case 4:
                    System.out.print("ID para remover: ");
                    arvoreCategorias.remover(lerInteiro());
                    System.out.println("Remoção concluída.");
                    break;
                case 5:
                    arvoreCategorias.listarEmOrdem();
                    break;
                case 6:
                    arvoreCategorias.gerarArquivoDOT("categorias.dot");
                    break;
            }
        }
    }


    // 5. Menu de vendas
    private static void menuVenda() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GESTÃO DE VENDAS ---");
            System.out.println("1. Registrar Nova Venda");
            System.out.println("2. Buscar Venda");
            System.out.println("3. Remover Venda");
            System.out.println("4. Listar Todas");
            System.out.println("5. Gerar Gráfico (.dot)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            switch (op) {
                case 1:
                    // Verifica integridade: Venda precisa de Cliente e Produto existentes
                    System.out.print("ID do Cliente: ");
                    int idCli = lerInteiro();
                    if (arvoreClientes.buscar(idCli) == null) {
                        System.out.println("ERRO: Cliente não encontrado. Cadastre o cliente primeiro.");
                        break;
                    }

                    System.out.print("ID do Produto: ");
                    int idProd = lerInteiro();
                    Produto prod = arvoreProdutos.buscar(idProd);
                    if (prod == null) {
                        System.out.println("ERRO: Produto não encontrado.");
                        break;
                    }

                    System.out.print("ID da Venda: ");
                    int id = lerInteiro();
                    System.out.print("Data (dd/mm/aaaa): ");
                    String data = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int qtd = lerInteiro();
                    
                    double total = prod.getPreco() * qtd;
                    System.out.printf("Valor Total Calculado: R$ %.2f\n", total);
                    
                    arvoreVendas.inserir(new Venda(id, data, idCli, idProd, qtd, total));
                    System.out.println("Venda registrada com sucesso!");
                    break;

                case 2:
                    System.out.print("ID da Venda: ");
                    Venda v = arvoreVendas.buscar(lerInteiro());
                    System.out.println(v != null ? v : "Venda não encontrada.");
                    break;
                
                case 3:
                    System.out.print("ID da Venda para cancelar/remover: ");
                    arvoreVendas.remover(lerInteiro());
                    System.out.println("Remoção concluída.");
                    break;

                case 4:
                    arvoreVendas.listarEmOrdem();
                    break;
                
                case 5:
                    arvoreVendas.gerarArquivoDOT("vendas.dot");
                    break;
            }
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static double lerDouble() {
        try {
            // Aceita tanto ponto quanto vírgula
            String input = scanner.nextLine().replace(",", ".");
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
    
    // Método auxiliar para não começar com o sistema vazio 
    private static void popularDadosIniciais() {
        arvoreProdutos.inserir(new Produto(101, "Notebook", 3500.00, 10));
        arvoreProdutos.inserir(new Produto(102, "Mouse", 50.00, 100));
        arvoreClientes.inserir(new Cliente(1, "João Silva", "123.456.789-00", "São Paulo"));
        arvoreFornecedores.inserir(new Fornecedor(50, "Tech Distributor", "00.000.000/0001-99", "contato@tech.com"));
    }
}
package zad_inventory.menu;

import zad_inventory.controller.OperacaoController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.entity.UsuarioEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuFuncionario {

    private final UsuarioEntity usuarioLogado;
    private final Scanner scanner = new Scanner(System.in);
    private final OperacaoController operacaoController;
    private final ProdutoController produtoController;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public MenuFuncionario(UsuarioEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.operacaoController = new OperacaoController(usuarioLogado);
        this.produtoController = new ProdutoController(usuarioLogado);
    }

    public void exibir() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU FUNCIONÁRIO (" + usuarioLogado.getNome() + ") =========");
            System.out.println("1 - Listar Produtos Disponíveis");
            System.out.println("2 - Registrar Nova Venda");
            System.out.println("3 - Listar Vendas Registradas");
            System.out.println("0 - Sair (Logout)");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    listarProdutosDisponiveis();
                    break;
                case "2":
                    registrarNovaVendaFuncionario();
                    break;
                case "3":
                    listarVendasRegistradasFuncionario();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Fazendo logout...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }





    private void listarProdutosDisponiveis() {
        System.out.println("\n--- LISTA DE PRODUTOS DISPONÍVEIS ---");
        try {
            produtoController.listarProdutos();

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private void registrarNovaVendaFuncionario() {
        System.out.println("\n--- REGISTRAR NOVA VENDA ---");
        try {
            System.out.print("Digite o ID do produto para a venda: ");
            Long produtoId = Long.parseLong(scanner.nextLine());

            System.out.print("Digite a quantidade a ser vendida: ");
            int quantidade = Integer.parseInt(scanner.nextLine());


            OperacaoEntity operacaoRegistrada = operacaoController.registrarOperacao(produtoId, quantidade);

            System.out.println("Venda registrada com sucesso!");

            System.out.println("Detalhes: " + formatarOperacaoSimples(operacaoRegistrada));

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida para ID do produto ou quantidade. Use apenas números.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao registrar a venda: " + e.getMessage());
        }
    }

    private void listarVendasRegistradasFuncionario() {
        System.out.println("\n--- VENDAS REGISTRADAS ---");
        try {

            List<OperacaoEntity> operacoes = operacaoController.listarOperacoes();

            if (operacoes.isEmpty()) {
                System.out.println("Nenhuma venda registrada no sistema.");
            } else {

                System.out.printf("%-7s | %-25s | %-20s | %-12s | %-5s | %-16s\n",
                        "ID Venda", "Produto", "Vendedor", "Situação", "Qtd", "Data/Hora");
                System.out.println("-".repeat(7 + 3 + 25 + 3 + 20 + 3 + 12 + 3 + 5 + 3 + 16));

                for (OperacaoEntity op : operacoes) {
                    String nomeProduto = (op.getProduto() != null && op.getProduto().getNomeProduto() != null)
                            ? op.getProduto().getNomeProduto() : "N/D";

                    String nomeVendedor = (op.getUsuario() != null && op.getUsuario().getNome() != null)
                            ? op.getUsuario().getNome() : "N/D";
                    String situacao = (op.getSituacao() != null) ? op.getSituacao().toString() : "N/D";
                    String dataFormatada = (op.getData() != null) ? op.getData().format(DATE_TIME_FORMATTER) : "N/D";

                    System.out.printf("%-7d | %-25.25s | %-20.20s | %-12.12s | %-5d | %-16s\n",
                            op.getId(),
                            nomeProduto,
                            nomeVendedor,
                            situacao,
                            op.getQuantidade(),
                            dataFormatada);
                }
                System.out.println("-".repeat(7 + 3 + 25 + 3 + 20 + 3 + 12 + 3 + 5 + 3 + 16));
                System.out.println("Total de vendas listadas: " + operacoes.size());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
    }


    private String formatarOperacaoSimples(OperacaoEntity op) {
        if (op == null) return "Dados da operação indisponíveis.";

        String produtoNome = (op.getProduto() != null && op.getProduto().getNomeProduto() != null)
                ? op.getProduto().getNomeProduto() : "N/D";

        Long produtoId = (op.getProduto() != null) ? op.getProduto().getId() : -1L;

        String usuarioNome = (op.getUsuario() != null && op.getUsuario().getNome() != null)
                ? op.getUsuario().getNome() : "N/D";
        Long usuarioId = (op.getUsuario() != null) ? op.getUsuario().getId() : -1L;

        String dataFormatada = (op.getData() != null)
                ? op.getData().format(DATE_TIME_FORMATTER) : "N/D";

        return String.format(
                "ID Venda: %d | Produto: %s (ID: %d) | Vendedor: %s (ID: %d) | Situação: %s | Qtd: %d | Data: %s",
                op.getId(),
                produtoNome,
                produtoId,
                usuarioNome,
                usuarioId,
                op.getSituacao() != null ? op.getSituacao().toString() : "N/D",
                op.getQuantidade(),
                dataFormatada
        );
    } }

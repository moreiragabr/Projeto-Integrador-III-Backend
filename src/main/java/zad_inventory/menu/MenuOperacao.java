package zad_inventory.menu;

import java.util.List;
import java.util.Scanner;
import zad_inventory.controller.OperacaoController;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.Situacao;
import java.time.format.DateTimeFormatter;

public class MenuOperacao {


    private final Scanner scanner;
    private final OperacaoController controller;

    public MenuOperacao(UsuarioEntity usuarioLogado) {
        this.scanner = new Scanner(System.in);

        this.controller = new OperacaoController(usuarioLogado);
    }

    public void exibirMenu() { // Renomeado de Operacoes para exibirMenu para clareza
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU DE OPERAÇÕES ===");
            System.out.println("1. Registrar Nova Operação de Venda");
            System.out.println("2. Listar Todas as Operações");
            System.out.println("3. Buscar Operação por ID");
            System.out.println("4. Atualizar Situação de Operação");
            System.out.println("5. Filtrar Operações por Situação");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> registrarNovaOperacao();
                case "2" -> listarTodasOperacoes();
                case "3" -> buscarOperacaoPorIdView();
                case "4" -> atualizarSituacaoOperacaoView();
                case "5" -> filtrarOperacoesPorSituacaoView();
                case "0" -> {
                    System.out.println("Voltando ao menu principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registrarNovaOperacao() {
        try {
            System.out.print("Digite o ID do produto: ");
            Long produtoId = Long.parseLong(scanner.nextLine());

            System.out.print("Digite a quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            OperacaoEntity operacaoRegistrada = controller.registrarOperacao(produtoId, quantidade);
            System.out.println("Operação registrada com sucesso!");
            System.out.println("Detalhes: " + formatarOperacao(operacaoRegistrada));

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida para ID ou quantidade. Use apenas números.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro ao registrar operação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());

        }
    }

    private void listarTodasOperacoes() {
        System.out.println("\n--- LISTA DE TODAS AS OPERAÇÕES ---");
        try {
            List<OperacaoEntity> operacoes = controller.listarOperacoes();
            if (operacoes.isEmpty()) {
                System.out.println("Nenhuma operação registrada no sistema.");
                return;
            }

            // Cabeçalho
            System.out.printf("%-7s | %-25s | %-20s | %-12s | %-5s | %-16s\n",
                    "ID Operação", "Produto", "Usuário", "Situação", "Qtd", "Data/Hora");
            System.out.println("-".repeat(7 + 3 + 25 + 3 + 20 + 3 + 12 + 3 + 5 + 3 + 16)); // Linha separadora TOPO

            //Dados
            for (OperacaoEntity op : operacoes) {
                String nomeProduto = (op.getProduto() != null && op.getProduto().getNomeProduto() != null)
                        ? op.getProduto().getNomeProduto() : "N/D";
                String nomeUsuario = (op.getUsuario() != null && op.getUsuario().getNome() != null)
                        ? op.getUsuario().getNome() : "N/D";
                String situacao = (op.getSituacao() != null) ? op.getSituacao().toString() : "N/D";
                String dataFormatada = (op.getData() != null) ? op.getData().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "N/D";

                System.out.printf("%-7d | %-25.25s | %-20.20s | %-12.12s | %-5d | %-16s\n",
                        op.getId(),
                        nomeProduto,
                        nomeUsuario,
                        situacao,
                        op.getQuantidade(),
                        dataFormatada);
            }
            System.out.println("-".repeat(7 + 3 + 25 + 3 + 20 + 3 + 12 + 3 + 5 + 3 + 16)); // Linha separadora FUNDO
            System.out.println("Total de operações: " + operacoes.size());

        } catch (Exception e) {
            System.out.println("Erro ao listar operações: " + e.getMessage());
        }
    }

    private void buscarOperacaoPorIdView() {
        try {
            System.out.print("Digite o ID da operação a ser buscada: ");
            Long id = Long.parseLong(scanner.nextLine());

            OperacaoEntity op = controller.buscarOperacaoPorId(id);

            System.out.println("Operação encontrada:");
            System.out.println(formatarOperacao(op));

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, use apenas números.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar a operação: " + e.getMessage());
        }
    }

    private void atualizarSituacaoOperacaoView() {
        try {
            System.out.print("Digite o ID da operação para atualizar a situação: ");
            Long id = Long.parseLong(scanner.nextLine());

            System.out.print("Digite a nova situação (ex: REALIZADA, CANCELADA, SEPARADA): ");
            String situacaoStr = scanner.nextLine().toUpperCase();
            Situacao novaSituacao = Situacao.valueOf(situacaoStr); // Pode lançar IllegalArgumentException

            OperacaoEntity operacaoAtualizada = controller.atualizarSituacao(id, novaSituacao);
            System.out.println("Situação da operação atualizada com sucesso!");
            System.out.println("Novos detalhes: " + formatarOperacao(operacaoAtualizada));

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Use apenas números.");
        } catch (IllegalArgumentException e) { // Captura Situacao.valueOf() ou erros do controller/service
            System.out.println("Erro ao atualizar situação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao atualizar a situação: " + e.getMessage());
        }
    }

    private void filtrarOperacoesPorSituacaoView() {
        try {
            System.out.print("Digite a situação para filtrar (ex: REALIZADA, CANCELADA, SEPARADA): ");
            String situacaoStr = scanner.nextLine().toUpperCase();
            Situacao filtro = Situacao.valueOf(situacaoStr); // Pode lançar IllegalArgumentException

            List<OperacaoEntity> resultado = controller.filtrarPorSituacao(filtro);

            if (resultado.isEmpty()) {
                System.out.println("Nenhuma operação encontrada com a situação: " + filtro);
            } else {
                System.out.println("\n=== OPERAÇÕES FILTRADAS POR SITUAÇÃO: " + filtro + " ===");
                for (OperacaoEntity op : resultado) {
                    System.out.println(formatarOperacao(op));
                }
            }
        } catch (IllegalArgumentException e) { // Captura Situacao.valueOf() ou erros do controller/service
            System.out.println("Erro ao filtrar operações: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao filtrar operações: " + e.getMessage());
        }
    }

    // Método utilitário para formatação consistente
    private String formatarOperacao(OperacaoEntity op) {
        if (op == null) return "Dados da operação indisponíveis.";
        return String.format(
                "ID: %d | Produto: %s (ID: %d) | Usuário: %s (ID: %d) | Situação: %s | Quantidade: %d | Data: %s",
                op.getId(),
                op.getProduto() != null ? op.getProduto().getNomeProduto() : "N/D",
                op.getProduto() != null ? op.getProduto().getId() : -1,
                op.getUsuario() != null ? op.getUsuario().getNome() : "N/D",
                op.getUsuario() != null ? op.getUsuario().getId() : -1, // Assumindo que UsuarioEntity tem getId()
                op.getSituacao(),
                op.getQuantidade(),
                op.getData() != null ? op.getData().toString() : "N/D"
        );
    }
}
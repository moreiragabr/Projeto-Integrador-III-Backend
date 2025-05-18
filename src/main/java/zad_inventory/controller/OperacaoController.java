package zad_inventory.controller;

import java.util.List;
import java.util.Scanner;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.Situacao;
import zad_inventory.service.OperacaoService;

public class OperacaoController {

    private final OperacaoService service;
    private final Scanner scanner;
    private final UsuarioEntity usuarioLogado;

    public OperacaoController(UsuarioEntity usuarioLogado) {
        this.service = new OperacaoService();
        this.scanner = new Scanner(System.in);
        this.usuarioLogado = usuarioLogado;
    }

    public void registrarOperacao() {
        System.out.print("ID do produto: ");
        try {
            Long produtoId = Long.parseLong(scanner.nextLine());
            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            service.registrarVenda(usuarioLogado, produtoId, quantidade);
            System.out.println("Operação registrada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: use apenas números.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar operação: " + e.getMessage());
        }
    }


    public void listarOperacoes() {
        try {
            List<OperacaoEntity> ops = service.buscarTodos();
            if (ops.isEmpty()) {
                System.out.println("Nenhuma operação registrada.");
            } else {
                System.out.println("\n=== LISTA DE OPERAÇÕES ===");
                for (OperacaoEntity op : ops) {
                    System.out.printf(
                            "ID: %d | Produto: %s | Usuário: %s | Situação: %s | Quantidade: %d | Data: %s%n",
                            op.getId(),
                            op.getProduto().getNomeProduto(),
                            op.getUsuario().getNome(),
                            op.getSituacao(),
                            op.getQuantidade(),
                            op.getData()
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar operações: " + e.getMessage());
        }
    }


    public void buscarOperacaoPorId() {
        System.out.print("Digite o ID da operação: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            OperacaoEntity op = service.buscarPorId(id);
            if (op != null) {
                System.out.printf(
                        "Operação encontrada: ID %d | Produto: %s | Usuário: %s | Situação: %s | Quantidade: %d | Data: %s%n",
                        op.getId(),
                        op.getProduto().getNomeProduto(),
                        op.getUsuario().getNome(),
                        op.getSituacao(),
                        op.getQuantidade(),
                        op.getData()
                );
            } else {
                System.out.println("Operação não encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Use apenas números.");
        } catch (Exception e) {
            System.out.println("Erro ao buscar operação: " + e.getMessage());
        }
    }


    public void atualizarSituacao() {
        System.out.print("Digite o ID da operação a ser atualizada: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            System.out.print("Nova situação (REALIZADA, CANCELADA, SEPARADA): ");
            Situacao situacao = Situacao.valueOf(scanner.nextLine().toUpperCase());

            service.atualizarSituacao(id, situacao);
            System.out.println("Situação atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao atualizar situação: " + e.getMessage());
        }
    }


    public void filtrarPorSituacao() {
        System.out.print("Situação para filtrar (REALIZADA, CANCELADA, SEPARADA): ");
        try {
            Situacao filtro = Situacao.valueOf(scanner.nextLine().toUpperCase());
            List<OperacaoEntity> resultado = service.filtrarPorSituacao(filtro);
            if (resultado.isEmpty()) {
                System.out.println("Nenhuma operação com esta situação.");
            } else {
                System.out.println("\n=== OPERAÇÕES FILTRADAS ===");
                for (OperacaoEntity op : resultado) {
                    System.out.printf(
                            "ID: %d | Produto: %s | Usuário: %s | Situação: %s | Quantidade: %d | Data: %s%n",
                            op.getId(),
                            op.getProduto().getNomeProduto(),
                            op.getUsuario().getNome(),
                            op.getSituacao(),
                            op.getQuantidade(),
                            op.getData()
                    );
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Situação inválida.");
        } catch (Exception e) {
            System.out.println("Erro ao filtrar operações: " + e.getMessage());
        }
    }
}

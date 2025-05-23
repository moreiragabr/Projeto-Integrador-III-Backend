package zad_inventory.controller;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.service.UsuarioService;
import zad_inventory.repository.UsuarioRepository;

import java.util.List;
import java.util.Scanner;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final Scanner scanner;

    public UsuarioController(UsuarioService usuarioService, Scanner scanner) {
        this.usuarioService = usuarioService;
        this.scanner = scanner;
    }

    public void cadastrarNovoUsuario(UsuarioEntity usuarioLogado) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Formato de email inválido!");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo (GERENTE ou FUNCIONARIO): ");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
            UsuarioEntity novoUsuario = new UsuarioEntity(nome, email, senha, tipoUsuario);
            UsuarioEntity usuarioRegistrado = usuarioService.registrarUsuario(novoUsuario, usuarioLogado);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("No enum constant")) {
                System.out.println("Tipo de usuário inválido. Use GERENTE ou FUNCIONARIO.");
            } else {
                System.out.println("Erro: " + e.getMessage());
            }
        } catch (SecurityException e) {
            System.out.println("Erro de permissão: " + e.getMessage());
        }
    }

    public void listarUsuarios() {
        try {
            List<UsuarioEntity> usuarios = usuarioService.listarTodos();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
            } else {
                System.out.println("\n--- Lista de Usuários ---");
                usuarios.forEach(u -> System.out.printf("ID: %d | Nome: %s | Email: %s | Tipo: %s%n",
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        u.getTipoUsuario()));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public void exibirRanking() {
        try {
            List<UsuarioEntity> ranking = usuarioService.listarRankingUsuarios();
            System.out.println("\n--- Ranking de Usuários por Produtos Cadastrados ---");
            int rank = 1;
            for (UsuarioEntity u : ranking) {
                System.out.printf("#%d | ID: %d | Nome: %s | Tipo: %s | Total de Produtos: %d%n",
                        rank++,
                        u.getId(),
                        u.getNome(),
                        u.getTipoUsuario(),
                        u.getTotalProdutos() != null ? u.getTotalProdutos() : 0);
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir ranking: " + e.getMessage());
        }
    }
}
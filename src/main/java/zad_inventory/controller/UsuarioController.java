package zad_inventory.controller;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.service.UsuarioService;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.config.DBConnection;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController() {
        EntityManager em = DBConnection.getEntityManager();
        this.usuarioService = new UsuarioService(new UsuarioRepository(em));
    }

    public UsuarioEntity registrarUsuario(UsuarioEntity novoUsuario, UsuarioEntity solicitante) {
        try {
            UsuarioEntity usuarioRegistrado = usuarioService.registrarUsuario(novoUsuario, solicitante);
            System.out.println("Usuário registrado com sucesso!");
            return usuarioRegistrado;
        } catch (SecurityException e) {
            System.out.println("Erro de permissão: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao registrar usuário: " + e.getMessage());
        }
        return null;
    }

    public void removerUsuario(Long id, UsuarioEntity solicitante) {
        try {
            usuarioService.removerUsuario(id, solicitante);
            System.out.println("Usuário removido com sucesso!");
        } catch (SecurityException e) {
            System.out.println("Erro de permissão: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }

    public UsuarioEntity buscarPorId(Long id) {
        try {
            UsuarioEntity usuario = usuarioService.buscarPorId(id);
            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
            }
            return usuario;
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }

    public UsuarioEntity buscarPorIdComProdutos(Long id) {
        try {
            return usuarioService.buscarPorIdComProdutos(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário com produtos: " + e.getMessage());
            return null;
        }
    }

    public List<UsuarioEntity> listarTodos() {
        try {
            return usuarioService.listarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
            return null;
        }
    }

    public List<UsuarioEntity> listarComTotalProdutos() {
        try {
            return usuarioService.listarComTotalProdutos();
        } catch (Exception e) {
            System.out.println("Erro ao listar usuários com total de produtos: " + e.getMessage());
            return null;
        }
    }

    public List<UsuarioEntity> listarRankingUsuarios() {
        try {
            return usuarioService.listarRankingUsuarios();
        } catch (Exception e) {
            System.out.println("Erro ao gerar ranking de usuários: " + e.getMessage());
            return null;
        }
    }

    public UsuarioEntity buscarPorEmail(String email) {
        try {
            return usuarioService.buscarPorEmail(email);
        } catch (IllegalArgumentException e) {
            System.out.println("Email inválido: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
            return null;
        }
    }

    public UsuarioEntity atualizarUsuario(UsuarioEntity usuarioAtualizado) {
        try {
            UsuarioEntity usuario = usuarioService.atualizarUsuario(usuarioAtualizado);
            System.out.println("Usuário atualizado com sucesso!");
            return usuario;
        } catch (IllegalArgumentException e) {
            System.out.println("Dados inválidos: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return null;
        }
    }
}
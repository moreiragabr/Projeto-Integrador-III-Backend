package zad_inventory.model.service;

import zad_inventory.model.config.DBConnection;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.model.enums.TipoUsuario;
import zad_inventory.model.repository.UsuarioRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        EntityManager em = DBConnection.getEntityManager();
        this.usuarioRepository = new UsuarioRepository(em);
    }

    // Métodos básicos de CRUD
    public UsuarioEntity registrarUsuario(UsuarioEntity novoUsuario, UsuarioEntity solicitante) {
        if (solicitante.getTipoUsuario() != TipoUsuario.GERENTE) {
            JOptionPane.showMessageDialog(null, "Apenas gerentes porem cadastrar usuários!");
        }

        if (usuarioRepository.buscarPorEmail(novoUsuario.getEmail()) != null) {
            JOptionPane.showMessageDialog(null, "Email já cadastrado!");
        }

        usuarioRepository.salvar(novoUsuario);
        return novoUsuario;
    }

    public void removerUsuario(Long id, UsuarioEntity solicitante) {
        if (solicitante.getTipoUsuario() != TipoUsuario.GERENTE) {
            throw new SecurityException("Apenas gerentes podem remover usuários");
        }

        UsuarioEntity usuario = buscarPorId(id);
        usuarioRepository.remover(usuario);
    }

    public UsuarioEntity buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return usuarioRepository.buscarPorId(id);
    }

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    public List<UsuarioEntity> listarComTotalProdutos() {
        return usuarioRepository.listarComTotalProdutos();
    }

    public List<UsuarioEntity> listarRankingUsuarios() {
        return listarComTotalProdutos().stream()
                .sorted((u1, u2) -> Long.compare(u2.getTotalProdutos(), u1.getTotalProdutos()))
                .collect(Collectors.toList());
    }

    public UsuarioEntity buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        return usuarioRepository.buscarPorEmail(email);
    }

    public UsuarioEntity atualizarUsuario(UsuarioEntity usuarioAtualizado) {
        if (usuarioAtualizado.getId() == null) {
            throw new IllegalArgumentException("Usuário deve ter ID para atualização");
        }

        UsuarioEntity usuarioExistente = buscarPorId(usuarioAtualizado.getId());
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());

        usuarioRepository.salvar(usuarioExistente);
        return usuarioExistente;
    }
}
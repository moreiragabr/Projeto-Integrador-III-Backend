package zad_inventory.service;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public void registrarUsuario(UsuarioEntity novoUsuario, UsuarioEntity solicitante) {
        if (solicitante.getTipoUsuario() != TipoUsuario.GERENTE) {
            throw new RuntimeException("Apenas gerentes podem registrar novos usuários.");
        }
        usuarioRepo.salvar(novoUsuario);
    }

    public void deletarUsuario(Long id, UsuarioEntity solicitante) {
        if (solicitante.getTipoUsuario() != TipoUsuario.GERENTE) {
            throw new RuntimeException("Apenas gerentes podem deletar usuários.");
        }

        UsuarioEntity usuario = usuarioRepo.buscarPorId(id);
        if (usuario != null) {
            usuarioRepo.remover(usuario);
        }
    }

    public UsuarioEntity buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca de usuário");
        }
        return usuarioRepo.buscarPorId(id);
    }

    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepo.listarTodos();
    }

    public List<UsuarioEntity> listarRankingUsuariosPorProdutos() {
        List<UsuarioEntity> usuarios = listarUsuarios();

        usuarios.sort((u1, u2) -> {
            Long total1 = u1.getTotalProdutos() != null ? u1.getTotalProdutos() : 0;
            Long total2 = u2.getTotalProdutos() != null ? u2.getTotalProdutos() : 0;
            return Long.compare(total2, total1); // decrescente
        });

        return usuarios;
    }


    public UsuarioEntity buscarPorEmail(String email) {
        return usuarioRepo.buscarPorEmail(email);
    }
}

package zad_inventory.service;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.entity.UsuarioEntity.TipoUsuario;
import zad_inventory.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

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

    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepo.listarTodos();
    }

    public UsuarioEntity buscarPorEmail(String email) {
        return usuarioRepo.buscarPorEmail(email);
    }
}

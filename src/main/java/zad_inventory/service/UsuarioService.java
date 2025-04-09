package zad_inventory.service;

import zad_inventory.entity.Usuario;
import zad_inventory.entity.Usuario.TipoUsuario;
import zad_inventory.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void registrarUsuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        if (repository.buscarPorEmail(email) != null) {
            throw new RuntimeException("Email já está em uso.");
        }

        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(senha);
        novo.setTipoUsuario(tipoUsuario);

        repository.salvar(novo);
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = repository.buscarPorEmail(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Credenciais inválidas.");
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodos();
    }

    public boolean removerUsuario(Long id) {
        return repository.removerPorId(id);
    }
}

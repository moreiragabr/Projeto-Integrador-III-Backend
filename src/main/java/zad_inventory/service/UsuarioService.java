package zad_inventory.service;

import zad_inventory.entity.Usuario;
import zad_inventory.entity.Usuario.TipoUsuario;
import zad_inventory.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        Optional<Usuario> existente = usuarioRepository.buscarPorEmail(email);
        if (existente.isPresent()) {
            throw new RuntimeException("Já existe um usuário com esse email!");
        }

        Usuario novoUsuario = new Usuario(null, nome, email, senha, tipoUsuario);
        return usuarioRepository.salvar(novoUsuario);
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.buscarPorEmail(email);
        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            return usuario.get();
        }
        throw new RuntimeException("Email ou senha inválidos.");
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarTodos();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public boolean removerUsuario(Long id) {
        return usuarioRepository.deletarPorId(id);
    }
}

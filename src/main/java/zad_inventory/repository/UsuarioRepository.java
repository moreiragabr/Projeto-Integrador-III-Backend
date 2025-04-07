package zad_inventory.repository;

import zad_inventory.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();
    private Long idAtual = 1L;

    public Usuario salvar(Usuario usuario) {
        usuario.setId(idAtual++);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst();
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream()
            .filter(u -> u.getEmail().equalsIgnoreCase(email))
            .findFirst();
    }

    public boolean deletarPorId(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }
}

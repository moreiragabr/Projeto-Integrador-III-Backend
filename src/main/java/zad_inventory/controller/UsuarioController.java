package zad_inventory.controller;

import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.model.service.UsuarioService;

import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();

    public void cadastrarNovoUsuario(UsuarioEntity novoUsuario, UsuarioEntity usuarioLogado) {
        usuarioService.registrarUsuario(novoUsuario, usuarioLogado);
    }

    public List<UsuarioEntity> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    public List<UsuarioEntity> exibirRanking() {
        return usuarioService.listarRankingUsuarios();
    }
}

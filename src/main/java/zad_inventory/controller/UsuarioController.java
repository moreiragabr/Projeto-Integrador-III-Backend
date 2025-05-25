package zad_inventory.controller;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.service.UsuarioService;

import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

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

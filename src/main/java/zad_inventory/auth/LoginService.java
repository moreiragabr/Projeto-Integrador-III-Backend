package zad_inventory.auth;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.service.UsuarioService;

import java.util.Scanner;

public class LoginService {

    private final UsuarioService usuarioService;

    public LoginService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioEntity realizarLogin(String email, String senha) {
        try {
            UsuarioEntity usuario = usuarioService.buscarPorEmail(email);
            if (usuario.getSenha().equals(senha)) {
                System.out.println(" Login bem-sucedido! Bem-vindo(a), " + usuario.getNome());
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(" Usuário não encontrado.");
        }
        return null;
    }
}

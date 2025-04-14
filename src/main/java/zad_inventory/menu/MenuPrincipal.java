package zad_inventory.menu;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.menu.MenuAdmin;
import zad_inventory.menu.MenuFuncionario;

public class MenuPrincipal {

    public static void exibir(UsuarioEntity usuarioLogado) {
        if (usuarioLogado.getTipoUsuario() == TipoUsuario.GERENTE) {
            MenuAdmin admin = new MenuAdmin(usuarioLogado);
            admin.exibir();
        } else {
            MenuFuncionario funcionario = new MenuFuncionario(usuarioLogado);
            funcionario.exibir();
        }
    }
}

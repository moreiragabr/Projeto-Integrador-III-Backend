package zad_inventory.view;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.view.gui.guiAdmin.GuiAdmin;
import zad_inventory.view.gui.guiFuncionario.GuiFuncionario;


public class MenuPrincipal {

    public static void exibir(UsuarioEntity usuarioLogado) {
        if (usuarioLogado.getTipoUsuario() == TipoUsuario.GERENTE) {
            GuiAdmin telaAdmin = new GuiAdmin(usuarioLogado);
        } else {
            GuiFuncionario telaFuncionario = new GuiFuncionario(usuarioLogado);
        }
    }
}

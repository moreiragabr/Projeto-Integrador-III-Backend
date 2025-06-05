package zad_inventory;


import zad_inventory.view.gui.GuiLogin;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Sistema iniciado com sucesso!");

            SwingUtilities.invokeLater(() -> {
            GuiLogin telaLogin = new GuiLogin();
            });
    }
}

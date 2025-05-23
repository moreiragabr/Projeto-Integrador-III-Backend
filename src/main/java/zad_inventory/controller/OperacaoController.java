package zad_inventory.controller;

import java.util.List;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.Situacao;
import zad_inventory.service.OperacaoService;

public class OperacaoController {

    private final OperacaoService service;
    private final UsuarioEntity usuarioLogado;

    /**public OperacaoController(UsuarioEntity usuarioLogado, OperacaoService operacaoService) {
        this.service = operacaoService;
        this.usuarioLogado = usuarioLogado;
    }**/

    public OperacaoController(UsuarioEntity usuarioLogado) {
        this.service = new OperacaoService();
        this.usuarioLogado = usuarioLogado;
    }

    public OperacaoEntity registrarOperacao(Long produtoId, int quantidade) {
        return service.registrarVenda(usuarioLogado, produtoId, quantidade);
    }


    public List<OperacaoEntity> listarOperacoes() {
        return service.buscarTodos();
    }

    public OperacaoEntity buscarOperacaoPorId(Long id) {
        return service.buscarPorId(id);
    }

    public OperacaoEntity atualizarSituacao(Long id, Situacao situacao) {
        return service.atualizarSituacao(id, situacao);
    }

    public List<OperacaoEntity> filtrarPorSituacao(Situacao situacao) {
        return service.filtrarPorSituacao(situacao);
    }


}

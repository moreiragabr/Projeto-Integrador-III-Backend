package zad_inventory.service;

import zad_inventory.entity.SituacaoEntity;
import zad_inventory.repository.SituacaoRepository;
import java.util.List;

public class SituacaoService {

    private final SituacaoRepository repository;

    public SituacaoService(SituacaoRepository repository){this.repository = repository;}

    // CREATE
    public void criarSituacao(SituacaoEntity situacao) {
        validarCamposObrigatorios(situacao);
        repository.salvar(situacao);
    }

    // READ
    public List<SituacaoEntity> listarTodasSituacoes() {
        return repository.buscarTodos();
    }

    public SituacaoEntity buscarSituacaoPorId(Long id) {
        validarId(id);
        return repository.buscarPorId(id);
    }

    // UPDATE
    public void atualizarSituacao(SituacaoEntity situacao) {
        validarId(situacao.getId());
        validarCamposObrigatorios(situacao);
        repository.atualizar(situacao);
    }

    // DELETE
    public void removerSituacao(Long id) {
        validarId(id);
        SituacaoEntity situacao = repository.buscarPorId(id);
        repository.remover(situacao);
    }

    // Validações
    private void validarCamposObrigatorios(SituacaoEntity situacao) {
        if(situacao.getSituacaoNome() == null || situacao.getSituacaoNome().isBlank()) {
            throw new IllegalArgumentException("Nome da situação é obrigatório!");
        }

        if(situacao.getProdutoId() <= 0 || situacao.getUsuarioId() <= 0) {
            throw new IllegalArgumentException("IDs de produto e usuário devem ser positivos!");
        }
    }

    private void validarId(Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
    }
}

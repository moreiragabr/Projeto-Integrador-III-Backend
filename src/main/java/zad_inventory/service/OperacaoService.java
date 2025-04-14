package zad_inventory.service;

import zad_inventory.entity.OperacaoEntity;
import zad_inventory.enums.Situacao;
import zad_inventory.repository.OperacaoRepository;

import java.time.LocalDate;
import java.util.List;

public class OperacaoService {

    private final OperacaoRepository operacaoRepository;

    public OperacaoService(OperacaoRepository operacaoRepository) {
        this.operacaoRepository = operacaoRepository;
    }


    public void createOperation(OperacaoEntity operacao) {
        operacaoRepository.save(operacao);
    }


    public List<OperacaoEntity> listAllOperations() {
        return operacaoRepository.listAll();
    }


    public OperacaoEntity findById(Long id) {
        return operacaoRepository.findById(id);
    }


    public void updateOperation(OperacaoEntity operacao) {
        operacaoRepository.update(operacao);
    }

    public void deleteOperation(OperacaoEntity operacao) {
        operacaoRepository.delete(operacao);
    }

    //Alterar operacao para cancelada
    public void cancelOperation(Long id) {
        OperacaoEntity op = operacaoRepository.findById(id);
        if (op != null) {
            op.setSituacao(Situacao.CANCELADA);
            operacaoRepository.update(op);
        }
    }

    //CONSULTA POR DATA EXATA
    public List<OperacaoEntity> listByDate(LocalDate data) {
        return operacaoRepository.findByDate(data);
    }

    //CONSULTA POR DATA PERIODO
    public List<OperacaoEntity> listByPeriod(LocalDate inicio, LocalDate fim) {
        return operacaoRepository.findByPeriod(inicio, fim);
    }

    //CONSULTA POR USUARIO
    public List<OperacaoEntity> listByUsuarioNome(String nome) {
        return operacaoRepository.findByUsuarioNome(nome);
    }

    //CONSULTA POR SITUAÇÃO
    public List<OperacaoEntity> listBySituacao(Situacao situacao) {
        return operacaoRepository.findBySituacao(situacao);
    }
}

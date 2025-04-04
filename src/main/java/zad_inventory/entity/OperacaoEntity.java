package zad_inventory.entity;

import java.time.LocalDateTime;

public class OperacaoEntity {
    private int id;
    private int produtoId;
    private String produtoNome;
    private int usuarioId;
    private String usuarioNome;
    private int situacaoId;
    private int quantidade;
    private LocalDateTime data;

    public OperacaoEntity() {}


    public OperacaoEntity(int id, int produtoId, int usuarioId, int situacaoId, int quantidade, LocalDateTime data) {
        this.id = id;
        this.produtoId = produtoId;
        this.usuarioId = usuarioId;
        this.situacaoId = situacaoId;
        this.quantidade = quantidade;
        this.data = data;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public String getProdutoNome() { return produtoNome; }
    public void setProdutoNome(String produtoNome) { this.produtoNome = produtoNome; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public int getSituacaoId() { return situacaoId; }
    public void setSituacaoId(int situacaoId) { this.situacaoId = situacaoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    @Override
    public String toString() {
        return "Operacao{" +
                "id=" + id +
                ", produto=" + produtoNome +
                ", usuarioId=" + usuarioNome +
                ", situacaoId=" + situacaoId +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }
}

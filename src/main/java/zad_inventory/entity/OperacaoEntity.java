package zad_inventory.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import zad_inventory.enums.Situacao;

@Entity
@Table(name = "operacao")
public class OperacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_produto_id")
    private int produtoId;

    @Column(name = "produto_nome")
    private String produtoNome;

    @Column(name = "fk_usuario_id")
    private int usuarioId;

    @Column(name = "usuario_nome")
    private String usuarioNome;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "data")
    private LocalDateTime data;

    public OperacaoEntity() {}

    public OperacaoEntity(Long id, int produtoId, String produtoNome, int usuarioId, String usuarioNome, Situacao situacao, int quantidade, LocalDateTime data) {
        this.id = id;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.situacao = situacao;
        this.quantidade = quantidade;
        this.data = data;
    }


    @Override
    public String toString() {
        return "OperacaoEntity{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", produtoNome='" + produtoNome + '\'' +
                ", usuarioId=" + usuarioId +
                ", usuarioNome='" + usuarioNome + '\'' +
                ", situacao='" + situacao + '\'' +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

    // Getters e Setters


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public String getProdutoNome() { return produtoNome; }
    public void setProdutoNome(String produtoNome) { this.produtoNome = produtoNome; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public Situacao getSituacao() { return situacao; }
    public void setSituacao(Situacao situacao) { this.situacao = situacao; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
}

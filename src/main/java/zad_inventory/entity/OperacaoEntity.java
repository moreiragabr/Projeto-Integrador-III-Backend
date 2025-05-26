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

    @ManyToOne
    @JoinColumn(name = "fk_produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    public OperacaoEntity() {}

    public OperacaoEntity(ProdutoEntity produto, UsuarioEntity usuario, Situacao situacao, int quantidade, LocalDateTime data) {
        this.produto = produto;
        this.usuario = usuario;
        this.situacao = situacao;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "OperacaoEntity{" +
                "id=" + id +
                ", produto=" + produto.getNomeProduto() +
                ", usuario=" + usuario.getNome() +
                ", situacao=" + situacao +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public ProdutoEntity getProduto() { return produto; }

    public void setProduto(ProdutoEntity produto) { this.produto = produto; }

    public UsuarioEntity getUsuario() { return usuario; }

    public void setUsuario(UsuarioEntity usuario) { this.usuario = usuario; }

    public Situacao getSituacao() { return situacao; }

    public void setSituacao(Situacao situacao) { this.situacao = situacao; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getData() { return data; }

    public void setData(LocalDateTime data) { this.data = data; }
}

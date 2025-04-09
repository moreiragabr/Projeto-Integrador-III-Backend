package zad_inventory.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "situacao")
public class SituacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "situacao_nome")
    private String situacaoNome;

    @ManyToOne
    @JoinColumn(name = "fk_id_produto")
    private int produtoId;

    @Column(name = "produto_nome")
    private String produtoNome;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private int usuarioId;

    @Column(name = "usuario_nome")
    private String usuarioNome;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "data")
    private LocalDateTime data;

    public SituacaoEntity() {}

    public SituacaoEntity(Long id, String situacaoNome, int produtoId, String produtoNome, int usuarioId, String usuarioNome, int quantidade, LocalDateTime data) {
        this.id = id;
        this.situacaoNome = situacaoNome;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SituacaoEntity{" +
                "id=" + id +
                ", situacaoNome='" + situacaoNome + '\'' +
                ", produtoId=" + produtoId +
                ", produtoNome='" + produtoNome + '\'' +
                ", usuarioId=" + usuarioId +
                ", usuarioNome='" + usuarioNome + '\'' +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

    public String getSituacaoNome() {return situacaoNome;}
    public void setSituacaoNome(String situacaoNome) {this.situacaoNome = situacaoNome;}

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public int getProdutoId() {return produtoId;}
    public void setProdutoId(int produtoId) {this.produtoId = produtoId;}

    public String getProdutoNome() {return produtoNome;}
    public void setProdutoNome(String produtoNome) {this.produtoNome = produtoNome;}

    public int getUsuarioId() {return usuarioId;}
    public void setUsuarioId(int usuarioId) {this.usuarioId = usuarioId;}

    public String getUsuarioNome() {return usuarioNome;}
    public void setUsuarioNome(String usuarioNome) {this.usuarioNome = usuarioNome;}

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public LocalDateTime getData() {return data;}
    public void setData(LocalDateTime data) {this.data = data;}
}

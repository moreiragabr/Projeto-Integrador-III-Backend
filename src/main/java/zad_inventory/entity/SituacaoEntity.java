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
    private String situacao_nome;

    @JoinColumn(name = "fk_id_produto")
    private int fk_id_produto;

    @Column(name = "nome_produto")
    private String nome_produto;

    @JoinColumn(name = "fk_id_usuario")
    private int fk_id_usuario;

    @Column(name = "usuario_nome")
    private String usuario_nome;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "data")
    private LocalDateTime data;

    public SituacaoEntity() {}

    public SituacaoEntity(Long id, String situacao_nome, int fk_id_produto, String nome_produto, int fk_id_usuario, String usuario_nome, int quantidade, LocalDateTime data) {
        this.id = id;
        this.situacao_nome = situacao_nome;
        this.fk_id_produto = fk_id_produto;
        this.nome_produto = nome_produto;
        this.fk_id_usuario = fk_id_usuario;
        this.usuario_nome = usuario_nome;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getSituacaoNome() {return situacao_nome;}
    public void setSituacaoNome(String situacaoNome) {this.situacao_nome = situacaoNome;}

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public int getProdutoId() {return fk_id_produto;}
    public void setProdutoId(int produtoId) {this.fk_id_produto = produtoId;}

    public String getProdutoNome() {return nome_produto;}
    public void setProdutoNome(String produtoNome) {this.nome_produto = produtoNome;}

    public int getUsuarioId() {return fk_id_usuario;}
    public void setUsuarioId(int usuarioId) {this.fk_id_usuario = usuarioId;}

    public String getUsuarioNome() {return usuario_nome;}
    public void setUsuarioNome(String usuarioNome) {this.usuario_nome = usuarioNome;}

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public LocalDateTime getData() {return data;}
    public void setData(LocalDateTime data) {this.data = data;}

}

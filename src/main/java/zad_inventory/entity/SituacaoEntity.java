package zad_inventory.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "situacao")
public class SituacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = " ")
    private int produtoId;

    @Column(name = " ")
    private String produtoNome;

    @ManyToOne
    @JoinColumn(name = " ")
    private int usuarioId;

    @Column(name = " ")
    private String usuarioNome;

    @Column(name = " ")
    private int quantidade;

    @Column(name = " ")
    private LocalDateTime data;

    public SituacaoEntity() {}

    public SituacaoEntity(Long id, int produtoId, String produtoNome, int usuarioId, String usuarioNome, int quantidade, LocalDateTime data) {
        this.id = id;
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
                ", produtoId=" + produtoId +
                ", produtoNome='" + produtoNome + '\'' +
                ", usuarioId=" + usuarioId +
                ", usuarioNome='" + usuarioNome + '\'' +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

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

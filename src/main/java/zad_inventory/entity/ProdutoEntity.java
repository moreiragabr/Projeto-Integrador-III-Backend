package zad_inventory.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "quantidade")
    private int quantidade;

    @Formula("(SELECT c.nome FROM categoria c WHERE c.id = fk_categoria_id)")
    private String nomeCategoria;

    @Formula("(SELECT c.descricao FROM categoria c WHERE c.id = fk_categoria_id)")
    private String descricaoCategoria;

    @Column(name = "fk_categoria_id")
    private int categoriaId; //public classe variavel

    @Column(name = "fk_usuario_id")
    private Long usuarioId; //public classe variavel

    @Column(name = "cor")
    private String cor;

    @Column(name = "tamanho")
    private String tamanho;

    public ProdutoEntity() {}

    public ProdutoEntity(Long id, String nomeProduto, int quantidade, int categoriaId, Long usuarioId, String cor, String tamanho) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.categoriaId = categoriaId;
        this.usuarioId = usuarioId;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", categoriaId=" + categoriaId +
                ", nomeCategoria=" + nomeCategoria +
                ", descricaoCategoria=" + descricaoCategoria +
                ", usuarioId=" + usuarioId +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

}